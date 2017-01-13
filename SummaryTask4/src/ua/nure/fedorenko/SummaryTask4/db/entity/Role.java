package ua.nure.fedorenko.SummaryTask4.db.entity;

/**
 * Created by Anastasia on 02.01.2017.
 */
public enum Role {
    ADMIN(0), CLIENT(1);
    private int roleId;
    Role(int id)
    {
        roleId=id;

    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
