import kotlinx.serialization.*
import java.util.Scanner

fun main() {
    val assistant = Agent(AgentType.PM).assistant
    val prompts = Prompts()
    val gameDescription = GameDescription()
    gameDescription.create()
    println(gameDescription.getDescription())

//    val Response: String = assistant.chat(prompts.gameIdea)
//    println(Response)
//    return
//    val modulesResponse: String = assistant.chat(prompts.gameIdea + prompts.prompt1)
//    val modulesJsonInput = OutputParser.parse(modulesResponse, "json").first()
//    val modules = OutputParser.parseModules(modulesJsonInput)
//
//    modules.forEach { module ->
//        println("Module: ${module.name}")
//        println("Description: ${module.description}")
//        println("Technologies: ${module.technologies.joinToString(", ")}")
//        println()
//    }
//
//    val interfacesResponse = assistant.chat(prompts.prompt_interfaces)
//    println(interfacesResponse)
//    val interfacesJsonInput = OutputParser.parse(interfacesResponse, "json").first()
//    val interfaces = OutputParser.parseInterfaces(interfacesJsonInput)
}

class GameDescription {
    val agent = Agent(AgentType.GameDescription)
    val assistant = agent.assistant
    fun create() {
        println("I can create a game for you. What do you want?")
        var request = readln()
        var prompt =
            "I’m creating a Unity game. $request \n Please help me define clear and concise game rules. The rules should include the game genre, goal, main mechanics, win/lose conditions, player controls, and any special features. Present it in a structured and readable format."
        assistant.chat(prompt)
        prompt = "Evaluate the quality of the game rules description provided in step 1.\n" +
                "Start your response with a score from 0 to 100, where:\n" +
                "0 means the rules are completely unusable\n" +
                "100 means the rules are perfect and ready for implementation\n" +
                "After the score, explain your evaluation:\n" +
                "Are all essential rules clearly stated?\n" +
                "Is the game objective understandable?\n" +
                "Are edge cases and special situations covered?\n" +
                "Is it clear what the player can and cannot do?\n" +
                "Are the win and lose conditions unambiguous?\n" +
                "Are player controls and interactions with the environment explained?\n" +
                "Suggest any improvements or additions needed to make the rules complete and implementation-ready."
        while (readFirstInt(assistant.chat(prompt)) < 80)
            assistant.chat ("I agree with your suggestion. Apply them and show me the fixed rules")
    }

    fun getDescription() :String{
        return agent.getLastDecision()
    }

    fun readFirstInt(text:String): Int {
        val scan = Scanner(text)
        val value = scan.nextInt()
        return value
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