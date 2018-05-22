package service;

import domenObject.Adres;
import domenObject.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ScriptReaderServiceImpl implements ScriptReaderService {

    private static final String INSERT_SCRIPT_FILE = "C:\\Java\\project\\Student\\src\\main\\resources\\insertStudent.sql";
    private static final String INSERT_SCRIPT_FILE_ADRES = "C:\\Java\\project\\Student\\src\\main\\resources\\insertAdres.sql";
    private static final String INSERT_SCRIPT_ADRES = "INSERT INTO adres(city, street, house) VALUES (";

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
        System.out.println("Количество студентов в таблице=" + studentCount);
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
        insertToTableAdresScript();
    }

    @Override
    public void sameSerName() {
        Student student = null;
        String[] studFieldMass = null;
        List<String> surnameList1 = new ArrayList<>();
        List<String> surnameList2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String studentDataStr = sCurrentLine.substring(sCurrentLine.indexOf("VALUES") + 8, sCurrentLine.length() - 2).replace("'", "");
                studFieldMass = studentDataStr.split(",");
                student = new Student(studFieldMass[0], studFieldMass[1], studFieldMass[2], studFieldMass[3]);
                //System.out.println(student.getSerName());
                surnameList1.add(student.getSerName());
                System.out.println(student.getName() + " " + student.getSerName() + " " + student.getPhone() + " " + student.getEmail());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertToTableAdresScript() {
        ArrayList<Adres> adres = new ArrayList<>();
        for (int i = 0; i <= studentCount; i++) {
            adres.add(new Adres("city" + i, "street" + i, i));
        }
        //Todo  проверить запись, еслинаша файл пустой.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE_ADRES, true))) {
            for (Adres as : adres) {
                bw.write(INSERT_SCRIPT_ADRES +"'"+as.getCity() + "', '" + as.getStreet() + "', '" + as.getHouse() +"');");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Запись скрипта для заполнения таблицы Table создана");
    }
}





