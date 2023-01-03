import java.io.Serializable;

public class Etudiant implements Serializable{
    //Attributs
    public String login;
    public String pswd;
    public boolean choixfait;

    //Constructeur
    public Etudiant() {
        this.login = "";
        this.pswd = "";
        this.choixfait = false;
    }


    public Etudiant(String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
        this.choixfait = false;
    }
    

    //setter et getter
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String pswd) {
        this.pswd = pswd;
    }

    public void setChoixfait(boolean bool) {
        this.choixfait = bool;
    }

    public String getLogin() { 
        return login;
    }
    public String getPassword() { 
        return pswd;
    }
    public boolean getChoixfait() {
        return choixfait;
    }
    public String toString() {
        return this.login + " " + this.pswd + " ";
    }

}