package main;

import main.interfaces.CampingSkills;
import main.interfaces.CombatSkills;

import java.util.List;

public class Character {
    private CharacterClass characterCLass;
    private List<? extends CampingSkills> campingSkills;
    private List<? extends CombatSkills> combatSkills;
    private String name;
    private int level;
    private String sex;

    public Character(String name, CharacterClass characterCLass,) {
        this.name = name;
        this.characterCLass = characterCLass;
        this.level = 0;
    }

    public Character(String name, CharacterClass characterCLass, int level) {
        this.name = name;
        this.characterCLass = characterCLass;
        this.level = level;
    }

}
