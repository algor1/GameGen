import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.ChatMessageType

class AgentJob(val agentType: String, val creationPrompt: String, val evaluationPrompt: String, val improvePrompt: String, val minScore: Int = 90): AutoCloseable {
    val agent = Agent(agentType)
    private val assistant = agent.assistant

    private fun create() {
        println(assistant.chat(creationPrompt + "\n Return your answer fully wrapped in a markdown code block. Begin with: \n" +
                "```${agentType}\n End with:\n" +
                "```"))
    }

    private fun improve() {
        while (evaluate() < minScore)
            println(assistant.chat(improvePrompt + "\n Return your answer fully wrapped in a markdown code block. Begin with: \n" +
                    "```${agentType}\n End with:\n" +
                    "```")) // should be changed to accept by user
    }

    private fun evaluate():Int {
        removePreviousResultsFromMemory()
        val response = assistant.chat(evaluationPrompt)
        println(response)
        return OutputParser.parseEvaluation(response)
    }

    private fun removePreviousResultsFromMemory() {
        val messages = agent.chatMemory.messages()
        if (messages.size > 5 && isAiResultResponse(messages[1]) && isAiEvaluationResponse(messages[3])) {
            val filtered = messages.filterIndexed { index, _ -> index !in 1..4 }.toList()
            agent.chatMemory.clear()
            for (message in filtered)
                agent.chatMemory.add(message)
        }
    }

    private fun isAiResultResponse(message: ChatMessage) =
        message.type() == ChatMessageType.AI && OutputParser.parse(message.text(), agentType.toString()).isNotEmpty()

    private fun isAiEvaluationResponse(message: ChatMessage) =
        message.type() == ChatMessageType.AI && OutputParser.parseEvaluation(message.text()) > 0

    fun getDescription() :String{
        complete()
        return findLastAnswer()
    }

    private fun complete(){
        if (!created())
            create()
        if (findLastEvaluation() < minScore)
            improve()
    }
    private fun created(): Boolean{
        return agent.chatMemory.messages().size > 1
    }

    private fun findLastEvaluation(): Int{
        val reversedMemory = agent.chatMemory.messages().asReversed()
        for (message in reversedMemory) {
            val evaluation = OutputParser.parseEvaluation(message.text())
            if (evaluation>0)
                return evaluation
        }
        return 0
    }

    private fun findLastAnswer(): String{
        val reversedMemory = agent.chatMemory.messages().filter { it.type() == ChatMessageType.AI }.asReversed()
        for (message in reversedMemory) {
            val messages = OutputParser.parse(message.text(), agentType)
            if (messages.size == 1)
                return message.text()
        }
        return ""
    }

    override fun close() {
        agent.close()
    }
}