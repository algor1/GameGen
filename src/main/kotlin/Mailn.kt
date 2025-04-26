import kotlinx.serialization.Serializable

fun main() {

    val prompts = Prompts()
    val gameCreatePrompt = composeFirstPrompt(prompts.gameDescriptionCreatePrompt)
    val gameDescriptionResult:String =
        AgentJob(AgentType.GameDescription,
            gameCreatePrompt,
            prompts.gameDescriptionEvaluationPrompt,
            prompts.gameDescriptionImprovePrompt,
            98)
            .use {gameDescription -> gameDescription.getDescription()}
    println(gameDescriptionResult)

    val visualGameDescriptionResult:String =
        AgentJob(AgentType.VisualGameDescription,
            gameDescriptionResult + "\n" + prompts.gameVisualDescriptionCreatePrompt,
            prompts.gameVisualDescriptionEvaluationPrompt,
            prompts.gameVisualDescriptionImprovePrompt,
            95)
            .use{visualGameDescription -> visualGameDescription.getDescription()}
    println(visualGameDescriptionResult)

    val objectDescriptionsListResult: String =
        AgentJob(AgentType.ObjectDescriptions,
        gameDescriptionResult + visualGameDescriptionResult+ "\n" + prompts.objectsDescriptionCreatePrompt,
        prompts.objectsDescriptionEvaluatePrompt,
        prompts.objectsDescriptionImprovePrompt,
        95)
        .use{objectDescriptionsList -> objectDescriptionsList.getDescription()}
    println(objectDescriptionsListResult)

    val objectIntarfacesListResult: String =
        AgentJob(AgentType.ObjectIntarfaces,
            gameDescriptionResult + "\n" + visualGameDescriptionResult+ "\n" + objectDescriptionsListResult + "\n"+ prompts.objectIntarfacesCreatePrompt,
            prompts.objectIntarfacesEvaluatePrompt,
            prompts.objectIntarfacesImprovePrompt,
            95)
            .use{objectIntarfacesList -> objectIntarfacesList.getDescription()}
    println(objectIntarfacesListResult)
}

private fun composeFirstPrompt(@Suppress("SameParameterValue") gameDescriptionCreatePrompt: String): String {
    val userRequest = Agent(AgentType.GameDescription).use{agent: Agent ->  agent.chatMemory.messages().firstOrNull()} //Check in agent memory. Looks like hack

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