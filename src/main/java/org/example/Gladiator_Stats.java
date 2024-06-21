package org.example;

import java.io.*;
import java.io.IOException;

public class Gladiator_Stats {
    public int constitution;
    int strength;
    int xp;
    int dexterity;
    int level;
    int basicAttack;
    int basicHP;
    int critChance;
    int charisma;
    boolean haveShield;

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public Gladiator_Stats(int constitution, int strength, int xp, int dexterity, int level, int basicAttack, int basicHP, int critChance, int charisma, boolean haveShield) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.critChance = critChance;
        this.charisma = charisma;
        this.haveShield = haveShield;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public int getBasicHP() {
        return basicHP;
    }

    public boolean isHaveShield() {
        return haveShield;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getStrength() {
        return strength;
    }

    public int getXp() {
        return xp;
    }

    public void setBasicAttack(int basicAttack) {
        this.basicAttack = basicAttack;
    }

    public void setBasicHP(int basicHP) {
        this.basicHP = basicHP;
    }

    public void setHaveShield(boolean haveShield) {
        this.haveShield = haveShield;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getLevel() {
        return level;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCharisma() {
        return charisma;
    }

    public void saveToFile(String file) {
        String filename = "Progress.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(constitution + "," + strength + "," + xp + "," + dexterity + "," + level + "," + basicAttack + "," + basicHP + "," + critChance + "," + charisma + "," + haveShield);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Gladiator_Stats loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            String[] data = line.split(",");
            return new Gladiator_Stats(
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]),
                    Integer.parseInt(data[5]),
                    Integer.parseInt(data[6]),
                    Integer.parseInt(data[7]),
                    Integer.parseInt(data[8]),
                    Boolean.parseBoolean(data[9])
                    );
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public String toString() {
        return "Gladiator_Stats{" +
                "constitution=" + constitution +
                ", strength=" + strength +
                ", xp=" + xp +
                ", dexterity=" + dexterity +
                ", level=" + level +
                ", basicAttack=" + basicAttack +
                ", basicHP=" + basicHP +
                ", critChance=" + critChance +
                ", charisma=" + charisma +
                ", haveShield=" + haveShield +
                '}';
    }
}
