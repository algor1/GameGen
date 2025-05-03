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
        val filePath = "test.txt"
        val fullPath = Path.of(workDir, aiGeneratedSubDir, filePath)
        val content = "Hello, Kotlin!"

        // Act
        val result = tool.saveFile(filePath, content)

        // Assert
        assertTrue(result.contains("File saved to"))
        val savedContent = Files.readString(fullPath)
        assertEquals(content, savedContent)
    }

    @Test
    fun `should create nested directories and save file`(@TempDir tempDir: Path) {
        // Arrange

        val nestedPath = "subdir1\\subdir2\\file.txt"
        val content = "Nested content"
        val fullPath = Path.of(workDir, aiGeneratedSubDir, nestedPath)

        // Act
        val result = tool.saveFile(nestedPath.toString(), content)

        // Assert
        assertTrue(Files.exists(fullPath))
        assertEquals(content, fullPath.readText())
        assertTrue(result.contains("File saved to"))
    }
}
