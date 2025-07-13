public class UserDAO {
    private static final Map<String, String> users = new HashMap<>(); // mock DB

    public static boolean emailExists(String email) {
        return users.containsKey(email);
    }

    public static boolean registerUser(String email, String password, String phone) {
        if (users.containsKey(email)) return false;
        users.put(email, password); // In reality, you'd hash the password and use JDBC
        return true;
    }

    public static boolean authenticateUser(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }
}
