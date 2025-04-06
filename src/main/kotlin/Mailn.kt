import kotlinx.serialization.*

fun main() {
    val assistant = Chat().assistant
    val prompts = Prompts()

    val modulesResponse: String = assistant.chat(prompts.gameIdea + prompts.prompt1)
    val modulesJsonInput = OutputParser.parse(modulesResponse, "json").first()
    val modules = OutputParser.parseModules(modulesJsonInput)

    modules.forEach { module ->
        println("Module: ${module.name}")
        println("Description: ${module.description}")
        println("Technologies: ${module.technologies.joinToString(", ")}")
        println()
    }

    val interfacesResponse = assistant.chat(prompts.prompt_interfaces)
    println(interfacesResponse)
    val interfacesJsonInput = OutputParser.parse(interfacesResponse, "json").first()
    val interfaces = OutputParser.parseInterfaces(interfacesJsonInput)
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