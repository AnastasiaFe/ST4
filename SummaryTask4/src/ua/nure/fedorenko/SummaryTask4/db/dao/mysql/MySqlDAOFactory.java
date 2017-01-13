package ua.nure.fedorenko.SummaryTask4.db.dao.mysql;

import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.PaymentDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class MySqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new MySqlServiceDAO();
    }

    @Override
    public TariffDAO getTariffDAO() {
        return new MySqlTariffDAO();
    }

    @Override
    public PaymentDAO getPaymentDAO() {
        return new MySqlPaymentDAO();
    }
}
