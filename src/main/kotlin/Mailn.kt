import kotlinx.serialization.Serializable

fun main() {
    val prompts = Prompts()
    val gameCreatePrompt = composeFirstPrompt(prompts.gameDescriptionCreatePrompt)
    val gameDescription = AgentJob(AgentType.GameDescription, gameCreatePrompt, prompts.ameDescriptionImprovePrompt, 98)

    println(gameDescription.getDescription())
}

private fun composeFirstPrompt(gameDescriptionCreatePrompt: String): String {
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