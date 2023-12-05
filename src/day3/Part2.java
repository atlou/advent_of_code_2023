package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        Scanner s;
        File f = new File("src/day3/input.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> lines = new ArrayList<>();
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }

        char[][] chars = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < chars.length; y++) {
            String line = lines.get(y);
            for (int x = 0; x < chars[y].length; x++) {
                chars[y][x] = line.charAt(x);
            }
        }

        int sum = 0;
        for (int y = 0; y < chars.length; y++) {
            for(int x = 0; x < chars[y].length; x++) {
                if(chars[y][x] == '*') sum += ratio(x, y, chars);
            }
        }
        System.out.println(sum);
    }

    static int ratio(int x, int y, char[][] chars) {
        ArrayList<Integer> nums = new ArrayList<>();
        if (y > 0) {
            // get numbers from top
            ArrayList<Integer> top = getNumbers(x, chars[y-1]);
            for(int number : top) {
                nums.add(number);
            }
        }
        if (y < chars.length - 1) {
            // get numbers from bot
            ArrayList<Integer> bot = getNumbers(x, chars[y+1]);
            for(int number : bot) {
                nums.add(number);
            }
        }
        if (x > 0) {
            // get number left
            String l = getNumberLeft(x - 1, chars[y]);
            if(l.length() > 0) nums.add(Integer.parseInt(l));
        }
        if (x < chars[y].length - 1) {
            // get number right
            String r = getNumberRight(x + 1, chars[y]);
            if(r.length() > 0) nums.add(Integer.parseInt(r));
        }

        if(nums.size() != 2) return 0;

        int ratio = 1;
        for(int number : nums) {
            System.out.printf("number %d\n", number);
            ratio *= number;
            System.out.printf("ratio %d\n", ratio);
        }

        System.out.println();
        return ratio;
    }

    static ArrayList<Integer> getNumbers(int x, char[] line) {
        ArrayList<Integer> nums = new ArrayList<>();
        if (Character.isDigit(line[x])) {
            String s = "";
            if(x > 0) s += getNumberLeft(x - 1, line);
            s += line[x];
            if(x < line.length - 1) s += getNumberRight(x + 1, line);
            if(s.length() > 0) {
                nums.add(Integer.parseInt(s));
            }
        } else {
            String r = getNumberRight(x + 1, line);
            String l = getNumberLeft(x - 1, line);
            if(r.length() > 0) nums.add(Integer.parseInt(r));
            if(l.length() > 0) nums.add(Integer.parseInt(l));
        }
        return nums;
    }

    static String getNumberRight(int startX, char[] chars) {
        StringBuilder n = new StringBuilder();
        for (int x = startX; x < chars.length; x++) {
            if (Character.isDigit(chars[x])) {
                n.append(chars[x]);
            }
            else
                break;
        }
        return n.toString();
    }

    static String getNumberLeft(int startX, char[] chars) {
        StringBuilder n = new StringBuilder();
        for (int x = startX; x >= 0; x--) {
            if (Character.isDigit(chars[x]))
                n.append(chars[x]);
            else
                break;
        }
        return n.reverse().toString();
    }
}
