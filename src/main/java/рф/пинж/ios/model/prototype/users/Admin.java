package рф.пинж.ios.model.prototype.users;

import java.util.List;

public class Admin extends Manager {
    {
        super.table = "admins";
        super.columns = List.of("id_institutes", "login", "password", "email", "lastName", "firstName", "middleName");
    }

    protected int id_institutes;
}
