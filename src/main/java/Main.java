import com.sun.org.apache.xpath.internal.SourceTree;
import domenObject.Adres;
import domenObject.Student;
import service.ScriptReaderService;
import service.ScriptReaderServiceImpl;
import service.TestJdbcConnetction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    private static ScriptReaderService scriptReaderService;

    private static final String INSERT_SCRIPT_FILE = "C:\\Java\\project\\Student2205\\src\\main\\resources\\insertStudent.sql";
    private static final String INSERT_SCRIPT = "INSERT INTO student(name, ser_name, phone, email) VALUES (";
    private static ArrayList<Student> students = new ArrayList<>();
    private static TestJdbcConnetction testJdbcConnetction = new TestJdbcConnetction();

    public static void main(String[] args) throws SQLException {

        ScriptReaderServiceImpl scriptReaderService = new ScriptReaderServiceImpl();

        testJdbcConnetction.myCreateConnection();//создание подключения к базе
        countStudentFromTableTest();//кол-во студентов в таблице
        selectByEmail();//вывод студентов у кого почта на gmail.com
        sameSername();//вывод однофамильцев из таблицы student

    }

    public static void countStudentFromTableTest() throws SQLException {
        PreparedStatement pr = testJdbcConnetction.getConnection().prepareStatement("select COUNT(name) FROM student");
        ResultSet rs2 = pr.executeQuery();
        while (rs2.next()) {
            System.out.println("Количество студентов в таблице Student=" + rs2.getString(1));
        }
        System.out.println("-----------------------------------");
    }

    public static void countStudentFromTable() {
        String query = "select COUNT(name) from student";
        try {
            Statement statement = testJdbcConnetction.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                System.out.println("Количество студентов в таблице Student=" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");
    }

    public static void sameSername() throws SQLException {
        PreparedStatement pr = testJdbcConnetction.getConnection().prepareStatement("SELECT * FROM student where ser_name=(SELECT ser_name FROM student group by ser_name Having COUNT(ser_name)>1 )");
        ResultSet rs2 = pr.executeQuery();
        System.out.println("Вывод однофамильцев:");
        while (rs2.next()) {
            System.out.println("Name=" + rs2.getString(1) + ", Ser_name=" + rs2.getString(2) + ", e-mail=" + rs2.getString(4));
        }
        System.out.println("-----------------------------------");
    }

    public static void selectByEmail() throws SQLException {
        PreparedStatement pr = testJdbcConnetction.getConnection().prepareStatement("select * FROM student WHERE email LIKE ?");
        pr.setString(1, "%gmail.com%");
        ResultSet rs2 = pr.executeQuery();
        System.out.println("Вывод студентов у кого почта на gmail.com:");
        while (rs2.next()) {
            System.out.println("Name=" + rs2.getString(1) + ", Ser_name=" + rs2.getString(2) + ", e-mail=" + rs2.getString(4));
        }
        System.out.println("-----------------------------------");
    }

    public static void insertStudentToScript() {
        System.out.println("Добавление записей в скрипт INSERT STUDENT");
        for (int i = 0; i < 30; i++) {
            students.add(new Student("alina" + i, "kurt" + i, "+3805023" + i, "all@gmaill.com" + i));
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE, true))) {
            for (Student student : students) {
                bw.write(INSERT_SCRIPT + "'" + student.getName() + "', '" + student.getSerName() + "', '" + student.getPhone() + "', '" + student.getEmail() + "');");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

