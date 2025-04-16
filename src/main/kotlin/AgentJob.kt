class AgentJob(val agentType: AgentType, val creationPrompt: String, val improvePrompt: String, val minScore: Int = 90) {
    private val agent = Agent(agentType)
    private val assistant = agent.assistant

    private fun create() {
        assistant.chat(creationPrompt + "\n Expect your answer in format: ```${agentType} <your answer here> ```")
    }

    private fun improve() {
        while (OutputParser.parseEvaluation(assistant.chat(improvePrompt)) < minScore)
            assistant.chat("I agree with your suggestions. Apply them and show me the fixed rules. \n" +
                    "Expect your answer in format: ```${agentType} <the fixed rules here> ```") // should be changed to user conversation
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
        val reversedMemory = agent.chatMemory.messages().asReversed()
        for (message in reversedMemory) {
            val messages = OutputParser.parse(message.text(), agentType.toString())
            if (messages.size == 1)
                return messages.first()
        }
        return ""
    }
}