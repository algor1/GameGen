import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson
import dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson
import dev.langchain4j.data.message.ChatMessageType
import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.store.memory.chat.ChatMemoryStore
import dev.langchain4j.model.openai.OpenAiChatModel
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer.STRING
import java.time.Duration
import java.io.File

interface Assistant {
    fun chat(message: String): String
}

enum class AgentType{
    GameDescription,
    VisualGameDescription,
    ObjectDescriptions
}
val enableOpenAi:Boolean = true

class Agent (agentType: AgentType): AutoCloseable{

    private val persistentChatMemoryStore = PersistentChatMemoryStore(agentType)

    val chatMemory: ChatMemory = MessageWindowChatMemory.builder()
        .maxMessages(20)
        .chatMemoryStore(persistentChatMemoryStore)
        .build()

    init {
        val messages = chatMemory.messages()
        println("Found ${messages.size} messages in chat $agentType")

        //Delete all user messages at the end of the chat memory. These messages may remain after an incorrect program shutdown.
        while (chatMemory.messages().size > 0 && chatMemory.messages()[chatMemory.messages().size-1].type() == ChatMessageType.USER){
            val dropLast = chatMemory.messages().dropLast(1)
            chatMemory.clear()
            for(message in dropLast)
                chatMemory.add(message)
        }
        println("After initialization ${messages.size} messages in chat $agentType")
    }

    private val model: ChatLanguageModel = createChatModel()

    private fun createChatModel(): ChatLanguageModel {
        if (enableOpenAi){

            return OpenAiChatModel.builder()
                .apiKey(loadApiKey())
                .modelName("gpt-4o-mini") // или gpt-4
                .build()
        }

        return OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("qwq")
            .timeout(Duration.ofMinutes(30))
            .build()
    }

    private fun loadApiKey(): String {
        val path = "C:\\Users\\alexe\\Desktop\\AIkey.txt"
        return File(path).readText().trim() // trim() удаляет лишние пробелы и переносы строк
    }

    val assistant: Assistant = AiServices.builder(Assistant::class.java)
        .chatLanguageModel(model)
        .chatMemory(chatMemory)
        .build()

    override fun close() {
        persistentChatMemoryStore.close()
    }
}

class PersistentChatMemoryStore(agentType: AgentType) : ChatMemoryStore, AutoCloseable {
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

    override fun close() {
        db.close()
    }


}