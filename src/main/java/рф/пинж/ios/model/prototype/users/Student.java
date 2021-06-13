package рф.пинж.ios.model.prototype.users;

import javax.swing.*;
import java.util.List;

public class Student extends User {

    //private Group group;
    private int id_group;
    private final long id_gradebook;

    {
        super.table = "students";
        super.columns = List.of("idGroup", "id_gradebook", "login", "password", "email", "lastName", "firstName", "middleName");
    }


    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + id_group +
                ", gradebookNumber=" + id_gradebook +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + last_name + '\'' +
                ", firstName='" + first_name + '\'' +
                ", middleName='" + middle_name + '\'' +
                '}';
    }

    public Student(/*Group group, */long gradebookNumber) {
        //this.group = group;
        this.id_gradebook = gradebookNumber;
    }

    public long getGradebookNumber() {
        return id_gradebook;
    }

    public void setGradebookNumber() {

    }

    /*public Group getGroup() {
        return group;
    }*/

    /*public void setGroup(Group group) {
        this.group = group;
    }*/
}
