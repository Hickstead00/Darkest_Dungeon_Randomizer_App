package main.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.Character;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDataManager {
    private static final String NAMES_FILE = "resources/names.json";
    private static final String SAVE_FILE = "resources/gamestate.json";
    private final Gson gson;

    public JsonDataManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<String> loadNames() {
        try (Reader reader = new FileReader(NAMES_FILE)) {
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error loading names: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveGameState(List<Character> roster, int maxSize) {
        GameState gameState = new GameState(roster, maxSize);
        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(gameState, writer);
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    public GameState loadGameState() {
        try (Reader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, GameState.class);
        } catch (IOException e) {
            System.err.println("No saved game found, starting fresh");
            return null;
        }
    }

    // Classe interne pour représenter l'état du jeu
    private static class GameState {
        private final List<Character> roster;
        private final int maxRosterSize;

        public GameState(List<Character> roster, int maxRosterSize) {
            this.roster = roster;
            this.maxRosterSize = maxRosterSize;
        }

        public List<Character> getRoster() {
            return roster;
        }

        public int getMaxRosterSize() {
            return maxRosterSize;
        }
    }
} 