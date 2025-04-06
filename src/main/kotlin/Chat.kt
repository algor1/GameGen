import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson
import dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson
import dev.langchain4j.data.message.UserMessage
import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.store.memory.chat.ChatMemoryStore
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer.STRING
import java.io.File
import java.time.Duration
import java.util.*

interface Assistant {
    fun chat(message: String): String
}

class Chat {

    val chatMemory: ChatMemory = MessageWindowChatMemory.builder()
        .maxMessages(10)
        .chatMemoryStore(PersistentChatMemoryStore())
        .build()

    private val model: OllamaChatModel = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("gemma3:27b")
        .timeout(Duration.ofMinutes(30))
        .build()

    val assistant: Assistant = AiServices.builder<Assistant>(Assistant::class.java)
        .chatLanguageModel(model)
        .chatMemory(chatMemory)
        .build()

    private val messages: MutableList<ChatMessage> = mutableListOf<ChatMessage>()
    init {
        File("D:\\ChatHistory.txt").appendText(Calendar.getInstance().time.toString() + "\n")
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
        File("D:\\ChatHistory.txt").appendText(historyPoint)
    }
}

enum class HistoryType{
    REQUEST, RESPONSE
}

class PersistentChatMemoryStore : ChatMemoryStore {
    private val db: DB = DBMaker.fileDB("D:\\chat-memory.db").transactionEnable().make()
    private val map: MutableMap<String, String> = db.hashMap("messages", STRING, STRING).createOrOpen()

    override fun getMessages(memoryId: Any): List<ChatMessage> {
        val json = map[memoryId as String]
        return messagesFromJson(json)
    }

    override fun updateMessages(memoryId: Any, messages: List<ChatMessage>) {
        val json = messagesToJson(messages)
        map[memoryId as String] = json
        db.commit()
    }

    override fun deleteMessages(memoryId: Any) {
        map.remove(memoryId as String)
        db.commit()
    }
}