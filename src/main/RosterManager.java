package main;

import java.util.ArrayList;
import java.util.List;

public class RosterManager {
    private int rostermaxSize = 9 ;
    private List<Character> roster;;

    public RosterManager() {
        this.roster = new ArrayList<Character>();
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
}
