class Prompts {
    val gameIdea =
        "Create a responsive and modern Snake game in Unity. The game should support both desktop and mobile devices, including gesture controls for mobile users. The UI should be visually appealing and adapt seamlessly to different screen sizes.\n" +
                "Game Rules:\n" +
                "\n" +
                "The player controls a snake that moves continuously in one of four directions (up, down, left, right).\n" +
                "The snake grows in length each time it eats food, which appears randomly on the game board.\n" +
                "The game ends if the snake collides with itself or the boundaries of the game board.\n" +
                "The player's score increases based on the number of food items eaten.\n" +
                "\n" +
                "Game Mechanics:\n" +
                "\n" +
                "Use arrow keys for desktop controls and swipe gestures for mobile controls.\n" +
                "The snake should move in a grid-based game board.\n" +
                "Food should spawn at random positions on the board, avoiding the snake's current position.\n" +
                "The speed of the snake should gradually increase as the game progresses.\n" +
                "Include a start screen, pause functionality, and a game-over screen with the player's score.\n" +
                "\n" +
                "Important: All UI elements (panels, buttons, text) should be generated programmatically from the GameManager script. The GameManager should also create default prefabs for the snake head, body segments, and food if they aren't assigned in the inspector.\n" +
                "The code should:\n" +
                "\n" +
                "Use a single comprehensive GameManager script that handles both game logic and UI creation\n" +
                "Include responsive UI that works across different screen sizes\n" +
                "Support both keyboard and touch controls\n" +
                "Feature smooth gameplay with gradual difficulty increase\n" +
                "Handle all game states (menu, playing, paused, game over)\n" +
                "Include proper collision detection with boundaries and self\n" +
                "Generate all game objects from the GameManager script\n" +
                "\n" +
                "Provide the full, well-commented C# code that can be directly implemented in a Unity 2D project."
    val prompt1 = "Before proceeding, identify the key technical modules of the game, specifying:\n" +
            "\n" +
            "Module Name: A short identifier for the module (e.g., \"Game Logic\", \"Rendering\", \"Networking\").\n" +
            "\n" +
            "Description: A brief explanation of what this module does.\n" +
            "\n" +
            "Technologies: A list of suggested technologies for this module (e.g., [\"Unity\", \"C#\"], [\"WebSockets\", \"gRPC\"]).\n" +
            "\n" +
            "Provide the response in a structured JSON format like this:\n" +
            "[\n" +
            "  {\n" +
            "    \"name\": \"Game Logic\",\n" +
            "    \"description\": \"Handles core game mechanics, player interactions, and AI behavior.\",\n" +
            "    \"technologies\": [\"Unity\", \"C#\"]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"User Interface\",\n" +
            "    \"description\": \"Manages HUD, menus, and on-screen elements.\",\n" +
            "    \"technologies\": [\"Unity UI\", \"HTML5\"]\n" +
            "  }\n" +
            "]\n"

    val prompt_interfaces = "\"I have a list of game modules generated from a game concept.\n" +
            "Each module includes its name, description, and technologies.\n" +
            "\n" +
            "Now, for each module, please provide:\n" +
            "\n" +
            "name: The module's name.\n" +
            "\n" +
            "logic: A detailed explanation of the internal logic and responsibilities of the module. This should include data flow, interactions, behaviors, state management, etc. The logic will be used for separate implementation later, so please make it as comprehensive and precise as possible.\n" +
            "\n" +
            "interfaces: A code snippet in a public interface style that defines how other modules or parts of the game will interact with this module. Use a suitable language depending on the technologies previously suggested (e.g., TypeScript for frontend, Kotlin or C# for logic modules).\n" +
            "\n" +
            "Return the result in the following JSON format:\n" +
            "\n" +
            "json\n" +
            "Copy\n" +
            "Edit\n" +
            "[\n" +
            "  {\n" +
            "    \"name\": \"<module name>\",\n" +
            "    \"logic\": \"<detailed logic description>\",\n" +
            "    \"interfaces\": \"<public interface code here>\"\n" +
            "  }\n" +
            "]\n" +
            "Avoid any additional explanation outside the JSON. Only return the JSON array.\""
}