package рф.пинж.ios.model.prototype.users;

import рф.пинж.ios.model.Model;

import static рф.пинж.ios.utils.Utils.sha512Generator;

public abstract class User extends Model {

    protected int id;
    protected String login;
    protected String password;
    protected String email;
    protected String last_name;
    protected String first_name;
    protected String middle_name;


    protected User() {

    }

    protected User(String last_name, String first_name, String middle_name, String email, String password) {
        this.password = password;
        this.email = email;
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_name = middle_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public void setMiddleName(String middleName) {
        this.middle_name = middleName;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(sha512Generator(password));
    }


}