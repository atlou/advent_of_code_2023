package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s;
        File f = new File("src/day1/input.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int sum = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
//            sum += calibrationValue(line);
            sum += calibrationValueWithText(line);
        }

        System.out.println(sum);
    }

    private static int calibrationValue(String s) {
        String first = "";
        String last = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                first = String.valueOf(c);
                break;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                last = String.valueOf(c);
                break;
            }
        }

        String num = first + last;
        return Integer.parseInt(num);
    }

    private static int calibrationValueWithText(String s) {
        String first = "";
        String last = "";

        s = s.replace("one", "one1one");
        s = s.replace("two", "two2two");
        s = s.replace("three", "three3three");
        s = s.replace("four", "four4four");
        s = s.replace("five", "five5five");
        s = s.replace("six", "six6six");
        s = s.replace("seven", "seven7seven");
        s = s.replace("eight", "eight8eight");
        s = s.replace("nine", "nine9nine");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                first = String.valueOf(c);
                break;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                last = String.valueOf(c);
                break;
            }
        }

        String num = first + last;
        return Integer.parseInt(num);
    }
}
