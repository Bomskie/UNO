package Client;

public class Session {
    private static Session s = new Session();
    private String userName = "";
    private Session(){

    }

    public static Session getInstance(){return s;}

    public void setUserName(String userName){this.userName = userName;}
}
