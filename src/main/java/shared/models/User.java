package shared.models;

public class User {

    private String Username;
    private String Password;

    public User(String username, String password){
        this.Username = username;
        this.Password = password;
    }

    public User(){

    }

    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

