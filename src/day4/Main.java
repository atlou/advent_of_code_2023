package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s;
        File f = new File("src/day4/input.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int sum = 0;
        while (s.hasNextLine()) {
            sum += cardPoints(s.nextLine());
        }
        System.out.println(sum);
    }

    static int cardPoints(String s) {
        String[] strings = s.split(":");
        String card = strings[0];
        String numbers = strings[1];

        String[] strings2 = numbers.split("\\|");

        ArrayList<Integer> winning = numbers(strings2[0]);
        ArrayList<Integer> yours = numbers(strings2[1]);

        int points = 0;
        for(int n : winning) {
            if(yours.contains(n)) {
                if(points > 0) points *= 2;
                else points = 1;
            }
        }

        return points;
    }

    static ArrayList<Integer> numbers(String s) {
        ArrayList<Integer> numbers = new ArrayList<>();
        String[] strings = s.split(" ");
        for(String substring : strings) {
            substring = substring.trim();
            if(substring.length() > 0)
                numbers.add(Integer.parseInt(substring));
        }
        return numbers;
    }
}
