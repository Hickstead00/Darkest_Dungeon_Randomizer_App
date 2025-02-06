package main;

import main.enums.*;
import main.interfaces.CombatSkills;
import java.util.Arrays;
import java.util.List;

public enum CharacterClass {
    CRUSADER(Arrays.asList(CrusaderCombatSkills.values())),
    ANTIQUARIAN(Arrays.asList(AntiquarianCombatSkills.values())),
    ARBALEST(Arrays.asList(ArbalestCombatSkills.values())),
    BOUNTY_HUNTER(Arrays.asList(BountyHunterCombatSkills.values())),
    GRAVE_ROBBER(Arrays.asList(GraveRobberCombatSkills.values())),
    HELLION(Arrays.asList(HellionCombatSkills.values())),
    HIGHWAYMAN(Arrays.asList(HighwaymanCombatSkills.values())),
    HOUNDMASTER(Arrays.asList(HoundmasterCombatSkills.values())),
    JESTER(Arrays.asList(JesterCombatSkills.values())),
    LEPER(Arrays.asList(LepperCombatSkills.values())),
    MAN_AT_ARMS(Arrays.asList(ManAtArmsCombatSkills.values())),
    MUSKETEER(Arrays.asList(MusketeerCombatSkills.values())),
    OCCULTIST(Arrays.asList(OccultistCombatSkills.values())),
    PLAGUE_DOCTOR(Arrays.asList(PlagueDocterCombatSkills.values())),
    VESTAL(Arrays.asList(VestalCombatSkills.values())),
    FLAGELLANT(Arrays.asList(FlagellantCombatSkills.values())),
    SHIELDBREAKER(Arrays.asList(ShieldbreakerCombatSkills.values())),
    ABOMINATION(Arrays.asList(AbominationCombatSkills.values()));

    private final List<? extends CombatSkills> skills;

    CharacterClass(List<? extends CombatSkills> skills) {
        this.skills = skills;
    }

    public List<? extends CombatSkills> getSkills() {
        return skills;
    }
}


