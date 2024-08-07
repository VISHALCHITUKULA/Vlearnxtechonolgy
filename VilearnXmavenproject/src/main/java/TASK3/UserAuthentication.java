package TASK3;

import java.sql.SQLException;

public class UserAuthentication {
    public static boolean authenticate(String username, String password) throws SQLException {
        return UserDAO.authenticate(username, password);
    }
}
