package main;

import java.util.List;

public class CharacterManager {
    private List<String> availableNames;
    private RosterManager rosterManager;

    public CharacterManager(List<String> availableNames, RosterManager rosterManager) {
        this.availableNames = availableNames;
        this.rosterManager = rosterManager;
    }

    public Character createCharacter (CharacterClass charClass) {
        Character character = new Character(charClass);
        giveName(character);
        rosterManager.addCharacter(character);
        return character;
    }

    private void giveName(Character character) {
        String name = availableNames.get((int) (Math.random() * availableNames.size()));
        character.setName(name);
    }




}
