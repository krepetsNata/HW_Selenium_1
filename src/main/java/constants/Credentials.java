package constants;

public enum Credentials {
    CORRECT_CRED("ivanhorintest@gmail.com", "ivanhorintestPassword"),
    INCORRECT_CRED("incorrectmail@gmail.com", "incorrectPassword");

    private String email;
    private String password;

    Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
