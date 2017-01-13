package ua.nure.fedorenko.SummaryTask4.db.dao.factory;

import ua.nure.fedorenko.SummaryTask4.db.dao.generics.PaymentDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;

/**
 * Created by Anastasia on 06.01.2017.
 */
public abstract class DAOFactory {
    public enum Factories {
        MYSQL
    }

    public abstract UserDAO getUserDAO();
    public abstract ServiceDAO getServiceDAO();
    public abstract TariffDAO getTariffDAO();
    public abstract PaymentDAO getPaymentDAO();


    public static DAOFactory getDAOFactory(Factories factoryName) {
        switch (factoryName) {
            case MYSQL:
                return new MySqlDAOFactory();
            default:
                return null;
        }
    }
}
