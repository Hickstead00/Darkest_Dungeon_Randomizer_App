package main;

import java.util.List;

public class RosterManager {
    private int rosterSize;
    private List<Character> roster;
    private String saveFilePath;

    public int getRosterSize() {
        return rosterSize;
    }

    public void addCharacter(Character character) {
        roster.add(character);
    }
}
