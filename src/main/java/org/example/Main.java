package org.example;

import org.example.Gladiators.Dimachaerus;
import org.example.Gladiators.Gallus;
import org.example.Gladiators.Sagittarius;

import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String[] monsters = {"wolf", "goblin", "ogr", "bear", "boar", "dark elf", "alien"};
    private static final String PROGRESS_FILE = "Progress.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gladiator_Stats gladiator = null;

        System.out.println("1. Start new game\n2. Load game");
        System.out.print("Choose action: ");
        int choice = scanner.nextInt();

        if (choice == 1){
           gladiator = chooseGladiator(scanner);
        } else if (choice == 2) {
            try {
                System.out.println(1);
                gladiator = Gladiator_Stats.loadFromFile(PROGRESS_FILE);
            } catch (IOException e) {
                System.out.println("Failed to load data, start a new one.");
                gladiator = chooseGladiator(scanner);
            }
        }
        while (true) {
            System.out.println("\n1. Travel\n2. Show attributes\n3. Save game and exit");
            System.out.print("Choose what to do: ");
            int actionChoice = scanner.nextInt();
            switch (actionChoice) {
                case 1 -> {
                    travel(gladiator);
                }
                case 2 -> {
                    showAttributes(gladiator);
                }
                case 3 -> {
                    System.out.println("Game saved successfully!");
                    gladiator.saveToFile("Progress.txt");
                    System.exit(0);
                }
            }
        }

    }

    private static Gladiator_Stats chooseGladiator(Scanner scanner) {
        System.out.println("Gladiators: ");
        System.out.println(" 1. Gallus\n 2. Sagittarius\n 3. Dimachaerus");
        System.out.print("Choose gladiator: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                Gallus gallus = new Gallus(5, 2, 0,1,1,10,30,1,3, true);
                System.out.println("You choose gladiator 'Gallus'. You have " + (gallus.getBasicHP() + gallus.getConstitution() * 5) + "hp and max attack " + (gallus.getBasicAttack() + gallus.getStrength() * 2));
                return gallus;
            }
            case 2 -> {
                Sagittarius sagittarius = new Sagittarius(2,2,0,5,1,8,20, 1,2,false);
                System.out.println("You choose gladiator 'Sagittarius'. You have " + (sagittarius.getBasicHP() + sagittarius.getConstitution() * 2) + "hp and max attack " + (sagittarius.getBasicAttack() + sagittarius.getStrength() * 2));
                return sagittarius;
            }

            case 3 -> {
                Dimachaerus dimachaerus = new Dimachaerus(2,5,0,2,1,14,15,1,1, false);
                System.out.println("You choose gladiator 'Dimachaerus'. You have " + (dimachaerus.getBasicHP() + dimachaerus.getConstitution() * 2) + "hp and max attack " + (dimachaerus.getBasicAttack() + dimachaerus.getStrength() * 5));
                return dimachaerus;
            }
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }


    public static void travel(Gladiator_Stats gladiator) {
        System.out.println(gladiator.getLevel());
        Monster monster = new Monster(randNumber(7, 15 + gladiator.getLevel()), randNumber(15, 50 * gladiator.getLevel()), monsters[randNumber(0, monsters.length)]);
        System.out.printf("You meet: %s, attack: %d, hp: %d \n", monster.getName(), monster.getAttack(), monster.getHp());
        System.out.println("1. Fight\n2. Agree/Appease\n3. Run away");
        System.out.print("Choose what to do: ");
        Scanner scanner = new Scanner(System.in);
        int gameProcess = scanner.nextInt();

        switch (gameProcess) {
            case 1 -> {
                System.out.println("Fight");
                fight(monster, gladiator);
            }
            case 2 -> {
                System.out.println("Placate");
            }
            case 3 -> {
                System.out.println("Negotiate");
            }
            case 4 -> {
                System.out.println("Run away");
            }
        }
    }

    public static void showAttributes(Gladiator_Stats gladiator){
        System.out.println(" ");
        System.out.printf(
                """
                        1. Constitution (%d)
                        2. Strength (%d)
                        3. HP (%d)
                        4. XP (%d)
                        5. Dexterity (%d)
                        6. Level (%d)
                        7. Charisma (%d)
                        8. BasicAttack (%d)
                        9. HaveShield (%s)""",
                gladiator.getConstitution(),
                gladiator.getStrength(),
                gladiator.getBasicHP(),
                gladiator.getXp(),
                gladiator.getDexterity(),
                gladiator.getLevel(),
                gladiator.getCharisma(),
                gladiator.getBasicAttack(),
                gladiator.isHaveShield()
        );
    }


    public static int randNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    private static void fight(Monster monster, Gladiator_Stats gladiator) {
        int gladHp = gladiator.getBasicHP() + gladiator.getConstitution() * 5;
        int combatEffect = (int) Math.sqrt(monster.getAttack() * monster.getHp()) / 2;
        int xp = (int) (combatEffect / 2);
        Scanner scanner = new Scanner(System.in);
        while (monster.getHp() > 0 && gladHp > 0) {
            System.out.println("1. Attack\n 2. Block");
            System.out.print("Chose action: ");
            int chooseAct = scanner.nextInt();
            int damageGlad = 0;
            int damageMons = 0;
            switch (chooseAct) {
                case 1 -> {
                    damageGlad = randNumber(gladiator.getBasicAttack(), gladiator.getBasicAttack() + gladiator.getStrength());
                }
                case 2 -> {
                    if (gladiator.isHaveShield()) {
                        damageMons /= 5;
                    } else {
                        damageMons /= 2;
                    }
                    System.out.println("You block attack");
                }
            }
            int chooseActEnemy = randNumber(1, 3);
            switch (chooseActEnemy) {
                case 1 -> {
                    damageMons = randNumber(1, monster.getAttack());
                }

                case 2 -> {
                    damageGlad /= 2;
                    System.out.println("Enemy block attack");
                }
            }

            gladHp -= damageMons;
            monster.setHp(monster.getHp() - damageGlad);

            if (monster.hp < 0) {
                System.out.println("\nYou win! You gain " + xp + " xp");
                gladiator.setXp(gladiator.getXp() + xp);
                monster.hp = 0;
                System.out.println(gladiator.getXp() + "/" + NEED_XP_TO_NEXT_LEVEL);
                levelUp(gladiator);
                break;
            } else if (gladHp < 0) {
                System.out.println("You lose You lost " + xp + "xp");
                gladiator.setXp(gladiator.getXp() - xp);
                gladHp = 0;
                System.out.println(gladiator.getXp() + "/" + NEED_XP_TO_NEXT_LEVEL);
                break;
            }


            System.out.printf("""
                            1. Your Hp %d
                            2. Enemy Hp %d
                            """,
                    gladHp,
                    monster.getHp()
            );

        }
    }

    private static int NEED_XP_TO_NEXT_LEVEL = 100;

    private static void levelUp(Gladiator_Stats gladiator) {
        if (gladiator.getXp() >= NEED_XP_TO_NEXT_LEVEL) {
            gladiator.setXp(0);
            gladiator.setLevel(gladiator.getLevel() + 1);
            System.out.println("Congratulations! You leveled up to level " + gladiator.getLevel());
            int points = 5;
            NEED_XP_TO_NEXT_LEVEL *= 1.4;
            while (points > 0) {
                upgradeAttributes(gladiator);
                points--;
            }
        }
    }


    private static void upgradeAttributes(Gladiator_Stats gladiator) {
        System.out.printf(
                """
                         1. Constitution (%d)
                         2. Strength (%d)
                         3. Dexterity (%d)
                         4. Charisma (%d)
                         5. Crit Chance (%d)
                        What do you want to upgrade? """,
                gladiator.getConstitution(),
                gladiator.getStrength(),
                gladiator.getDexterity(),
                gladiator.getCharisma(),
                gladiator.getCritChance()
        );
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> gladiator.setConstitution(gladiator.getConstitution() + 1);
            case 2 -> gladiator.setStrength(gladiator.getStrength() + 1);
            case 3 -> gladiator.setDexterity(gladiator.getDexterity() + 1);
            case 4 -> gladiator.setCharisma(gladiator.getCharisma() + 1);
            case 5 -> gladiator.setCritChance(gladiator.getCritChance() + 1);
        }
    }


}