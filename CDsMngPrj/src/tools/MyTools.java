package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.CD;

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

    public static int getInt(String welcome) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number !!!");
                check = true;
            }
        } while (check);
        return number;
    }

    public static List<String> readLinesFromFile(String filename) {
        ArrayList list = new ArrayList();
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

    public static String parseDate(String welcome, String dateFormat) {
        String input = "";
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(welcome);
            input = sc.nextLine();
            SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
            dF.applyPattern(dateFormat);
            try {
                long t = dF.parse(input).getTime();
                check = false;
                return dF.format(t);
            } catch (ParseException e) {
                System.out.println("Wrong format");
                check = true;
            }
        } while (check);
        return null;
    }

    public static String dateToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }

    public static ArrayList<CD> loadCDFromFile(String filename) {
        String ID;
        String name;
        String type;
        String title;
        int unitprice;
        String publishingyear;

        ArrayList<CD> list = new ArrayList<CD>();
        list.clear();
        List<String> lines = MyTools.readLinesFromFile("CDList.txt");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("" + ',');
            ID = parts[0].trim();
            name = parts[1].trim();
            type = parts[2].trim();
            title = parts[3].trim();
            unitprice = Integer.parseInt(parts[4].trim());
            publishingyear = parts[5].trim();
            list.add(new CD(ID, name, type, title, unitprice, publishingyear));
        }
        return list;
    }

    public static void saveToFile(List<CD> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list !");
        }
        try {
            File f = new File("CDList.txt");
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            for (CD x : list) {
                pw.println(x.getID() + ", " + x.getName() + ", " + x.getType() + ", " + x.getTitle() + ", " + x.getUnitprice() + ", " + x.getPublishingyear());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
