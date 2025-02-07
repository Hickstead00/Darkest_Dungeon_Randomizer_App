package main;

import java.util.ArrayList;
import java.util.List;
import main.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        List<String> liste = new ArrayList<>();
        liste.add("Reynauld");
        liste.add("Dismas");
        liste.add("Junia");
        liste.add("Paracelse");
        
        ConsoleView view = new ConsoleView();
        RosterManager rosterManager = new RosterManager();
        CharacterManager characterManager = new CharacterManager(liste, rosterManager, view);
        
        characterManager.recruitmentSession();
        view.displayRoster(rosterManager.toString());
    }
} 