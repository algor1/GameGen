import kotlinx.serialization.*

fun main() {
    val chat = Chat()
    val prompts = Prompts()

    val response = chat.generate(prompts.gameIdea + prompts.prompt1)
    val jsonInput = OutputParser.parse(response, "json").first()
    val modules = OutputParser.parseModules(jsonInput)

    modules.forEach { module ->
        println("Module: ${module.name}")
        println("Description: ${module.description}")
        println("Technologies: ${module.technologies.joinToString(", ")}")
        println()
    }

    val gameLogicResponce = chat.generate(prompts.prompt_interfaces)
    println(gameLogicResponce)


}

@Serializable
data class Module(
    val name: String,
    val description: String,
    val technologies: List<String>
)