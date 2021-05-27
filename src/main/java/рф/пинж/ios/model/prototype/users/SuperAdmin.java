package рф.пинж.ios.model.prototype.users;

import java.util.List;

public class SuperAdmin extends Admin {
    {
        super.table = "admins";
        super.columns = List.of("id_institutes", "login", "password", "email", "lastName", "firstName", "middleName");
    }

    protected int id_institutes;
}
