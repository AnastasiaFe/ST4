package ua.nure.fedorenko.SummaryTask4.db.entity;

/**
 * Created by Anastasia on 02.01.2017.
 */
public enum  Status {

    BLOCKED(0),UNBLOCKED(1),UNVERIFIED(2);
    private int statusId;
    Status(int id)
    {
        statusId=id;
    }

    public int getStatusId() {
        return statusId;
    }
    public String getName() {
        return name().toLowerCase();
    }


}
