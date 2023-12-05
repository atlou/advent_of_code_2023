package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
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
        for(int y = 0; y < chars.length; y++) {
            String line = lines.get(y);
            for (int x = 0; x < chars[y].length; x++) {
                chars[y][x] = line.charAt(x);
            }
        }

        int sum = 0;
        for (int y = 0; y < chars.length; y++) {
            int x1 = -1;
            int x2 = -1;
            for (int x = 0; x < chars[y].length; x++) {
                char c = chars[y][x];
                if(Character.isDigit(c)) {
                    if(x1 == -1) {
                        x1 = x;
                    }
                    x2 = x;
                } else {
                    if(x1 != -1) sum += getNumber(x1, x2, y, chars); // number just ended
                    x1 = -1;
                }
            }
            if(x1 != -1) sum += getNumber(x1, x2, y, chars); // number ends with new line
        }
        System.out.println(sum);
    }

    // returns the number contained in the range of x
    // returns 0 if the number is not surrounded by a symbol
    static int getNumber(int x1, int x2, int y, char[][] chars) {
        if(!inRange(x1, x2, y, chars)) return 0;
        String n = "";
        for (int x = x1; x <= x2; x++) {
            n += chars[y][x];
        }
        return Integer.parseInt(n);
    }

    // returns true if a symbol is present around a range of x
    static boolean inRange(int x1, int x2, int y, char[][] chars) {
        boolean onEdgeL = x1 == 0;
        boolean onEdgeR = x2 == chars[0].length - 1;

        int l = onEdgeL ? 0 : x1 - 1;
        int r = onEdgeR ? x2 : x2 + 1;

        // check same y before and after range
        if(!onEdgeL && isSpecial(chars[y][x1-1])) return true;
        if(!onEdgeR && isSpecial(chars[y][x2+1])) return true;

        // check above and below for whole range
        for (int x = l; x <= r; x++) {
            if (y > 0 && isSpecial(chars[y - 1][x])) return true;
            if (y < chars.length - 1 && isSpecial(chars[y+1][x])) return true;
        }

        return false;
    }

    // checks if a char is a symbol
    static boolean isSpecial(char c) {
        return !(Character.isDigit(c) || c == '.');
    }
}
