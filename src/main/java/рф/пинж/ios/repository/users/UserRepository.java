package рф.пинж.ios.repository.users;

import org.sql2o.Query;
import рф.пинж.ios.IOS;
import рф.пинж.ios.Server;
import рф.пинж.ios.model.prototype.users.*;
import рф.пинж.ios.repository.IRepository;
import рф.пинж.ios.repository.Repository;
import рф.пинж.ios.utils.MainLogger;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static рф.пинж.ios.utils.Utils.sha512Generator;

public class UserRepository extends Repository<User> implements IRepository<User> {
    public UserRepository() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(User.class);
    }

    public static User getUserByLogin(String login) {
        User user = null;
        try {
            Map<Class<? extends User>, String> roles = new HashMap<>();
            roles.put(Student.class, "students");
            roles.put(Teacher.class, "teachers");
            //roles.put(Manager.class, "managers");
            //roles.put(Admin.class, "admins");
            for (Map.Entry<Class<? extends User>, String> entry: roles.entrySet()) {
                Query getStudentByLogin = Server.getInstance().getDatabase().createQuery(
                        "SELECT * " +
                                "FROM users " +
                                "JOIN " + entry.getValue() + " " +
                                "USING (id) " +
                                "WHERE login = '" + login + "'"
                );
                user = getStudentByLogin.executeAndFetchFirst(entry.getKey());
                if (user != null)
                    break;
            }
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
        }
        return user;
    }

    public static String getEmailByLogin(String login) {
        String email = null;
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
                    "SELECT email " +
                            "FROM users " +
                            "WHERE login = '" + login + "'"
            );
            email = query.executeScalarList(String.class).toString();
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
        }
        return email;
    }

    public static void changeUserPassword(String login, String password) throws Exception {
        String hashPassword = sha512Generator(password);
        Query updatePassword = Server.getInstance().getDatabase().createQuery(
                "UPDATE users " +
                        "SET password = '" + hashPassword + "' " +
                        "WHERE login = '" + login + "'"
        );
        updatePassword.executeUpdate();
    }

    public static int countEmails(String email) {
        Query countEmails = Server.getInstance().getDatabase().createQuery(
                "SELECT COUNT email " +
                        "FROM users" +
                        "WHERE email = :email"
        );
        countEmails.addParameter("email", email);
        return countEmails.executeScalar(Integer.class);
    }

    public static String getLoginByEmail(String email) {
        String login = null;
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
                    "SELECT login " +
                            "FROM users " +
                            "WHERE email = :email"
            );
            query.addParameter("email", email);
            login = query.executeScalarList(String.class).toString();
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
        }
        return login;
    }

    public static int getIdByLogin(String login) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
                    "SELECT id " +
                            "FROM users " +
                            "WHERE login = :login"
            );
            query.addParameter("login", login);
            return query.executeScalar(Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteById(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
               "DELETE " +
                       "FROM users " +
                       "WHERE id = :id"
            );
            query.addParameter("id", id);
            return query.executeUpdate().getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void changeUserDataByLogin(String login, String param, String value) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
                    "UPDATE " +
                            "SET " + param + " = :value " +
                            "WHERE login = :login"
            );
            query.addParameter("value", value);
            query.addParameter("login", login);
            query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLoginById(int id) {
        try {
            Query query = Server.getInstance().getDatabase().createQuery(
                    "SELECT login " +
                            "FROM users " +
                            "WHERE id = :id"
            );
            query.addParameter("id", id);
            return query.executeScalar(String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void registerNewUser() {

    }


}
