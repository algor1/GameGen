class Prompts {

    val gameDescriptionImprovePrompt = "Evaluate the quality of the game rules description provided in last message\n" +
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

    val gameVisualDescriptionCreatePrompt ="Describe the visual design of the game in great detail so that a game artist can create all necessary visual assets.\n" +
            "Your description should include:\n" +
            "Overall Look and Feel\n" +
            "What is the visual style? (e.g., pixel art, cartoon, realistic, minimalist)\n" +
            "What is the color palette or mood (bright, dark, futuristic, retro, etc.)?\n" +
            "Player Character\n" +
            "What does the player character look like? (shape, size, color, animations, equipment, etc.)\n" +
            "How does it move or change visually during gameplay (running, jumping, damaged, powered-up)?\n" +
            "Enemies and NPCs\n" +
            "Describe different types of enemies/NPCs, their appearance, size, animations, and behaviors.\n" +
            "Game World / Environment\n" +
            "Describe the setting (e.g., forest, dungeon, space station).\n" +
            "What background elements are there? (sky, walls, ground, decorations)\n" +
            "Are there parallax layers or animated elements?\n" +
            "Objects and Interactables\n" +
            "Describe visual appearance of items, obstacles, power-ups, doors, chests, etc.\n" +
            "UI / HUD Elements\n" +
            "Describe the layout of the interface during different game states:\n" +
            "Main menu\n" +
            "In-game (e.g., health bar, score, timer, minimap)\n" +
            "Pause menu\n" +
            "Game over screen\n" +
            "Where is each element located on screen?\n" +
            "How do UI elements look (style, colors, fonts, borders)?\n" +
            "Special Effects\n" +
            "Are there any particle effects, animations, screen shakes, flashes, etc.?\n" +
            "Responsiveness / Platform Differences\n" +
            "Describe how visuals should adapt for different platforms (PC, mobile, etc.), if applicable.\n" +
            "Make the description detailed enough so an artist can begin working based only on the text."

    val gameVisualDescriptionImprovePrompt = "Evaluate the quality and completeness of the game's visual description.\n" +
            "Start your response with a score from 0 to 100, where:\n" +
            "0 means the visual description is extremely poor and unusable\n" +
            "100 means the visual description is perfect and fully ready for artists to begin working\n" +
            "Format of score: ```Evaluation: <score>```\n" +
            "Then provide detailed feedback based on the following:\n" +
            "Is every visual aspect of the game described?\n" +
            "Are all objects, characters, UI elements, maps, and sprites listed and explained in sufficient detail?\n" +
            "Is the art style clearly defined (e.g., pixel art, cartoon, realistic, etc.)?\n" +
            "Is it clear how things should look, move, and behave visually?\n" +
            "Are there any missing visual components or vague parts that should be expanded?\n" +
            "Suggest improvements or clarifications that would help an artist fully understand what needs to be drawn and how it should look."

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