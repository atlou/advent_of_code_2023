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

        int sum = 0;
        while(s.hasNextLine()) {
            String line = s.nextLine();
            sum += gameIsPossible(line, 12, 13, 14);
        }
        System.out.println(sum);
    }

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
}
