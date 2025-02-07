package main.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import main.Character;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.*;
import main.interfaces.CampingSkills;
import main.interfaces.CombatSkills;
import java.lang.reflect.Type;



public class JsonDataManager {
    private static final String NAMES_FILE = "/names.json";
    private static final String SAVE_FILE = "resources/gamestate.json";
    private final Gson gson;

    public JsonDataManager() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(CampingSkills.class, new InterfaceAdapter<CampingSkills>())
            .registerTypeAdapter(CombatSkills.class, new InterfaceAdapter<CombatSkills>())
            .create();
        // Créer le dossier resources s'il n'existe pas
        new File("resources").mkdirs();
    }

    // Classe interne pour gérer la sérialisation des interfaces
    private static class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
        @Override
        public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("type", new JsonPrimitive(src.getClass().getName()));
            result.add("data", context.serialize(src));
            return result;
        }

        @Override
        public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            JsonElement element = jsonObject.get("data");
            try {
                return context.deserialize(element, Class.forName(type));
            } catch (ClassNotFoundException cnfe) {
                throw new JsonParseException("Unknown element type: " + type, cnfe);
            }
        }
    }

    public List<String> loadNames() {
        try (InputStream is = getClass().getResourceAsStream(NAMES_FILE);
             Reader reader = new InputStreamReader(is)) {
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
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists() || saveFile.length() == 0) {
            System.out.println("Aucune sauvegarde trouvée, démarrage d'une nouvelle partie");
            return null;
        }

        try (Reader reader = new FileReader(saveFile)) {
            GameState state = gson.fromJson(reader, GameState.class);
            if (state == null || state.getRoster() == null) {
                System.out.println("Fichier de sauvegarde corrompu, démarrage d'une nouvelle partie");
                return null;
            }
            return state;
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la sauvegarde: " + e.getMessage());
            // Supprimer le fichier corrompu
            saveFile.delete();
            return null;
        }
    }

    public void resetSaveFile() {
        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            if (!saveFile.delete()) {
                System.err.println("Impossible de supprimer le fichier de sauvegarde");
                // Tenter une suppression à la fermeture de la JVM
                saveFile.deleteOnExit();
            }
        }
        // S'assurer que le fichier est bien supprimé avant de continuer
        while (saveFile.exists()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
} 