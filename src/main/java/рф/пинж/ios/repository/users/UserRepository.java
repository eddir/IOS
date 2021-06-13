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

    public static void changeUserPassword (String login) {
        Random random = new Random();
        int password = 0;
        for (int i = 0; i < 7; i++) {
            password += random.nextInt(10) * (int)Math.pow(10, i);
        }
        try {
            changeUserPassword(login, Integer.toString(password));
        }
        catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
        }
    }

    public static void changeUserPassword (String login, String password) throws Exception {
        String hashPassword = User.sha512Generator(password);
        Query updatePassword = Server.getInstance().getDatabase().createQuery(
                "UPDATE users " +
                        "SET password = '" + hashPassword + "' " +
                        "WHERE login = '" + login + "'"
        );
        updatePassword.executeUpdate();


//        Properties properties = System.getProperties();
//        properties.setProperty("mail.transport.protocol", "smtps");
//        properties.setProperty("mail.smtps.auth", "true");
//        properties.setProperty("mail.smtps.host", "smtp.gmail.com");
//        properties.setProperty("mail.smtps.user", "ios.sstu.ru@gmail.com");
//
//        Session mailSession = Session.getDefaultInstance(properties);
//        MimeMessage message = new MimeMessage(mailSession);
//        message.setFrom(new InternetAddress("ios.sstu.ru@gmail.com"));
//        message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(getEmailByLogin(login))});
//        message.setSubject("Ваш пароль был изменён");
//        message.setText("Ваш новый пароль для входа в ИОС: " + password);
//
//        Transport transport = mailSession.getTransport();
//        transport.connect("ios.sstu.ru@gmail.com", "20012308");
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
//
//        System.out.println(password);
    }

    public static void registerNewUser() {

    }


}
