package main;

public class Main {
    public static void main(String[] args) {
        // Créer quelques personnages
        Character crusader = new Character("Reynauld", CharacterClass.CRUSADER);
        Character vestal = new Character("Junia", CharacterClass.VESTAL);
        Character plague = new Character("Paracelse", CharacterClass.PLAGUE_DOCTOR);

        // Afficher les informations pour chaque personnage
        System.out.println("=== " + crusader.getName() + " le Crusader ===");
        System.out.println("Niveau: " + crusader.getLevel());
        System.out.println("Combat skills: ");
        crusader.getCombatSkills().forEach(skill -> System.out.println("- " + skill));
        System.out.println();

        System.out.println("=== " + vestal.getName() + " la Vestal ===");
        System.out.println("Niveau: " + vestal.getLevel());
        System.out.println("Combat skills: ");
        vestal.getCombatSkills().forEach(skill -> System.out.println("- " + skill));
        System.out.println();

        System.out.println("=== " + plague.getName() + " le Plague Doctor ===");
        System.out.println("Niveau: " + plague.getLevel());
        System.out.println("Combat skills: ");
        plague.getCombatSkills().forEach(skill -> System.out.println("- " + skill));
    }
} 