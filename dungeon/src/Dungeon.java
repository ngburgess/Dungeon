import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Dungeon {
    public static final String BOLD = "\u001b[1m";
    public static final String RESET = "\u001b[0m";
    public static final String CLEAR = "\u001b[H\u001b[2J";

    private static Clip clip = null;

    private static void clearScreen() {
        System.out.print(CLEAR);
        System.out.flush();
    }

    private static void centerText(String text) {
        for(int i = 0; i < (45 - text.length()) / 2; ++i) {
            System.out.print(" ");
        }
        System.out.println(text);
    }

    private static void printStartScreen() {
        System.out.println("+-------------------------------------------+\n\n\n\n" + BOLD);
        centerText("Dungeon");
        System.out.print(RESET);
        centerText("Text-Based Adventure Game");
        System.out.println("\n\n\n");
        centerText("Press Enter");
        System.out.println("+-------------------------------------------+");
        centerText("(C) 2025 Nick Burgess");
        System.out.println("+-------------------------------------------+");
    }

    private static void playBackgroundMusic(String filePath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            File audioPath = new File(filePath);
            if (audioPath.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioPath);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("Background music not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printHeader(String title) {
        System.out.println("+-------------------------------------------+");
        centerText(title);
        System.out.println("+-------------------------------------------+");
    }

    private static void printHeader(Monster monster) {
        System.out.println("+-------------------------------------------+");

        String name = monster.toString();
        int level = monster.getLevel();
        int hp = monster.getHp();
        int strength = monster.getStrength();
        int statsLength = String.valueOf(hp).length() + String.valueOf(strength).length() + 3;
        int numSpaces = (45-name.length())/2 - 7;

        System.out.print("  LVL " + level);
        for(int i = 0; i < numSpaces; ++i) {
            System.out.print(" ");
        }
        System.out.print(name);
        for(int i = 0; i < 38-numSpaces-name.length()-statsLength; ++i) {
            System.out.print(" ");
        }
        System.out.println(hp + "/" + strength);

        System.out.println("+-------------------------------------------+");
    }

    private static void printInstructions() {
        System.out.println("+-------------------------------------------+");
        centerText("Press Enter");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        clearScreen();
        playBackgroundMusic("../resources/music/mixkit-unforgiven-890.wav");
        printStartScreen();
        System.out.print("  > ");
        sc.nextLine();

        clearScreen();
        playBackgroundMusic("../resources/music/mixkit-vampires-in-the-city-892.wav");
        Monster monster = new Monster("Monster", 1, 10, 5);
        Player player = new Player(1, 0, 0, 0, 0, 0, null, null, null);
        printHeader(monster);
        System.out.println("\n\n\n\n");
        printInstructions();
        player.printStatusBar();
    }
}
