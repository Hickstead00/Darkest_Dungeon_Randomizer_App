package main;


import java.util.List;

import main.utils.GameState;
import main.view.ConsoleView;
import main.controller.GameController;
import main.utils.JsonDataManager;

public class Main {
    private static Thread shutdownHook;
    
    public static void main(String[] args) {
        JsonDataManager jsonManager = new JsonDataManager();
        
        // Charger les noms depuis le fichier JSON
        List<String> availableNames = jsonManager.loadNames();
        
        // Initialiser les managers
        ConsoleView view = new ConsoleView();
        final RosterManager rosterManager;
        
        // Charger l'état sauvegardé s'il existe
        GameState savedState = jsonManager.loadGameState();
        if (savedState != null) {
            rosterManager = new RosterManager(savedState.getRoster(), savedState.getMaxRosterSize());
        } else {
            rosterManager = new RosterManager();
        }
        
        CharacterManager characterManager = new CharacterManager(availableNames, rosterManager, view);
        GameController controller = new GameController(view, characterManager, rosterManager, jsonManager);
        
        // Ajouter un Runtime shutdown hook pour sauvegarder avant de quitter
        shutdownHook = new Thread(() -> 
            jsonManager.saveGameState(rosterManager.getRoster(), rosterManager.getMaxSize())
        );
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        
        controller.run();
    }

    public static void removeShutdownHook() {
        if (shutdownHook != null) {
            Runtime.getRuntime().removeShutdownHook(shutdownHook);
        }
    }
} 