package main.controller;

import main.CharacterManager;
import main.RosterManager;
import main.Character;
import main.view.ConsoleView;
import java.util.List;
import main.utils.JsonDataManager;

public class GameController {
    private ConsoleView view;
    private CharacterManager characterManager;
    private RosterManager rosterManager;
    private boolean running;
    private final JsonDataManager jsonManager;

    public GameController(ConsoleView view, CharacterManager characterManager, RosterManager rosterManager, JsonDataManager jsonManager) {
        this.view = view;
        this.characterManager = characterManager;
        this.rosterManager = rosterManager;
        this.running = true;
        this.jsonManager = jsonManager;
    }

    public void run() {
        while (running) {
            int choice = view.displayMainMenu();
            handleMenuChoice(choice);
        }
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                handleRecruitment();
                break;
            case 2:
                handleRemoveCharacter();
                break;
            case 3:
                view.displayRoster(rosterManager.toString());
                break;
            case 4:
                handleTeamGeneration();
                break;
            case 5:
                handleLevelUp();
                break;
            case 6:
                running = false;
                jsonManager.saveGameState(rosterManager.getRoster(), rosterManager.getMaxSize());
                view.displayMessage("Game saved. Thank you for playing!");
                break;
            default:
                view.displayError("Invalid option selected");
        }
    }

    private void handleRecruitment() {
        if (rosterManager.isRosterFull()) {
            view.displayError("Roster is full! Cannot recruit more characters.");
            return;
        }
        characterManager.recruitmentSession();
    }

    private void handleRemoveCharacter() {
        if (rosterManager.getRosterSize() == 0) {
            view.displayError("Roster is empty!");
            return;
        }
        
        view.displayRoster(rosterManager.toString());
        String name = view.askCharacterName();
        
        // Trouver et supprimer le personnage
        Character characterToRemove = null;
        for (Character c : rosterManager.getRoster()) {
            if (c.getName().equalsIgnoreCase(name)) {
                characterToRemove = c;
                break;
            }
        }
        
        if (characterToRemove != null) {
            rosterManager.removeCharacter(characterToRemove);
            view.displayMessage("Character " + name + " has been removed from the roster.");
        } else {
            view.displayError("Character not found!");
        }
    }

    private void handleTeamGeneration() {
        if (rosterManager.getRosterSize() < 4) {
            view.displayError("Not enough characters in roster to form a team!");
            return;
        }

        int missionLevel = view.askMissionLevel();
        if (missionLevel < 1 || missionLevel > 4) {
            view.displayError("Invalid mission level! Must be between 1 and 4.");
            return;
        }

        List<Character> team = rosterManager.generateTeamForMission(missionLevel);
        if (team == null) {
            view.displayError("Not enough characters of appropriate level for this mission!");
        } else {
            view.displayTeam(team);
        }
    }

    private void handleLevelUp() {
        if (rosterManager.getRosterSize() == 0) {
            view.displayError("Roster is empty!");
            return;
        }

        view.displayRoster(rosterManager.toString());
        String name = view.askCharacterName();
        
        // Trouver le personnage
        Character characterToLevel = null;
        for (Character c : rosterManager.getRoster()) {
            if (c.getName().equalsIgnoreCase(name)) {
                characterToLevel = c;
                break;
            }
        }
        
        if (characterToLevel != null) {
            int newLevel = view.askNewLevel();
            if (newLevel >= 0 && newLevel <= 6) {
                characterToLevel.setLevel(newLevel);
                view.displayMessage("Character " + name + " is now level " + newLevel);
            } else {
                view.displayError("Invalid level! Must be between 0 and 6.");
            }
        } else {
            view.displayError("Character not found!");
        }
    }
} 