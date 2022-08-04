
package pack.service;

import java.io.Serializable;

public class User implements Serializable
{
    private String firstname;//nombre
    private int age;//edad
    private int phone;//telefono
    private String email;//correo
    private String password;//contrasenna
    private String confirmPassword;//confirmar contrasenna
    private int userType;//tipo de usuario

    public User() {
    }

    public User(String firstname, int age, int phone, String email, String password, String confirmPassword, int userType) {
        this.firstname = firstname;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userType = userType;
    }

    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "firstname=" + firstname + ", age=" + age + ", phone=" + phone + ", email=" + email + ", password=" + password + ", userType=" + userType + '}';
    }
}
