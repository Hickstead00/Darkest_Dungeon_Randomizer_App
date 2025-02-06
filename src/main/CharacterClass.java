package main;

import main.enums.*;
import main.interfaces.CampingSkills;
import main.interfaces.CombatSkills;
import java.util.Arrays;
import java.util.List;

public enum CharacterClass {
    CRUSADER(Arrays.asList(CrusaderCombatSkills.values()), Arrays.asList(CrusaderCampingSkills.values())),
    ANTIQUARIAN(Arrays.asList(AntiquarianCombatSkills.values()), Arrays.asList(AntiquarianCampingSkills.values())),
    ARBALEST(Arrays.asList(ArbalestCombatSkills.values()), Arrays.asList(ArbalestCampingSkills.values())),
    BOUNTY_HUNTER(Arrays.asList(BountyHunterCombatSkills.values()), Arrays.asList(BountyHunterCampingSkills.values())),
    GRAVE_ROBBER(Arrays.asList(GraveRobberCombatSkills.values()), Arrays.asList(GraveRobberCampingSkills.values())),
    HELLION(Arrays.asList(HellionCombatSkills.values()), Arrays.asList(HellionCampingSkills.values())),
    HIGHWAYMAN(Arrays.asList(HighwaymanCombatSkills.values()), Arrays.asList(HighwaymanCampingSkills.values())),
    HOUNDMASTER(Arrays.asList(HoundmasterCombatSkills.values()), Arrays.asList(HoundmasterCampingSkills.values())),
    JESTER(Arrays.asList(JesterCombatSkills.values()), Arrays.asList(JesterCampingSkills.values())),
    LEPER(Arrays.asList(LepperCombatSkills.values()), Arrays.asList(LepperCampingSkills.values())),
    MAN_AT_ARMS(Arrays.asList(ManAtArmsCombatSkills.values()), Arrays.asList(ManAtArmsCampingSkills.values())),
    MUSKETEER(Arrays.asList(MusketeerCombatSkills.values()), Arrays.asList(MusketeerCampingSkills.values())),
    OCCULTIST(Arrays.asList(OccultistCombatSkills.values()), Arrays.asList(OccultistCampingSkills.values())),
    PLAGUE_DOCTOR(Arrays.asList(PlagueDocterCombatSkills.values()), Arrays.asList(PlagueDocterCampingSkills.values())),
    VESTAL(Arrays.asList(VestalCombatSkills.values()), Arrays.asList(VestalCampingSkills.values())),
    FLAGELLANT(Arrays.asList(FlagellantCombatSkills.values()), Arrays.asList(FlagellantCampingSkills.values())),
    SHIELDBREAKER(Arrays.asList(ShieldbreakerCombatSkills.values()), Arrays.asList(ShieldbreakerCampingSkills.values())),
    ABOMINATION(Arrays.asList(AbominationCombatSkills.values()), Arrays.asList(AbominationCampingSkills.values())),;

    private final List<? extends CombatSkills> cskills;
    private final List<? extends CampingSkills> caskills;

    CharacterClass(List<? extends CombatSkills> cskills, List<? extends CampingSkills> caskills) {
        this.cskills = cskills;
        this.caskills = caskills;
    }

    public List<? extends CombatSkills> getCombatSkills() {
        return cskills;
    }

    public List<? extends CampingSkills> getCampingSkills() {
        return caskills;
    }
}


