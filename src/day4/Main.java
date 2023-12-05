package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner s;
        File f = new File("src/day4/input.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        int sum = 0;
//        while (s.hasNextLine()) {
//            sum += cardPoints(s.nextLine());
//        }
//        System.out.println(sum);

        ArrayList<String> cards = new ArrayList<>();
        while (s.hasNextLine()) {
            String card = s.nextLine();
            cards.add(card);
        }

        int[] factors = new int[cards.size()];
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            int fac = 1 + factors[i];
            sum++;
            for(int j = 1; j <= numberOfScratchards(cards.get(i)); j++) {
                factors[j+i] += fac;
                sum += fac;
            }
            System.out.println(Arrays.toString(factors));
        }

        System.out.println(sum);
    }

    static int numberOfScratchards(String s) {
        String[] strings = s.split(":");
        String card = strings[0];
        String numbers = strings[1];

        String[] strings2 = numbers.split("\\|");

        ArrayList<Integer> winning = numbers(strings2[0]);
        ArrayList<Integer> yours = numbers(strings2[1]);

        int number = 0;
        for(int n : winning) {
            if(yours.contains(n)) {
                number++;
            }
        }

        return number;
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
