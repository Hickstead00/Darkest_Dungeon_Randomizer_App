package main;

import main.interfaces.CampingSkills;
import main.interfaces.CombatSkills;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Character {
    private CharacterClass characterClass;
    private List<? extends CampingSkills> campingSkills;
    private List<? extends CombatSkills> combatSkills;
    private String name;
    private int level;
    private String sex;
    private boolean isAvailable;
    private String unavailabilityReason;

    public Character(CharacterClass characterClass) {
        this.characterClass = characterClass;
        this.level = 0;
        this.combatSkills = assignCombatSkills(characterClass);
        this.campingSkills = assignCampingSkills(characterClass);
        this.isAvailable = true;
        this.unavailabilityReason = "";
    }

    public Character(String name, CharacterClass characterClass, int level) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
        this.combatSkills = assignCombatSkills(characterClass);
        this.campingSkills = assignCampingSkills(characterClass);
    }

    private List<? extends CombatSkills> assignCombatSkills(CharacterClass characterClass) {
        List<? extends CombatSkills> allCombatSkills = characterClass.getCombatSkills();
        List<CombatSkills> shuffledSkills = new ArrayList<>(allCombatSkills);
        Collections.shuffle(shuffledSkills);
        return shuffledSkills.subList(0, 4);
    }

    private List<? extends CampingSkills> assignCampingSkills(CharacterClass characterClass) {
        List<? extends CampingSkills> allCampingSkills = characterClass.getCampingSkills();
        List<CampingSkills> shuffledSkills = new ArrayList<>(allCampingSkills);
        Collections.shuffle(shuffledSkills);
        return shuffledSkills.subList(0, 4);
    }

    // Getters et Setters
    public CharacterClass getCharacterClass() {
        return characterClass;
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

    public void setName(String name) {
        this.name = name;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getUnavailabilityReason() {
        return unavailabilityReason;
    }

    public void setAvailability(boolean available, String reason) {
        this.isAvailable = available;
        this.unavailabilityReason = reason;
    }
}
