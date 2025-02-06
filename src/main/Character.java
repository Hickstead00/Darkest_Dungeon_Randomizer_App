package main;

import main.interfaces.CampingSkills;
import main.interfaces.CombatSkills;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Character {
    private CharacterClass characterCLass;
    private List<? extends CampingSkills> campingSkills;
    private List<? extends CombatSkills> combatSkills;
    private String name;
    private int level;
    private String sex;

    public Character(String name, CharacterClass characterCLass) {
        this.name = name;
        this.characterCLass = characterCLass;
        this.level = 0;
        this.combatSkills = assignCombatSkills(characterCLass);
    }

    public Character(String name, CharacterClass characterCLass, int level) {
        this.name = name;
        this.characterCLass = characterCLass;
        this.level = level;
    }

    private List<? extends CombatSkills> assignCombatSkills(CharacterClass characterClass) {
        List<? extends CombatSkills> allCombatSkills = characterClass.getSkills();
        List<CombatSkills> shuffledSkills = new ArrayList<>(allCombatSkills);
        Collections.shuffle(shuffledSkills);
        return shuffledSkills.subList(0, 4);
    }

    // Getters et Setters
    public CharacterClass getCharacterClass() {
        return characterCLass;
    }

    public List<? extends CampingSkills> getCampingSkills() {
        return campingSkills;
    }

    public List<? extends CombatSkills> getCombatSkills() {
        return combatSkills;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getSex() {
        return sex;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
