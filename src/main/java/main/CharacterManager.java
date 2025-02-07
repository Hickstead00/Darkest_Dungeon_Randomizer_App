package main;

import main.view.ConsoleView;
import java.util.*;

public class CharacterManager {
    private List<String> availableNames;
    private RosterManager rosterManager;
    private Random random;
    private ConsoleView view;

    public CharacterManager(List<String> availableNames, RosterManager rosterManager, ConsoleView view) {
        this.availableNames = availableNames;
        this.rosterManager = rosterManager;
        this.random = new Random();
        this.view = view;
    }

    public void recruitmentSession() {
        int numberOfRecruits = view.askNumberOfRecruits();

        for (int i = 0; i < numberOfRecruits; i++) {
            System.out.println("\nRecrutement #" + (i + 1));
            view.displayAvailableClasses(CharacterClass.values());
            
            List<CharacterClass> selectedClasses = view.getSelectedClasses(CharacterClass.values());
            Character newRecruit = createCharacter(selectedClasses);
            
            view.displayNewRecruit(newRecruit);
        }
    }

    public Character createCharacter(List<CharacterClass> availableClasses) {
        CharacterClass randomClass = availableClasses.get(random.nextInt(availableClasses.size()));
        Character character = new Character(randomClass);
        giveName(character);
        rosterManager.addCharacter(character);
        return character;
    }

    private void giveName(Character character) {
        String name = availableNames.get(random.nextInt(availableNames.size()));
        character.setName(name);
    }
}
