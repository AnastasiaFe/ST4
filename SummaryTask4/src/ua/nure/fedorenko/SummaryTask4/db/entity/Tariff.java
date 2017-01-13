package ua.nure.fedorenko.SummaryTask4.db.entity;

/**
 * Created by Anastasia on 02.01.2017.
 */
public class Tariff {


    private int tariffId;
    private String name;
    private String desc;
    private double price;
    private Service service;

    public Tariff(String name, String desc, double price, Service service) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.service = service;
    }

    public Tariff(int tariffId, String name, String desc, double price, Service service) {

        this.tariffId = tariffId;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.service = service;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
