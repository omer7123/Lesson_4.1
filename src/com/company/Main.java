package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {250, 250, 250, 200};
    public static int[] heroesDamage = {20, 20, 20};
    public static int[] heroesRegeneration = {10};

    public static String[] heroesAttackType = {"Phusical", "magical", "Mental", "Medic"};

    public static void main(String[] args) {

        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bosshit();
        heroeshit();
        heroesRegen();
        fightInfo();
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss Won");
            return true;
        }
        return false;
    }

    public static void bosshit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
    }

    public static void heroeshit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random2 = new Random();
                    int koeff = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * koeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koeff;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit " + heroesDamage[i] * koeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }

            }
        }
    }

    public static void fightInfo() {
        System.out.println("____________________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Mental health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);

        System.out.println("____________________________");
    }

    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length - 1);
        bossDefenceType = heroesAttackType[randomIndex];
    }


    public static void heroesRegen() {

        for (int i = 0; i < heroesRegeneration.length; i++) {

            for (int j = 0; j < heroesHealth.length - 1; j++) {
                if (heroesHealth[j] > 0) {
                    heroesHealth[j]  = heroesHealth[j] + heroesRegeneration[i];
                }
            }

        }

    }
}
