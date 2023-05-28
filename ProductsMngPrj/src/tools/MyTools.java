package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTools {
    public static final Scanner SC = new Scanner(System.in);

    public static String getString(String welcome, String msg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static String getStringForm(String welcome, String msg, String pattern, String form) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!result.matches(pattern)) {
                System.out.println(form);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static float getFloat(String welcome, float min, float max) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Must be larger than " + min);
                } else if (number > max) {
                    System.out.println("Must be smaller than " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number !!!");
            }
        } while (check);
        return number;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Must be larger than " + min);
                } else if (number > max) {
                    System.out.println("Must be smaller than " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number !!!");
            }
        } while (check);
        return number;
    }

    public static List<String> readLinesFromFile(String filename) {
        ArrayList list = new ArrayList();
        File f = new File(filename);
        String line;
        if (f.exists()) {
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) list.add(line);
                }
                br.close();
                fr.close();
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }
    
}
