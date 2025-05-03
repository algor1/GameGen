class Prompts {

    val gameDescriptionEvaluationPrompt =
        "Evaluate the quality of the game rules description provided in last message\n" +
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

    val gameDescriptionImprovePrompt = "\"I agree with your suggestions. Apply them and show me the fixed result rules."

    val gameDescriptionCreatePrompt =
        "Please help me define clear and concise game rules. The rules should include the game genre, goal, main mechanics, win/lose conditions, player controls, and any special features. Present it in a structured and readable format."

    val gameVisualDescriptionCreatePrompt =
        "Describe the visual design of the game in great detail so that a game artist can create all necessary visual assets.\n" +
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

    val gameVisualDescriptionEvaluationPrompt =
        "Evaluate the quality and completeness of the game's visual description.\n" +
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

    val gameVisualDescriptionImprovePrompt =
        "Based on the previous evaluation and feedback, improve the game’s visual description.\n" +
                "\n" +
                "Add all missing details mentioned in the evaluation\n" +
                "\n" +
                "Expand and clarify any vague or incomplete parts\n" +
                "\n" +
                "Ensure that every visual element of the game is fully described, including characters, enemies, objects, environments, UI elements, animations, effects, and maps\n" +
                "\n" +
                "Clearly describe the overall art style, including mood, colors, shapes, and visual references if needed\n" +
                "\n" +
                "The final result should be detailed enough that a game artist can create all assets without needing to ask for clarification."


    val objectsDescriptionCreatePrompt = "I have a complete description of the game rules and visuals.\n" +
            "Based on that, generate a comprehensive list of all game objects (classes) that need to be created for the game in Unity.\n" +
            "For each object/class, include:\n" +
            "A name\n" +
            "A detailed description of its purpose\n" +
            "A list of properties/fields it should contain\n" +
            "A list of methods/actions it should perform\n" +
            "How it interacts with other objects in the game\n" +
            "Be sure to include:\n" +
            "Visible objects: player, enemies, collectibles, obstacles, background layers, visual effects\n" +
            "UI elements: buttons, menus, score displays, health bars, game over screens, pause menu, and any other HUD components\n" +
            "Input handling: include all relevant control schemes (keyboard, mouse, touch, etc.) and create objects/classes responsible for processing input\n" +
            "Logic and systems: game manager, event system, state machine, collision detection, level management, audio manager, camera controller, background/map scroller\n" +
            "Any systems for managing game states, scene transitions, or multiplayer components (if applicable)\n" +
            "All objects should be instantiated programmatically via scripts, and assume that all visual assets (sprites, prefabs, UI elements) will be created and assigned separately.\n" +
            "Do not write any code — only the full structural and behavioral description of each object."

    val objectsDescriptionEvaluatePrompt =
        "Evaluate the completeness and quality of the object (class) descriptions for a Unity game project.\n" +
                "Start your response with a score from 0 to 100, where:\n" +
                "0 means the descriptions are very incomplete and unusable for development\n" +
                "100 means the descriptions are fully complete and sufficient to start coding and building the game\n" +
                "Format of score: ```Evaluation: <score>```\n" +
                "Then analyze the following aspects in detail:\n" +
                "Object Coverage:\n" +
                "Are all necessary game objects and systems included (visual, logical, UI, input, background, audio, etc.)?\n" +
                "Are there any missing components or systems that should be defined?\n" +
                "Action Completeness:\n" +
                "Are the actions and behaviors of each object clearly and fully described?\n" +
                "Is it clear what each object does during gameplay?\n" +
                "Interaction Clarity:\n" +
                "Are interactions between objects clearly explained?\n" +
                "Can we understand how objects communicate and affect each other during gameplay?\n" +
                "Property Definitions:\n" +
                "Are all important properties/fields of each object described?\n" +
                "Is it clear what data each class holds?\n" +
                "Implementation Readiness:\n" +
                "Is the description complete enough for a developer to begin implementing the game in Unity?\n" +
                "Would a developer be able to create scripts, systems, and game logic based solely on this document?\n" +
                "Provide specific suggestions for what to improve, clarify, or expand to make the descriptions fully implementation-ready."

    val objectsDescriptionImprovePrompt =
        "Based on the evaluation and feedback received about the game object descriptions, improve and update the list of game objects (classes) accordingly.\n" +
                "Fix all issues, add any missing objects or systems\n" +
                "Expand or clarify any vague or incomplete object descriptions\n" +
                "Make sure each object includes:\n" +
                "A clear and specific name\n" +
                "A detailed description of its purpose and role in the game\n" +
                "A complete list of properties/fields (with types and purpose)\n" +
                "A complete list of methods/actions (with their responsibilities)\n" +
                "A clear explanation of interactions with other game objects or systems\n" +
                "Ensure that the final result:\n" +
                "Covers all aspects of the game, including visual objects, logic systems, input, UI, camera, background, audio, and game state\n" +
                "Is structured and detailed enough for a developer to begin implementing all systems and gameplay logic in Unity using scripts\n" +
                "Assumes that all objects will be instantiated programmatically, and that all visual assets (sprites, prefabs, UI elements) will be created separately\n" +
                "Return a fully updated and improved object list, ready for implementation."

    val objectInterfacesCreatePrompt =
        "Based on the detailed descriptions of the game objects (classes) created for a Unity project, generate C# interfaces (interface) for each object.\n" +
                "For each interface, follow these rules:\n" +
                "The interface must fully reflect the logic and behavior of the corresponding object\n" +
                "Include all methods/actions that the object is supposed to perform\n" +
                "Include all important properties/fields that the object needs to store and expose\n" +
                "Only define signatures — no implementation details\n" +
                "Use C# syntax, matching Unity development practices\n" +
                "Assume that all objects and UI elements will be instantiated programmatically via scripts, and all visual assets (sprites, prefabs, UI components) will be prepared separately\n" +
                "Naming conventions:\n" +
                "Interface names should start with a capital I followed by the object name (e.g., IPlayer, IEnemy, IGameManager)\n" +
                "Use properties instead of public fields whenever possible\n" +
                "Output the interface definitions grouped logically if needed (e.g., player-related, UI-related, system-related).\n" +
                "Do not include any unrelated code. Only clean and properly structured C# interfaces ready for use in Unity development."

    val objectInterfacesEvaluatePrompt =
        "Evaluate the completeness and quality of the generated C# interfaces for a Unity game project.\n" +
                "Start your response with a score from 0 to 100, where:\n" +
                "0 means the interfaces are very incomplete and unusable\n" +
                "100 means the interfaces are fully complete, correctly represent the game logic, and are ready for implementation\n" +
                "Format of score: ```Evaluation: <score> ``` \n" +
                "Then provide detailed feedback based on the following points:\n" +
                "Consistency with Object Descriptions:\n" +
                "Do the interfaces correctly reflect all described actions (methods) and data (properties/fields) for each object?\n" +
                "Is anything from the original object descriptions missing in the interfaces?\n" +
                "Method and Property Completeness:\n" +
                "Are all necessary methods and properties included?\n" +
                "Are their signatures clear and correctly defined?\n" +
                "Logical Structure:\n" +
                "Are the responsibilities of each interface logically grouped and clearly separated?\n" +
                "Are there any interfaces that are overloaded with too many responsibilities and should be split?\n" +
                "Implementation Readiness:\n" +
                "Based on the interfaces alone, is it possible to start implementing all game objects, systems, and UI elements?\n" +
                "Would a Unity developer have enough information from these interfaces to build a working version of the game’s logic?\n" +
                "Suggest specific improvements if anything needs to be added, corrected, clarified, or better structured to ensure the interfaces fully support the game's development."

    val objectInterfacesImprovePrompt =
        "Based on the latest evaluation and feedback, do not simply fix the reported mistakes.\n" +
                "Actively improve the C# interfaces for the Unity game project by:\n" +
                "Adding any missing methods, properties, or interfaces that are necessary even if they were not explicitly pointed out\n" +
                "Refactoring any interfaces that have too many responsibilities (apply interface segregation)\n" +
                "Introducing base interfaces where multiple objects share common behaviors (e.g., IMovable, IDamageable, ICollectible)\n" +
                "Ensuring that every class described can be fully implemented just by using its interface\n" +
                "Enhancing the flexibility and scalability of the system (following SOLID principles where applicable)\n" +
                "Considering real-world Unity development practices (e.g., lifecycle events, input handling separation, dependency injection patterns)\n" +
                "Fully covering all described behaviors and data\n" +
                "Building a clean, maintainable, and expandable interface architecture\n" +
                "Output the complete corrected and improved set of C# interfaces, organized logically, and ready for immediate use in development."

    val saveInterfacesPrompt =
        "Please save each of the following C# interface definitions into a separate .cs file using FileSaveTool.\n" +
                "The filename for each file should exactly match the interface name (e.g., IPlayer.cs, IGameManager.cs)\n" +
                "Each file should contain only one interface in valid C# syntax\n" +
                "Include necessary using statements if required (like using UnityEngine;)\n" +
                "Here is the full list of interfaces:\n"

    val interfacesTestsCreatePrompt =
        "Based on the provided C# interfaces for a Unity project, generate a comprehensive set of unit tests for each interface.\n" +
                "For each interface:\n" +
                "Create a separate test class named [InterfaceName]Tests (e.g., IPlayerTests, IGameManagerTests)\n" +
                "Use NUnit framework for testing ([TestFixture], [Test], etc.)\n" +
                "Assume the interfaces will be implemented via mock classes or test doubles where necessary\n" +
                "Create mock implementations to verify behavior and logic defined by the interface\n" +
                "Write tests for all public properties (get/set), and all method signatures to ensure they behave as expected\n" +
                "Cover normal usage, boundary cases, and invalid input scenarios if applicable\n" +
                "Tests should validate that the contract of each interface is enforced\n" +
                "Use clean and idiomatic C# and Unity test conventions.\n" +
                "Do not include production code implementations — only test classes and test methods.\n" +
                "Group the tests logically if needed."
    val interfacesTestsEvaluatePrompt =
        "Evaluate the quality and completeness of the unit tests written for the given C# interfaces in a Unity project.\n" +
                "Provide a score from 0 to 100, where:\n" +
                "0 means the tests are completely inadequate\n" +
                "100 means the tests are perfect and nothing needs to be improved\n" +
                "Format of score: ```Evaluation: <score> ``` \n" +
                "In your evaluation, consider the following criteria:\n" +
                "Do the tests cover all methods and properties in the interfaces?\n" +
                "Are valid, invalid, and edge cases tested where appropriate?\n" +
                "Are the tests clear, maintainable, and logically organized?\n" +
                "Do the tests help ensure the interface contract is fully validated?\n" +
                "Are the tests using appropriate mock objects or test doubles where needed?\n" +
                "After the score, list any missing test cases, redundant or low-value tests, or design improvements that should be made.\n" +
                "The goal is to ensure the tests provide full confidence that any implementation of the interface will behave correctly in production."
    val interfacesTestsImprovePrompt =
        "Based on the feedback and score given in the previous evaluation of the unit tests for the C# interfaces, improve and extend the test suite.\n" +
                "For each test class:\n" +
                "Add all missing test cases mentioned in the review\n" +
                "Correct any flaws, incomplete validations, or unclear logic\n" +
                "Remove or refactor redundant or low-value tests\n" +
                "Make sure to include valid, invalid, and edge case scenarios\n" +
                "Ensure full method and property coverage as required by the interface contract\n" +
                "Maintain proper use of NUnit and mock objects/test doubles when necessary\n" +
                "Output clean, corrected C# code for the updated test classes.\n" +
                "Only include the improved test classes — no implementation code or unrelated comments."

    val implementationCreatePrompt = "Based on the detailed descriptions of the game objects, the corresponding C# interfaces, and the unit tests, implement the concrete classes for each game object in a Unity project.\n" +
            "For each class:\n" +
            "The class must fully implement the corresponding interface\n" +
            "Include all necessary fields, properties, and methods\n" +
            "Ensure all logic described in the original object specification is implemented\n" +
            "The implementation must be compatible with the existing unit tests (they should pass without modification)\n" +
            "Follow Unity C# development best practices (e.g., MonoBehaviour inheritance when appropriate)\n" +
            "Do not include boilerplate code (like Start or Update) unless needed\n" +
            "Output clean, idiomatic C# class code only — one class per block\n" +
            "Do not include test code, explanations, or unrelated text"
    val implementationEvaluatePrompt = "Evaluate the quality and completeness of the class implementations based on the original object descriptions, interfaces, and unit tests.\n" +
            "Provide a score from 0 to 100, where:\n" +
            "0 = the implementation is unusable or missing critical functionality\n" +
            "100 = the implementation is complete, correct, and production-ready\n" +
            "Format of score: ```Evaluation: <score> ``` \n" +
            "In your evaluation, consider:\n" +
            "Does each class fully implement its interface?\n" +
            "Does the logic match the original object descriptions?\n" +
            "Do all required behaviors and edge cases appear to be handled?\n" +
            "Is the code clean, maintainable, and aligned with Unity C# standards?\n" +
            "Would the implementation likely pass all defined unit tests?\n" +
            "After the score, provide detailed comments on what is missing, incorrect, redundant, or unclear. Suggest any improvements or design corrections."
    val implementationImprovePrompt = "Based on the evaluation feedback and score given for the class implementations, improve and correct each class accordingly.\n" +
            "For each class:\n" +
            "Fix all identified issues and implement all missing functionality\n" +
            "Align the behavior strictly with the object description and interface\n" +
            "Ensure that the implementation would now pass all unit tests\n" +
            "Apply all suggestions for code clarity, maintainability, or design improvements\n" +
            "Output only the revised C# class implementations — no explanations or additional text.\n" +
            "Group related classes logically if needed (e.g., UI, gameplay, systems)."
}