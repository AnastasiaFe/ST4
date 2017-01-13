package ua.nure.fedorenko.SummaryTask4.db.dao.generics;

import ua.nure.fedorenko.SummaryTask4.db.entity.Payment;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.util.List;


public abstract class PaymentDAO extends AbstractDAO<Integer,Payment> {

    public abstract void insertPaymentTariff(int paymentId, List<Tariff>tariffs, Connection con) throws DBException;
    public abstract void doPayment(User user,List<Tariff>tariffs) throws DBException;
    public abstract int createForTransaction(Payment payment, Connection con) throws DBException;
}
