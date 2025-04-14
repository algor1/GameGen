import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson
import dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson
import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.store.memory.chat.ChatMemoryStore
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer.STRING
import java.time.Duration

interface Assistant {
    fun chat(message: String): String
    fun getLastDecision()
}

enum class AgentType{
    DEVELOPER,
    QA,
    PM,
    GameDescription
}

class Agent (val agentType: AgentType){

    private val chatMemory: ChatMemory = MessageWindowChatMemory.builder()
        .maxMessages(10)
        .chatMemoryStore(PersistentChatMemoryStore(agentType))
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

    fun getLastDecision(): String {
        return chatMemory.messages().last().text();
    }
}

class PersistentChatMemoryStore(val agentType: AgentType) : ChatMemoryStore {
    private val db: DB = DBMaker.fileDB("D:\\${agentType}-memory.db").transactionEnable().make()
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