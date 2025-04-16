class Prompts {

    val ameDescriptionImprovePrompt = "Evaluate the quality of the game rules description provided in last message\n" +
            "Start your response with a score from 0 to 100, where:\n" +
            "0 means the rules are completely unusable\n" +
            "100 means the rules are perfect and ready for implementation\n" +
            "Format of score: ```Evaluation: <score>```\n" +
            "After the score, explain your evaluation:\n" +
            "Are all essential rules clearly stated?\n" +
            "Is the game objective understandable?\n" +
            "Are edge cases and special situations covered?\n" +
            "Is it clear what the player can and cannot do?\n" +
            "Are the win and lose conditions unambiguous?\n" +
            "Are player controls and interactions with the environment explained?\n" +
            "Suggest any improvements or additions needed to make the rules complete and implementation-ready."


    val gameDescriptionCreatePrompt = "Please help me define clear and concise game rules. The rules should include the game genre, goal, main mechanics, win/lose conditions, player controls, and any special features. Present it in a structured and readable format."

//    val prompt1 = "Before proceeding, identify the key technical modules of the game, specifying:\n" +
//            "\n" +
//            "Module Name: A short identifier for the module (e.g., \"Game Logic\", \"Rendering\", \"Networking\").\n" +
//            "\n" +
//            "Description: A brief explanation of what this module does.\n" +
//            "\n" +
//            "Technologies: A list of suggested technologies for this module (e.g., [\"Unity\", \"C#\"], [\"WebSockets\", \"gRPC\"]).\n" +
//            "\n" +
//            "Provide the response in a structured JSON format like this:\n" +
//            "[\n" +
//            "  {\n" +
//            "    \"name\": \"Game Logic\",\n" +
//            "    \"description\": \"Handles core game mechanics, player interactions, and AI behavior.\",\n" +
//            "    \"technologies\": [\"Unity\", \"C#\"]\n" +
//            "  },\n" +
//            "  {\n" +
//            "    \"name\": \"User Interface\",\n" +
//            "    \"description\": \"Manages HUD, menus, and on-screen elements.\",\n" +
//            "    \"technologies\": [\"Unity UI\", \"HTML5\"]\n" +
//            "  }\n" +
//            "]\n"
//
//    val prompt_interfaces = "\"I have a list of game modules generated from a game concept.\n" +
//            "Each module includes its name, description, and technologies.\n" +
//            "\n" +
//            "Now, for each module, please provide:\n" +
//            "\n" +
//            "name: The module's name.\n" +
//            "\n" +
//            "logic: A detailed explanation of the internal logic and responsibilities of the module. This should include data flow, interactions, behaviors, state management, etc. The logic will be used for separate implementation later, so please make it as comprehensive and precise as possible.\n" +
//            "\n" +
//            "interfaces: A code snippet in a public interface style that defines how other modules or parts of the game will interact with this module. Use a suitable language depending on the technologies previously suggested (e.g., TypeScript for frontend, Kotlin or C# for logic modules).\n" +
//            "\n" +
//            "Return the result in the following JSON format:\n" +
//            "\n" +
//            "json\n" +
//            "Copy\n" +
//            "Edit\n" +
//            "[\n" +
//            "  {\n" +
//            "    \"name\": \"<module name>\",\n" +
//            "    \"logic\": \"<detailed logic description>\",\n" +
//            "    \"interfaces\": \"<public interface code here>\"\n" +
//            "  }\n" +
//            "]\n" +
//            "Avoid any additional explanation outside the JSON. Only return the JSON array.\""
}