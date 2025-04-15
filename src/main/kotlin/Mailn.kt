import kotlinx.serialization.*

fun main() {

    val gameDescription = GameDescription()
    gameDescription.getDescription()
    println(gameDescription.getDescription())
}

class GameDescription {
    private val agent = Agent(AgentType.GameDescription)
    private val assistant = agent.assistant
    private val minScore = 90

    private fun create() {
        println("I can create a game for you. What do you want?")
        val request = readln()
        val prompt =
            "I’m creating a Unity game. $request \n Please help me define clear and concise game rules. The rules should include the game genre, goal, main mechanics, win/lose conditions, player controls, and any special features. Present it in a structured and readable format."
        assistant.chat(prompt)
        improve()
    }

    private fun improve() {
        val prompt = "Evaluate the quality of the game rules description provided in last message\n" +
                "Start your response with a score from 0 to 100, where:\n" +
                "0 means the rules are completely unusable\n" +
                "100 means the rules are perfect and ready for implementation\n" +
                "Format of score: ```Evaluation: <score>```\n" +
                "After the score, explain your evaluation:\n" +
                "Are all essential rules clearly stated?\n" +
                "Is the game objective understandable?\n" +
                "Are edge cases and special situations covered?\n" +
                "Is it clear what the player can and cannot do?\n" +
                "Are the win and lose conditions unambiguous?\n" +
                "Are player controls and interactions with the environment explained?\n" +
                "Suggest any improvements or additions needed to make the rules complete and implementation-ready."

        while (OutputParser.parseEvaluation(assistant.chat(prompt)) < minScore)
            assistant.chat("I agree with your suggestion. Apply them and show me the fixed rules")
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

@Serializable
data class Module(
    val name: String,
    val description: String,
    val technologies: List<String>
)

@Serializable
data class Interface(
    val name: String,
    val logic: String,
    val interfaces: String
)