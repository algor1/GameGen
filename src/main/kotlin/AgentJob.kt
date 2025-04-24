import dev.langchain4j.data.message.ChatMessageType

class AgentJob(val agentType: AgentType, val creationPrompt: String, val evaluationPrompt: String, val improvePrompt: String, val minScore: Int = 90): AutoCloseable {
    val agent = Agent(agentType)
    private val assistant = agent.assistant

    private fun create() {
        println(assistant.chat(creationPrompt + "\n Expect your answer in format: ```${agentType} <your answer here> ```"))
    }

    private fun improve() {
        while (evaluate() < minScore)
            println(assistant.chat(improvePrompt + "\n Expect your answer in format: ```${agentType} <your answer here> ```")) // should be changed to acsept by user
    }

    private fun evaluate():Int {
        val response = assistant.chat(evaluationPrompt)
        println(response)
        return OutputParser.parseEvaluation(response)
    }

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
            val messages = OutputParser.parse(message.text(), agentType.toString())
            if (messages.size == 1)
                return messages.first()
        }
        return ""
    }

    override fun close() {
        agent.close()
    }
}