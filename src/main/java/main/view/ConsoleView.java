package main.view;

import main.CharacterClass;
import main.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("\n=== DARKEST DUNGEON ROSTER MANAGER ===");
        System.out.println("1. Recruit new characters");
        System.out.println("2. Remove dead character");
        System.out.println("3. List roster");
        System.out.println("4. Generate mission team");
        System.out.println("5. Level up character");
        System.out.println("6. Quit");
        System.out.print("\nChoose an option: ");
        return scanner.nextInt();
    }

    public int askNumberOfRecruits() {
        System.out.println("How many characters do you wish to recruit?");
        return scanner.nextInt();
    }

    public void displayAvailableClasses(CharacterClass[] classes) {
        System.out.println("\nAvailable classes:");
        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i]);
        }
    }

    public List<CharacterClass> getSelectedClasses(CharacterClass[] allClasses) {
        System.out.println("Enter the numbers of the available classes (separated by a space):");
        scanner.nextLine(); // consume newline
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        List<CharacterClass> selectedClasses = new ArrayList<>();
        
        for (String number : numbers) {
            try {
                int index = Integer.parseInt(number.trim()) - 1;
                if (index >= 0 && index < allClasses.length) {
                    selectedClasses.add(allClasses[index]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + number + " - skipping...");
            }
        }
        return selectedClasses;
    }

    public int askMissionLevel() {
        System.out.println("Enter mission level (1-4):");
        return scanner.nextInt();
    }

    public String askCharacterName() {
        System.out.println("Enter character name:");
        scanner.nextLine(); // consume newline
        return scanner.nextLine();
    }

    public int askNewLevel() {
        System.out.println("Enter new level (0-6):");
        return scanner.nextInt();
    }

    public void displayNewRecruit(Character character) {
        System.out.println("\nNew recruited character:");
        displayCharacterDetails(character);
    }

    public void displayRoster(String rosterString) {
        System.out.println("\nCurrent roster:");
        System.out.println(rosterString);
    }

    public void displayCharacterDetails(Character character) {
        System.out.println("Name: " + character.getName());
        System.out.println("Class: " + character.getCharacterClass());
        System.out.println("Level: " + character.getLevel());
        System.out.println("Combat Skills: " + character.getCombatSkills());
        System.out.println("Camping Skills: " + character.getCampingSkills());
    }

    public void displayTeam(List<Character> team) {
        System.out.println("\nSelected team for mission:");
        for (Character c : team) {
            displayCharacterDetails(c);
            System.out.println("-------------------");
        }
    }

    public void displayError(String message) {
        System.out.println("\nError: " + message);
    }

    public void displayMessage(String message) {
        System.out.println("\n" + message);
    }
} 