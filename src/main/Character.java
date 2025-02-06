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


}
