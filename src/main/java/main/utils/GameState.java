package main.utils;

import main.Character;
import java.util.List;

public class GameState {
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