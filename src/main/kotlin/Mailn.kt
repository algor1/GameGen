import kotlinx.serialization.Serializable

const val workDir = "D:\\GameGenOutput\\"
const val aiGeneratedSubDir = "AIGenerated"

fun main() {

    val prompts = Prompts()
    val gameCreatePrompt = composeFirstPrompt(prompts.gameDescriptionCreatePrompt)
    val gameDescriptionResult:String =
        AgentJob(AgentType.GameDescription.toString(),
            gameCreatePrompt,
            prompts.gameDescriptionEvaluationPrompt,
            prompts.gameDescriptionImprovePrompt,
            98)
            .use {gameDescription -> gameDescription.getDescription()}
    println(gameDescriptionResult)

    val visualGameDescriptionResult:String =
        AgentJob(AgentType.VisualGameDescription.toString(),
            gameDescriptionResult + "\n" + prompts.gameVisualDescriptionCreatePrompt,
            prompts.gameVisualDescriptionEvaluationPrompt,
            prompts.gameVisualDescriptionImprovePrompt,
            95)
            .use{visualGameDescription -> visualGameDescription.getDescription()}
    println(visualGameDescriptionResult)

    val objectDescriptionsListResult: String =
        AgentJob(AgentType.ObjectDescriptions.toString(),
        gameDescriptionResult + visualGameDescriptionResult+ "\n" + prompts.objectsDescriptionCreatePrompt,
        prompts.objectsDescriptionEvaluatePrompt,
        prompts.objectsDescriptionImprovePrompt,
        95)
        .use{objectDescriptionsList -> objectDescriptionsList.getDescription()}
    println(objectDescriptionsListResult)

    val objectInterfacesListResult: String =
        AgentJob(AgentType.ObjectInterfaces.toString(),
            gameDescriptionResult + "\n" + visualGameDescriptionResult+ "\n" + objectDescriptionsListResult + "\n"+ prompts.objectInterfacesCreatePrompt,
            prompts.objectInterfacesEvaluatePrompt,
            prompts.objectInterfacesImprovePrompt,
            95)
            .use{objectIntarfacesList -> objectIntarfacesList.getDescription()}
    println(objectInterfacesListResult)

    val agent = Agent(AgentType.InterfacesSaver.toString())
    if (agent.chatMemory.messages().any { it.text().contains("File saved to") }.not()) {
        val saveResult = agent.assistant.chat(prompts.saveInterfacesPrompt + objectInterfacesListResult)
        println(saveResult)
    }



//    val interfacesTestsListResult: String =
//        AgentJob(AgentType.InterfacesTests.toString(),
//            gameDescriptionResult + "\n" + visualGameDescriptionResult+ "\n" + objectDescriptionsListResult + "\n"+ objectInterfacesListResult + "\n" + prompts.interfacesTestsCreatePrompt,
//            prompts.interfacesTestsEvaluatePrompt,
//            prompts.interfacesTestsImprovePrompt,
//            95)
//            .use{interfacesTestsList -> interfacesTestsList.getDescription()}
//    println(interfacesTestsListResult)
//
//    val implementationListResult: String =
//        AgentJob(AgentType.Implementation.toString(),
//            objectDescriptionsListResult + "\n"+ objectInterfacesListResult + "\n"+ interfacesTestsListResult +"\n"+ prompts.implementationCreatePrompt,
//            prompts.implementationEvaluatePrompt,
//            prompts.implementationImprovePrompt,
//            95)
//            .use{implementationList -> implementationList.getDescription()}
//    println(implementationListResult)


}

private fun composeFirstPrompt(@Suppress("SameParameterValue") gameDescriptionCreatePrompt: String): String {
    val userRequest = Agent(AgentType.GameDescription.toString()).use{agent: Agent ->  agent.chatMemory.messages().firstOrNull()} //Check in agent memory. Looks like hack

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