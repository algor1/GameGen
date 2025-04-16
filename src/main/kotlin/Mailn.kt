import kotlinx.serialization.Serializable

fun main() {
    val prompts = Prompts()
    val gameDescription = AgentJob(AgentType.GameDescription, prompts.gameDescriptionCreatePrompt(), prompts.ameDescriptionImprovePrompt, 98)
    println(gameDescription.getDescription())
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