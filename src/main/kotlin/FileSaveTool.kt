import dev.langchain4j.agent.tool.Tool
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class FileSaveTool {
    @Tool
    fun saveFile(filePath: String, content: String): String {
        try {
            val path = Path.of(filePath)
            Files.createDirectories(path.getParent())
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
            return "File saved to: " + path.toAbsolutePath()
        } catch (e: IOException) {
            return "Failed to save file: " + e.message
        }
    }
}