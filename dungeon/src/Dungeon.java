import java.io.*;
import java.util.*;

import javax.sound.sampled.*;

public class Dungeon {
    private static ArrayList<Monster> monsters = new ArrayList<>();
    private static ArrayList<Armor> armorList = new ArrayList<>();
    private static ArrayList<Weapon> weapons = new ArrayList<>();
    private static ArrayList<Spell> spells = new ArrayList<>();

    private static Clip music = null;
    private static boolean fleeEnabled = true;
    private static int maxMana = 4;

    private static void clearScreen() {
        System.out.print(AnsiCodes.CLEAR);
        System.out.flush();
    }

    private static void centerText(String text) {
        if (text == null) {
            System.out.println("Text is null.");
            return;
        }

        for(int i = 0; i < (45 - text.length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.println(text);
    }

    private static void printStartScreen() {
        System.out.println("+-------------------------------------------+\n\n\n\n" + AnsiCodes.BOLD);
        centerText("Dungeon");
        System.out.print(AnsiCodes.RESET);
        centerText("Text-Based Adventure Game");
        System.out.println("\n\n\n");
        centerText("Press Enter");
        System.out.println("+-------------------------------------------+");
        centerText("(C) 2025 Nick Burgess");
        System.out.println("+-------------------------------------------+");
    }

    private static void playBackgroundMusic(String filePath) {
        try {
            if (music != null && music.isRunning()) {
                music.stop();
                music.close();
            }

            File audioPath = new File(filePath);
            if (audioPath.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioPath);
                music = AudioSystem.getClip();
                music.open(audioStream);
                music.loop(Clip.LOOP_CONTINUOUSLY);
                music.start();
            } else {
                System.out.println("Background music not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void playSoundEffect(String filePath) {
        try {
            File audioPath = new File(filePath);
            if (audioPath.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioPath);
                Clip soundEffect = AudioSystem.getClip();
                soundEffect.open(audioStream);
                soundEffect.start();
            } else {
                System.out.println("Sound effect not found.");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printHeader(String title) {
        System.out.println("+-------------------------------------------+");
        centerText(title);
        System.out.println("+-------------------------------------------+");
    }

    private static void printHeader(Monster monster) {
        if (monster == null) {
            System.out.println("Monster is null.");
            return;
        }

        System.out.println("+-------------------------------------------+");

        String name = monster.toString();
        int level = monster.getLevel();
        int hp = monster.getHp();
        int strength = monster.getStrength();
        int dexterity = monster.getDexterity();
        int statsLength = String.valueOf(hp).length() + String.valueOf(strength).length() + String.valueOf(dexterity).length() + 4;
        int numSpaces = (45-name.length())/2 - 7;

        System.out.print("  LVL " + level);
        for(int i = 0; i < numSpaces; ++i) {
            System.out.print(" ");
        }
        System.out.print(AnsiCodes.BOLD + name + AnsiCodes.RESET);
        for(int i = 0; i < 38-numSpaces-name.length()-statsLength; ++i) {
            System.out.print(" ");
        }
        System.out.println(hp + "/" + strength + "/" + dexterity);

        System.out.println("+-------------------------------------------+");
    }

    private static void printInstructions() {
        System.out.println("+-------------------------------------------+");
        centerText("Press Enter");
    }

    private static void printInstructions(String type) {
        System.out.println("+-------------------------------------------+");

        if (type == null) {
            System.out.println("Instructions type is null.");
            return;
        }

        switch(type) {
            case "yesOrNo":
                centerText("1-Yes  2-No");
                break;
            case "battle":
                centerText("1-Attack  2-Spell  3-Flee");
                break;
            case "spell":
                centerText("Select a Spell");
        }
    }

    private static void storeMonsters(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] stats = line.split(",");

                String name = stats[0];
                int level = Integer.parseInt(stats[1].trim());
                int health = Integer.parseInt(stats[2].trim());
                int strength = Integer.parseInt(stats[3].trim());
                int dexterity = Integer.parseInt(stats[4].trim());

                monsters.add(new Monster(name, level, health, strength, dexterity));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void storeArmor(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] stats = line.split(",");

                String name = stats[0];
                String rarity = stats[1];
                int cost = Integer.parseInt(stats[2].trim());
                int hpBonus = Integer.parseInt(stats[3].trim());

                armorList.add(new Armor(name, rarity, cost, hpBonus));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void storeWeapons(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] stats = line.split(",");

                String name = stats[0];
                String rarity = stats[1];
                int cost = Integer.parseInt(stats[2].trim());
                String damage = stats[3];
                int crit = Integer.parseInt(stats[4].trim());

                weapons.add(new Weapon(name, rarity, cost, damage, crit));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void storeSpells(String filePath) {

    }

    private static Monster selectMonster(Player player) {
        Monster monster = null;
        Random rd = new Random();

        switch(player.getLevel()) {
            case 1:
                monster = monsters.get(rd.nextInt(0, 10));
                break;
            case 5:
                monster = monsters.get(rd.nextInt(40, 50));
        }

        return monster;
    }

    private static void battleLoop(Player player, Monster monster, Spellbook spellbook) {
        Scanner sc = new Scanner(System.in);

        printHeader(monster);
        System.out.println("  You've encountered a " + monster + ".");
        System.out.println("  What would you like to do?\n\n\n");

        int initPlayerHp = player.getHp();
        int initMonsterHp = monster.getHp();
        while (monster.getHp() > 0) {
            printInstructions("battle");
            player.displayStatusBar();
            System.out.print("  > ");
            int move = sc.nextInt();
            clearScreen();

            if (move == 1) {
                int totalDamage = player.getStrength();
                if (player.getWeapon() != null) {
                    Random rd = new Random();
                    switch (player.getWeapon().getDamage()) {
                        case "1d4":
                            totalDamage += rd.nextInt(1, 5);
                            break;
                        case "1d6":
                            totalDamage += rd.nextInt(1, 7);
                            break;
                        case "1d8":
                            totalDamage += rd.nextInt(1, 9);
                            break;
                        case "1d10":
                            totalDamage += rd.nextInt(1, 11);
                            break;
                        case "1d12":
                            totalDamage += rd.nextInt(1, 13);
                            break;
                        case "2d6":
                            totalDamage += rd.nextInt(1, 7) + rd.nextInt(1, 7);
                    }
                }

                monster.changeHp(-totalDamage);
                if (monster.getHp() == 0) {
                    playSoundEffect("../resources/sound_effects/mixkit-winning-a-coin-video-game-2069.wav");
                    int exp = player.getLevel() * 20;
                    player.changeExp(exp);
                    int gold = player.getLevel() * 5;
                    player.changeGold(gold);

                    printHeader(monster);
                    System.out.println("  You defeated the " + monster + "!");
                    System.out.println(AnsiCodes.ITALICS + "  +" + exp + " EXP");
                    System.out.println("  +" + gold + " Gold\n\n" + AnsiCodes.RESET);
                    printInstructions();
                    player.displayStatusBar();
                    player.setHp(initPlayerHp);
                    monster.setHp(initMonsterHp);
                    System.out.print("  > ");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                } else {
                    player.changeHp(-monster.getStrength());
                    if (player.getHp() == 0) {
                        //TODO Add game over
                        playSoundEffect("../resources/sound_effects/mixkit-player-losing-or-failing-2042.wav");
                        break;
                    } else {
                        playSoundEffect("../resources/sound_effects/mixkit-martial-arts-fast-punch-2047.wav");
                        printHeader(monster);
                        System.out.println("  You hit the " + monster + " for " + totalDamage + " damage.");
                        System.out.println("  The " + monster + " hit you for " + monster.getStrength() + " damage.\n\n\n");
                    }
                }
            } else if (move == 2) {
                printHeader(monster);
                System.out.println("  Mana: " + player.getMana() + "/" + maxMana);
                spellbook.displaySpellbook();
                printInstructions("spell");
                player.displayStatusBar();
                System.out.print("  > ");
                sc.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        storeMonsters("../resources/objects/monsters.csv");
        storeArmor("../resources/objects/armor.csv");
        storeWeapons("../resources/objects/weapons.csv");

        Scanner sc = new Scanner(System.in);

        clearScreen();
        playBackgroundMusic("../resources/music/mixkit-unforgiven-890.wav");
        printStartScreen();
        System.out.print("  > ");
        sc.nextLine();

        playBackgroundMusic("../resources/music/mixkit-vampires-in-the-city-892.wav");
        Player player = new Player(5, 0, 0, 30, 8, 5, 4, null, weapons.get(11), null);
        player.changeArmor(armorList.get(7));
        Spellbook spellbook = new Spellbook();
        spellbook.addSpell(new Spell("Fortitude", "Paladin", 100, 4, 4));
        spellbook.addSpell(new Spell("Healing", "Paladin", 150, 6, 2));
        spellbook.addSpell(new Spell("Revival", "Paladin", 250, 10, 1));

        for (int i = 0; i < 50; i++) {
            clearScreen();
            Monster monster = selectMonster(player);
            battleLoop(player, monster, spellbook);
        }
    }
}
