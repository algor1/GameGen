import dev.langchain4j.model.ollama.OllamaChatModel
import java.io.File
import java.time.Duration

class Chat {
    private val model: OllamaChatModel = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("gemma3:27b")
        .timeout(Duration.ofMinutes(30))
        .build()

    fun generate(request: String): String {
        saveChat(request, HistoryType.REQUEST)
        val response = model.generate(request)
        saveChat(response, HistoryType.RESPONSE)
        return response
    }

    private fun saveChat(content: String, type: HistoryType) {
        val historyPoint = OutputParser.SEPARATOR + type + "\n" +
                content + "\n" +
                OutputParser.SEPARATOR + "\n"
        File("D:\\ChatHistory.txt",).appendText(historyPoint)
    }
}

enum class HistoryType{
    REQUEST, RESPONSE
}