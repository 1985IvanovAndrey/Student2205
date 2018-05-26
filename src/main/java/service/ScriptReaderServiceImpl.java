package service;

import domenObject.Adres;
import domenObject.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScriptReaderServiceImpl implements ScriptReaderService {

    public static final String INSERT_SCRIPT_FILE = "C:\\Java\\project\\Student2205\\src\\main\\resources\\insertStudent.sql";
    public static final String INSERT_SCRIPT_FILE_ADRES = "C:\\Java\\project\\Student2205\\src\\main\\resources\\insertAdres1.sql";
    public static final String INSERT_SCRIPT_ADRES = "INSERT INTO adres(city, street, house) VALUES (";

    private static List<String> surnameList = new ArrayList<>();

    int studentCount = 0;

    @Override
    public void countNameFromScript() {
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                studentCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Количество студентов в таблице Student=" + studentCount);
        this.studentCount = 0;
        System.out.println("----------------------------------------------");
    }

    @Override
    public void printEmail() {
        System.out.println("Студенты с почтой на gmail.com:");
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("gmail.com")) {
                    String studentDataStr = sCurrentLine.substring(sCurrentLine.indexOf("VALUES") + 8, sCurrentLine.length() - 2).replace("'", "");
                    System.out.println(studentDataStr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------");
    }

    public void sameSerName() {
        System.out.println("Вывод однофамильцев в таблице Student:");
        Student student = null;
        Student student1 = null;
        String[] studFieldMass = null;
        String[] studFieldMass1 = null;
        List<String> surnameList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String studentDataStr = sCurrentLine.substring(sCurrentLine.indexOf("VALUES") + 8, sCurrentLine.length() - 2).replace("'", "");
                studFieldMass = studentDataStr.split(",");
                student = new Student(studFieldMass[0], studFieldMass[1], studFieldMass[2], studFieldMass[3]);
                surnameList.add(student.getSerName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> hm = new HashMap<>();
        Integer am;
        for (String i : surnameList) {
            am = hm.get(i);
            hm.put(i, am == null ? 1 : am + 1);
        }
        ArrayList<String> serName = new ArrayList<>();
        for (Map.Entry entry : hm.entrySet()) {
            if ((int) entry.getValue() > 1) {
                serName.add((String) entry.getKey());
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine1;
            while ((sCurrentLine1 = br.readLine()) != null) {
                String studentDataStr1 = sCurrentLine1.substring(sCurrentLine1.indexOf("VALUES") + 8, sCurrentLine1.length() - 2).replace("'", "");
                studFieldMass1 = studentDataStr1.split(",");
                student1 = new Student(studFieldMass1[0], studFieldMass1[1], studFieldMass1[2], studFieldMass1[3]);
                for (String s : serName) {
                    if (s.equals(student1.getSerName())) {
                        System.out.println(student1.getName() + " " + student1.getSerName() + " " + student1.getPhone() + " " + student1.getEmail());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------");

    }

    public void insertToTableAdresScript() {
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                studentCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Adres> adres = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            adres.add(new Adres("city" + i + 1, "street" + i + 1, i + 1));
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE_ADRES, true))) {
            for (Adres as : adres) {
                bw.write(INSERT_SCRIPT_ADRES + "'" + as.getCity() + "', '" + as.getStreet() + "', '" + as.getHouse() + "');");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Запись скрипта для заполнения таблицы Table создана для " + studentCount + " студентов");
        System.out.println("----------------------------------------------");
    }
}





