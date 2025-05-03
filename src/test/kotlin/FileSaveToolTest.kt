import org.junit.jupiter.api.*
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileSaveToolTest {

    private val tool = FileSaveTool()

    @Test
    fun `should save file successfully`(@TempDir tempDir: Path) {
        // Arrange
        val filePath = tempDir.resolve("test.txt").toString()
        val content = "Hello, Kotlin!"

        // Act
        val result = tool.saveFile(filePath, content)

        // Assert
        assertTrue(result.contains("File saved to"))
        val savedContent = Files.readString(Path.of(filePath))
        assertEquals(content, savedContent)
    }

    @Test
    fun `should create nested directories and save file`(@TempDir tempDir: Path) {
        // Arrange
        val nestedPath = tempDir.resolve("subdir1/subdir2/file.txt")
        val content = "Nested content"

        // Act
        val result = tool.saveFile(nestedPath.toString(), content)

        // Assert
        assertTrue(Files.exists(nestedPath))
        assertEquals(content, nestedPath.readText())
        assertTrue(result.contains("File saved to"))
    }

    @Test
    fun `should return error message on invalid path`() {
        // Arrange
        val invalidPath = "/dev/null/test.txt" // или другой заведомо недоступный путь
        val content = "Data"

        // Act
        val result = tool.saveFile(invalidPath, content)

        // Assert
        assertTrue(result.contains("Failed to save file"))
        assertTrue(result.contains("filePath: $invalidPath"))
    }
}
