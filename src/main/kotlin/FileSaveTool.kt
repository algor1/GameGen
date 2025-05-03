import dev.langchain4j.agent.tool.Tool
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class FileSaveTool {
    @Tool
    fun saveFile(filePath: String, content: String): String {
        try {
            val path = Path.of(workDir,  aiGeneratedSubDir, filePath)
            Files.createDirectories(path.getParent())
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
            return "File saved to: " + path.toAbsolutePath()
        } catch (e: Exception) {
            val message = "Failed to save file:  ${e.message} \n" +
                    "filePath: $filePath \n" +
                    "content: $content"
            println(message)
            return message
        }
    }
}