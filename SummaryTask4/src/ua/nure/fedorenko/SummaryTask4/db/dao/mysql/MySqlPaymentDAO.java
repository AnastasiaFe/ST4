package ua.nure.fedorenko.SummaryTask4.db.dao.mysql;

import ua.nure.fedorenko.SummaryTask4.db.DBManager;
import ua.nure.fedorenko.SummaryTask4.db.Fields;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.PaymentDAO;
import ua.nure.fedorenko.SummaryTask4.db.entity.Payment;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;
import ua.nure.fedorenko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class MySqlPaymentDAO extends PaymentDAO {

    private static final String INSERT_PAYMENT = "INSERT INTO provider.payments (login) VALUES(?)";
    private static final String SELECT_PAYMENT_BY_ID = "SELECT * FROM provider.payments WHERE paymentId=?";
    private static final String INSERT_PAYMENT_TARIFF = "INSERT INTO provider.payments_tariffs(paymentId, tariffId) VALUES(?,?)";

    private Payment getPaymentFromResultset(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(Fields.LOGIN);
        User user = null;
        try {
            user = new MySqlUserDAO().getById(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new Payment(resultSet.getInt(Fields.PAYMENT_ID), user);
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public Payment getById(Integer id) throws DBException {

        Payment payment = null;
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(SELECT_PAYMENT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = getPaymentFromResultset(resultSet);
            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_PAYMENT_BY_ID, e);
        } finally {
            DBManager.getInstance().close(con, statement, resultSet);
        }
        return payment;
    }

    @Override
    public void delete(Payment entity) {

    }

    @Override
    public void create(Payment entity) throws DBException {
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {
            statement = con.prepareStatement(INSERT_PAYMENT);
            statement.setString(1, entity.getUser().getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_PAYMENT, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }
    }

    @Override
    public void update(Payment entity) {

    }

    @Override
    public void insertPaymentTariff(int paymentId, List<Tariff> tariffs, Connection con) throws DBException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(INSERT_PAYMENT_TARIFF);
            int i = 0;
            int k = 1;
            statement.setInt(k++, paymentId);
            while (i < tariffs.size()) {
                statement.setInt(k, tariffs.get(i).getTariffId());
                statement.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_PAYMENT_TARIFF, e);
        } finally {
            DBManager.getInstance().close(statement);

        }

    }

    @Override
    public void doPayment(User user, List<Tariff>tariff) throws DBException {

        Connection connection = DBManager.getInstance().getConnection();
        Payment payment = new Payment(user);
        try {
            connection.setAutoCommit(false);
            int paymentId = createForTransaction(payment,connection);
            insertPaymentTariff(paymentId, tariff,connection);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DBException(Messages.ERR_CANNOT_DO_TRANSACTION, e1);
            }

        } finally {
            DBManager.getInstance().close(connection);
        }

    }

    @Override
    public int createForTransaction(Payment payment, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getUser().getLogin());
            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    payment.setPaymentId(key);
                    return key;
                }

            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_PAYMENT, e);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);

        }
        throw new DBException();
    }


}
