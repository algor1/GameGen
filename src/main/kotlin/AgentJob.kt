class AgentJob(agentType: AgentType, val creationPrompt: String, val improvePrompt: String, val minScore: Int = 90) {
    private val agent = Agent(AgentType.GameDescription)
    private val assistant = agent.assistant

    private fun create() {
        assistant.chat(creationPrompt)
    }

    private fun improve() {
        while (OutputParser.parseEvaluation(assistant.chat(improvePrompt)) < minScore)
            assistant.chat("I agree with your suggestions. Apply them and show me the fixed rules") // should be changed to user conversation
    }

    fun getDescription() :String{
        complete()
        return agent.getLastDecision()
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
        for (message in agent.chatMemory.messages().asReversed()) {
            val evaluation = OutputParser.parseEvaluation(message.text())
            if (evaluation>0)
                return evaluation
        }
        return 0
    }
}