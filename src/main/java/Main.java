import domenObject.Student;
import service.ScriptReaderService;
import service.ScriptReaderServiceImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static ScriptReaderService scriptReaderService;

    private static final String INSERT_SCRIPT_FILE = "C:\\Java\\project\\Student\\src\\main\\resources\\insertStudent.sql";
    private static final String INSERT_SCRIPT = "INSERT INTO student(name, ser_name, phone, email) VALUES (";

    public static void main(String[] args) {
//        ArrayList<Student> students = new ArrayList<>();
//        for (int i = 0; i <= 30; i++) {
//            students.add(new Student("alina" + i, "kurt" + i, "+3805023" + i, "all@gmaill.com" + i));
//        }
//        //Todo  проверить запись, еслинаша файл пустой.
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE, true))) {
//            for (Student student : students) {
//                bw.write(INSERT_SCRIPT +"'"+student.getName() + "', '" + student.getSerName() + "', '" + student.getPhone() + "', '" + student.getEmail()+"');");
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        scriptReaderService = new ScriptReaderServiceImpl();
        scriptReaderService.countNameFromScript();
        scriptReaderService.printEmail();
       // scriptReaderService.sameSerName();


    }
}

