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

    public int askNumberOfRecruits() {
        System.out.println("How many characters do you wich to recruit ?");
        return scanner.nextInt();
    }

    public void displayAvailableClasses(CharacterClass[] classes) {
        System.out.println("Avalaible classes :");
        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i]);
        }
    }

    public List<CharacterClass> getSelectedClasses(CharacterClass[] allClasses) {
        System.out.println("Enter the number of the avalaible classes (separated by a space) :");
        scanner.nextLine(); // consume newline
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        List<CharacterClass> selectedClasses = new ArrayList<>();
        
        for (String number : numbers) {
            int index = Integer.parseInt(number) - 1;
            if (index >= 0 && index < allClasses.length) {
                selectedClasses.add(allClasses[index]);
            }
        }
        return selectedClasses;
    }

    public void displayNewRecruit(Character character) {
        System.out.println("\nNew recruited character:");
        System.out.println(character);
    }

    public void displayRoster(String rosterString) {
        System.out.println("\nFinal roster :");
        System.out.println(rosterString);
    }
} 