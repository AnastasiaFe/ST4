package ua.nure.fedorenko.SummaryTask4.db.entity;

/**
 * Created by Anastasia on 02.01.2017.
 */
public class Service {

    private int serviceId;
    private String name;

    public String getName() {
        return name;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Service(int serviceId, String name) {
        this.serviceId = serviceId;
        this.name = name;
    }

    public Service(String name) {
        this.name = name;
    }
}
