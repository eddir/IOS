package рф.пинж.ios.model.prototype.users;

import java.util.List;

public class Teacher extends User {
    //private Cathedra cathedra;
    {
        super.table = "teachers";
        super.columns = List.of("id_cathedra", "login", "password", "email", "lastName", "firstName", "middleName");
    }

    private int id_cathedra;
}
