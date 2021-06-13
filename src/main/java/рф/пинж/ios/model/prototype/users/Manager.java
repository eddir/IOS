package рф.пинж.ios.model.prototype.users;

import java.util.List;

public class Manager extends User {
    //protected Institute institute;
    {
        super.table = "managers";
        super.columns = List.of("id_institutes", "login", "password", "email", "lastName", "firstName", "middleName");
    }

    protected int id_institutes;
}
