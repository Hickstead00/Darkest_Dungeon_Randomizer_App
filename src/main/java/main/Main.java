package main;

import java.util.ArrayList;
import java.util.List;
import main.view.ConsoleView;
import main.controller.GameController;
import main.utils.JsonDataManager;
import main.Character;

public class Main {
    public static void main(String[] args) {
        JsonDataManager jsonManager = new JsonDataManager();
        
        // Charger les noms depuis le fichier JSON
        List<String> availableNames = jsonManager.loadNames();
        
        // Initialiser les managers
        ConsoleView view = new ConsoleView();
        RosterManager rosterManager = new RosterManager();
        
        // Charger l'état sauvegardé s'il existe
        GameState savedState = jsonManager.loadGameState();
        if (savedState != null) {
            rosterManager = new RosterManager(savedState.getRoster(), savedState.getMaxRosterSize());
        }
        
        CharacterManager characterManager = new CharacterManager(availableNames, rosterManager, view);
        GameController controller = new GameController(view, characterManager, rosterManager, jsonManager);
        
        // Ajouter un Runtime shutdown hook pour sauvegarder avant de quitter
        Runtime.getRuntime().addShutdownHook(new Thread(() -> 
            jsonManager.saveGameState(rosterManager.getRoster(), rosterManager.getMaxSize())
        ));
        
        controller.run();
    }
} 