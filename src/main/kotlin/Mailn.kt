import kotlinx.serialization.Serializable

fun main() {


    val prompts = Prompts()
    val gameCreatePrompt = composeFirstPrompt(prompts.gameDescriptionCreatePrompt)
    val gameDescription = AgentJob(AgentType.GameDescription, gameCreatePrompt, prompts.gameDescriptionImprovePrompt, 98)
    val gameDescriptionResult = gameDescription.getDescription()
    println(gameDescriptionResult)
    val visualGameDescription = AgentJob(AgentType.VisualGameDescription, gameDescriptionResult + "\n" + prompts.gameVisualDescriptionCreatePrompt, prompts.gameVisualDescriptionImprovePrompt, 98)
    println(visualGameDescription.getDescription())
}

private fun composeFirstPrompt(@Suppress("SameParameterValue") gameDescriptionCreatePrompt: String): String {
    val userRequest = Agent(AgentType.GameDescription).chatMemory.messages().firstOrNull() //Check in agent memory. Looks like hack

    if (userRequest != null)
        return userRequest.toString()

    println("I can create a game for you. What do you want?")
    val request = readln()

    return "I’m creating a Unity game. $request \n $gameDescriptionCreatePrompt"
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