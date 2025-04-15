private const val EVALUATION_TAG = "Evaluation:"

object OutputParser {
    const val SEPARATOR = "```"
    const val DELIMITER = "54693253-0db9-4334-bf25-b6a83137125a"

    fun parse(content: String, contentType: String): List<String> {
        val code = mutableListOf<String>()
        if (content.contains(SEPARATOR + contentType)) {
            val contentTypeLength = contentType.length + SEPARATOR.length
            var startIndex = content.indexOf(SEPARATOR + contentType)
            while (true) {
                val beginIndex = content.indexOf(SEPARATOR + contentType, startIndex) + contentTypeLength
                if (beginIndex == contentTypeLength - 1) break

                val endIndex = content.indexOf(SEPARATOR, beginIndex)
                if (endIndex == -1) break

                val line = content.substring(beginIndex, endIndex)
                code.add(line)
                startIndex = endIndex + SEPARATOR.length
            }
        }
        return code
    }


    fun parseEvaluation(text:String): Int {
        if (text.contains(EVALUATION_TAG))
            return parse(text, EVALUATION_TAG).first().trim().toInt()

        return 0
    }

//    fun parseFiles(input: String): Map<String, String> {
//        val files = mutableMapOf<String, String>()
//        val parts = parse(input, "FILE:")
//        for (part in parts) {
//            val lines = part.split(DELIMITER, limit = 2)
//            if (lines.size < 2) continue
//
//            val filePath = lines[0].trim()
//            val fileContents = lines[1].trim()
//            files[filePath] = fileContents
//        }
//        return files
//    }
//
//    fun parseModules(jsonString: String): List<Module> {
//        return Json.decodeFromString(jsonString)
//    }
//
//    private fun getJsonTupleArray(jsonText: String, arrayName: String, propertyName: String, propertyContent: String): List<Pair<String, String>> {
//        val modules = mutableListOf<Pair<String, String>>()
//
//        val jsonElement = Json.parseToJsonElement(jsonText)
//        if (jsonElement is JsonObject) {
//            val jsonArray = jsonElement[arrayName] as? JsonArray ?: return modules
//
//            for (element in jsonArray) {
//                val jsonObj = element.jsonObject
//                val name = jsonObj[propertyName]?.jsonPrimitive?.content ?: ""
//                val description = jsonObj[propertyContent]?.jsonPrimitive?.content ?: ""
//                modules.add(name to description)
//            }
//        }
//        return modules
//    }
//
//    fun parseInterfaces(interfacesJsonInput: String): List<Interface> {
//        return Json.decodeFromString(interfacesJsonInput)
//    }
}