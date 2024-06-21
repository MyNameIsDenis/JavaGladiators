package org.example;

public class Monster {
    String name;
    int attack;
    int hp;

    public Monster(int attack, int hp, String name) {
        this.attack = attack;
        this.name = name;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
