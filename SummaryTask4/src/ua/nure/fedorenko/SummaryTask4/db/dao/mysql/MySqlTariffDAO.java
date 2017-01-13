package ua.nure.fedorenko.SummaryTask4.db.dao.mysql;

import ua.nure.fedorenko.SummaryTask4.db.DBManager;
import ua.nure.fedorenko.SummaryTask4.db.Fields;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.entity.Service;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;
import ua.nure.fedorenko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class MySqlTariffDAO extends TariffDAO {

    private static final String SELECT_ALL_TARIFFS = "SELECT * FROM provider.tariffs";
    private static final String SELECT_TARIFF_BY_ID = "SELECT * FROM provider.tariffs WHERE tariffId=?";
    private static final String INSERT_TARIFF = "INSERT INTO provider.tariffs (name, description, price, service) VALUES(?,?,?,?)";
    private static final String UPDATE_TARIFF = "UPDATE provider.tariffs SET name=?,description=?,price=? WHERE tariffId=?";
    private static final String DELETE_TARIFF = "DELETE FROM provider.tariffs WHERE tariffId=?";
    private static final String SELECT_TARIFFS_BY_SERVICE = "SELECT * FROM provider.tariffs WHERE service=?";


    private static Tariff getTariffFromResultSet(ResultSet set) throws SQLException {
        int serviceId = set.getInt(Fields.SERVICE);
        Service service = null;
        try {
            service = new MySqlServiceDAO().getById(serviceId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new Tariff(set.getInt(Fields.TARIFF_ID), set.getString(Fields.TARIFF_NAME), set.getString(Fields.TARIFF_DESC), set.getDouble(Fields.TARIFF_PRICE), service);

    }

    @Override
    public List<Tariff> getAll() throws DBException {
        List<Tariff> tariffs = new LinkedList<>();
        Statement statement = null;
        Connection connection = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_TARIFFS);
            if (resultSet != null) {
                while (resultSet.next()) {
                    tariffs.add(getTariffFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TARIFFS, e);
        } finally {
            DBManager.getInstance().close(connection, statement, resultSet);
        }
        return tariffs;

    }

    @Override
    public Tariff getById(Integer id) throws DBException {
        Tariff tariff = null;
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(SELECT_TARIFF_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tariff = getTariffFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TARIFF_BY_ID, e);
        } finally {
            DBManager.getInstance().close(con, statement, resultSet);
        }
        return tariff;
    }

    @Override
    public void delete(Tariff entity) throws DBException {

        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {

            statement = con.prepareStatement(DELETE_TARIFF);
            statement.setInt(1, entity.getTariffId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_DELETE_TARIFF, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }

    }

    @Override
    public void create(Tariff entity) throws DBException {

        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {
            statement = con.prepareStatement(INSERT_TARIFF);
            int k=1;
            statement.setString(k++,entity.getName());
            statement.setString(k++,entity.getDesc());
            statement.setDouble(k++,entity.getPrice());
            statement.setInt(k,entity.getService().getServiceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_TARIFF, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }
    }

    @Override
    public void update(Tariff entity) throws DBException {
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {

            statement = con.prepareStatement(UPDATE_TARIFF);
            int k = 1;
            statement.setString(k++, entity.getName());
            statement.setString(k++, entity.getDesc());
            statement.setDouble(k++, entity.getPrice());
            statement.setInt(k, entity.getTariffId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TARIFF, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }
    }

    @Override
    public List<Tariff> getAllServiceTariffs(int serviceId) throws DBException {

        List<Tariff> tariffs = new LinkedList<>();
        PreparedStatement statement = null;
        Connection connection = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_TARIFFS_BY_SERVICE);
            statement.setInt(1,serviceId);
            resultSet=statement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    tariffs.add(getTariffFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TARIFFS_BY_SERVICE, e);
        } finally {
            DBManager.getInstance().close(connection, statement, resultSet);
        }
        return tariffs;
    }
}
