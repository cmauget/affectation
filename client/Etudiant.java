import java.util.*;
import java.io.Serializable;

public class Etudiant implements Serializable{
    //Attributs
    public String login;
    public String pswd;

    //Constructeur
    public Etudiant() {
        this.login = "";
        this.pswd = "";
    }


    public Etudiant(String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
    }

    //setter et getter
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String pswd) {
        this.pswd = pswd;
    }
    public String getLogin() { 
        return login;
    }
    public String getPassword() { 
        return pswd;
    }
    public String toString() {
        return this.login + " " + this.pswd + " ";
    }

}