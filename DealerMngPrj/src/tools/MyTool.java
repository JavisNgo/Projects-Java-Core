package tools;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.io.IOException;

public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        boolean check = true;
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();
            check = false;
        } while (check);
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFormFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        File f = new File(filename);
        String line;
        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        list.add(line);
                    }
                }
                br.close();
                fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        File f = new File(filename);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i));
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
//    public static void main(String[] args) {
//        System.out.println("Tests with phone numbers:");
//        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));
//
//        System.out.println("Test pass word");
//        System.out.println(validPassword("qwerty", 8));
//        System.out.println(validPassword("qwertyABC", 8));
//        System.out.println(validPassword("12345678", 8));
//        System.out.println(validPassword("qbc123456", 8));
//        System.out.println(validPassword("qbc@123456", 8));
//
//        System.out.println("Test with IDs:");
//        System.out.println(validStr("A0001", "D\\d{3}"));
//        System.out.println(validStr("10001", "D\\d{3}"));
//        System.out.println(validStr("D0001", "D\\d{3}"));
//        System.out.println(validStr("D101", "D\\d{3}"));
//
//        Date d = parseDate("2022:12:07", "yyyy:MM:dd");
//        System.out.println(d);
//        System.out.println(dataToStr(d, "dd/MM/yyyy"));
//        d = parseDate("12/07/2022", "MM/dd/yyyy");
//    }

