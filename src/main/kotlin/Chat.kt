import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.UserMessage
import dev.langchain4j.model.ollama.OllamaChatModel
import java.io.File
import java.time.Duration
import java.time.format.DateTimeFormatter
import java.util.*

class Chat {
    private val model: OllamaChatModel = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("gemma3:27b")
        .timeout(Duration.ofMinutes(30))
        .build()

    private val messages: MutableList<ChatMessage> = mutableListOf<ChatMessage>()
    init {
        File("D:\\ChatHistory.txt",).appendText(Calendar.getInstance().time.toString() + "\n")
    }

    fun generate(request: String): String {
        saveChat(request, HistoryType.REQUEST)
        messages.add(UserMessage.from(request))
        val response = model.generate(messages).content()
        messages.add(response)
        saveChat(response.text(), HistoryType.RESPONSE)
        return response.text()
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