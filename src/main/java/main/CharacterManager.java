package main;

import main.view.ConsoleView;
import java.util.*;

public class CharacterManager {
    private List<String> availableNames;
    private Set<String> usedNames;  // Pour suivre les noms déjà utilisés
    private RosterManager rosterManager;
    private Random random;
    private ConsoleView view;

    public CharacterManager(List<String> availableNames, RosterManager rosterManager, ConsoleView view) {
        this.availableNames = new ArrayList<>(availableNames);  // Copie de la liste originale
        this.usedNames = new HashSet<>();  // Initialisation du set des noms utilisés
        this.rosterManager = rosterManager;
        this.random = new Random();
        this.view = view;
        
        // Ajouter les noms déjà utilisés dans le roster existant
        for (Character c : rosterManager.getRoster()) {
            usedNames.add(c.getName());
        }
    }

    public void recruitmentSession() {
        if (rosterManager.isRosterFull()) {
            view.displayError("Le roster est plein !");
            return;
        }

        int numberOfRecruits = view.askNumberOfRecruits();
        int placesDisponibles = rosterManager.getMaxSize() - rosterManager.getRosterSize();
        
        if (numberOfRecruits > placesDisponibles) {
            view.displayMessage("Nombre ajusté à " + placesDisponibles + " places disponibles.");
            numberOfRecruits = placesDisponibles;
        }

        view.displayAvailableClasses(CharacterClass.values());
        List<CharacterClass> selectedClasses = view.getSelectedClasses(CharacterClass.values());
        
        if (selectedClasses.isEmpty()) {
            view.displayError("Aucune classe valide sélectionnée !");
            return;
        }

        // Créer la liste des classes à utiliser
        List<CharacterClass> classesToUse = new ArrayList<>();
        if (selectedClasses.size() == 1) {
            // Si une seule classe sélectionnée, la répéter
            for (int i = 0; i < numberOfRecruits; i++) {
                classesToUse.add(selectedClasses.get(0));
            }
        } else {
            // Sinon, utiliser des classes différentes
            if (numberOfRecruits > selectedClasses.size()) {
                view.displayMessage("Le nombre de recrues a été ajusté au nombre de classes différentes sélectionnées.");
                numberOfRecruits = selectedClasses.size();
            }
            // Prendre numberOfRecruits classes différentes
            Collections.shuffle(selectedClasses);
            classesToUse = selectedClasses.subList(0, numberOfRecruits);
        }

        // Créer les personnages
        for (CharacterClass characterClass : classesToUse) {
            Character newRecruit = new Character(characterClass);
            giveName(newRecruit);
            rosterManager.addCharacter(newRecruit);
            view.displayNewRecruit(newRecruit);
        }
    }

    public Character createCharacter(List<CharacterClass> availableClasses) {
        if (availableClasses.isEmpty()) {
            throw new IllegalArgumentException("La liste des classes disponibles ne peut pas être vide");
        }
        CharacterClass randomClass = availableClasses.get(random.nextInt(availableClasses.size()));
        Character character = new Character(randomClass);
        giveName(character);
        rosterManager.addCharacter(character);
        return character;
    }

    private void giveName(Character character) {
        List<String> availableNamesLeft = new ArrayList<>(availableNames);
        availableNamesLeft.removeAll(usedNames);

        if (availableNamesLeft.isEmpty()) {
            // Si tous les noms sont utilisés, créer un nom unique
            String newName;
            do {
                newName = "Hero_" + random.nextInt(1000);
            } while (usedNames.contains(newName));
            character.setName(newName);
            usedNames.add(newName);
            return;
        }

        String name = availableNamesLeft.get(random.nextInt(availableNamesLeft.size()));
        character.setName(name);
        usedNames.add(name);
    }
}
