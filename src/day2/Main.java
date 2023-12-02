package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s;
        File f = new File("src/day2/input.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int sumID = 0;
        int sumPower = 0;
        while(s.hasNextLine()) {
            String line = s.nextLine();
            sumID += gameIsPossible(line, 12, 13, 14);
            sumPower += gamePower(line);
        }
        System.out.println(sumID);
        System.out.println(sumPower);
    }

    // returns the game id if the game is possible
    // returns 0 otherwise
    private static int gameIsPossible(String s, int red, int green, int blue) {
        s = s.replace(';', ',');
        String[] sc = s.split(":");

        String game = sc[0];
        int gameID = Integer.parseInt(game.substring(5));

        String specs = sc[1];
        String[] grabs = specs.split(",");

        for (String g : grabs) {
            g = g.trim();
            if (g.contains("red")) {
                g = g.replace(" red", "");
                if (Integer.parseInt(g) > red) return 0;
            }
            if (g.contains("green")) {
                g = g.replace(" green", "");
                if (Integer.parseInt(g) > green) return 0;
            }
            if (g.contains("blue")) {
                g = g.replace(" blue", "");
                if (Integer.parseInt(g) > blue) return 0;
            }
        }

        return gameID;
    }

    // returns the power of a game
    private static int gamePower(String s) {
        s = s.replace(';', ',');
        String[] sc = s.split(":");

        String game = sc[0];
        String specs = sc[1];
        String[] grabs = specs.split(",");

        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;
        for (String g : grabs) {
            g = g.trim();
            if (g.contains("red")) {
                g = g.replace(" red", "");
                minRed = Math.max(Integer.parseInt(g), minRed);
            }
            if (g.contains("green")) {
                g = g.replace(" green", "");
                minGreen = Math.max(Integer.parseInt(g), minGreen);
            }
            if (g.contains("blue")) {
                g = g.replace(" blue", "");
                minBlue = Math.max(Integer.parseInt(g), minBlue);
            }
        }

        return minRed * minGreen * minBlue;
    }
}
