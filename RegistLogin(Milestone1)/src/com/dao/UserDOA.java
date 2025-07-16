private static final Map<String, User> users = new HashMap<>();

public static boolean registerUser(User user) {
    if (users.containsKey(user.getEmail())) return false;
    users.put(user.getEmail(), user);
    return true;
}

public static boolean authenticateUser(String email, String password, String phone) {
    if (!users.containsKey(email)) return false;

    User user = users.get(email);
    return user.getPassword().equals(password) && user.getPhone().equals(phone);
}
