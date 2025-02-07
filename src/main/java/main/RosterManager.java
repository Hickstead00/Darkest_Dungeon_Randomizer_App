package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RosterManager {
    private int rostermaxSize = 9;
    private List<Character> roster;

    public RosterManager() {
        this.roster = new ArrayList<Character>();
    }

    // Nouveau constructeur pour charger un état sauvegardé
    public RosterManager(List<Character> savedRoster, int maxSize) {
        this.roster = savedRoster;
        this.rostermaxSize = maxSize;
    }

    // Getter pour la taille maximale
    public int getMaxSize() {
        return rostermaxSize;
    }

    public int getRosterSize() {
        return roster.size();
    }

    public List<Character> getRoster() {
        return roster;
    }

    public void addCharacter(Character character) {
        if (roster.size() < rostermaxSize) {
            roster.add(character);
        }
    }

    public void removeCharacter(Character character) {
        roster.remove(character);
    }

    public boolean isRosterFull(){
        return roster.size() == rostermaxSize;
    }

    public String toString() {
        if (roster.isEmpty()) {
            return "Le roster est vide.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== ROSTER ===\n");
        for (Character c : roster) {
            sb.append(String.format("Nom: %s - Classe: %s - Niveau: %d\n", 
                c.getName(), 
                c.getCharacterClass(), 
                c.getLevel()));
            sb.append("Combat Skills: ");
            c.getCombatSkills().forEach(skill -> sb.append(skill + ", "));
            sb.append("\nCamping Skills: ");
            c.getCampingSkills().forEach(skill -> sb.append(skill + ", "));
            sb.append("\n\n");
        }
        return sb.toString();
    }

    public List<Character> generateTeamForMission(int missionLevel) {
        List<Character> availableCharacters = new ArrayList<>();
        
        // Filtrer les personnages selon le niveau de mission
        for (Character c : roster) {
            int charLevel = c.getLevel();
            boolean isEligible = switch (missionLevel) {
                case 1 -> charLevel >= 0 && charLevel <= 2;
                case 2 -> charLevel >= 3 && charLevel <= 4;
                case 3 -> charLevel >= 4 && charLevel <= 5;
                case 4 -> charLevel == 6;
                default -> false;
            };
            
            if (isEligible) {
                availableCharacters.add(c);
            }
        }

        // Vérifier s'il y a assez de personnages éligibles
        if (availableCharacters.size() < 4) {
            return null;
        }

        // Mélanger la liste et prendre les 4 premiers
        Collections.shuffle(availableCharacters);
        return availableCharacters.subList(0, 4);
    }
}
