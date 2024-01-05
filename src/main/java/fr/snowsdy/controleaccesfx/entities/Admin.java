package fr.snowsdy.controleaccesfx.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("A")
public class Admin extends User implements Serializable {

    private String login;

    private String password;

    public Admin() {
    }

    public Admin(String name, String login, String password) {
        super(name);
        this.login = login;
        this.setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String mdp) {
        this.password = codeMD5(mdp);
    }

    public static String codeMD5(String msg) {
        StringBuilder code = new StringBuilder();
        byte[] b;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            b = md.digest(msg.getBytes());
            for (int j : b) {
                int x = j;

                if (x < 0) {
                    x += 256;
                }

                String s = String.format("%02x", x);
                code.append(s);
            }
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }

        return code.toString();
    }

    @Override
    public String toString() {
        return "(" + this.login + ") " + super.toString();
    }
}
