package domenObject;

public class Student {



    private String name;
    private String serName;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String name, String serName, String phone, String email) {
        this.name = name;
        this.serName = serName;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}