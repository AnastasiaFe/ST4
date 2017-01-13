package ua.nure.fedorenko.SummaryTask4.db.entity;

/**
 * Created by Anastasia on 02.01.2017.
 */
public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private Status status;
    private double score;
    private int verif_attempts;
    private String verif_hash;

    public User(String login, String password, String name,String surname, String email, Role role, Status status, double score, int verif_attempts, String verif_hash) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname=surname;
        this.email = email;
        this.role = role;
        this.status = status;
        this.score = score;
        this.verif_attempts = verif_attempts;
        this.verif_hash = verif_hash;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getVerif_attempts() {
        return verif_attempts;
    }

    public void setVerif_attempts(int verif_attempts) {
        this.verif_attempts = verif_attempts;
    }

    public String getVerif_hash() {
        return verif_hash;
    }

    public void setVerif_hash(String verif_hash) {
        this.verif_hash = verif_hash;
    }
}
