package ua.nure.fedorenko.SummaryTask4.db.dao.mysql;

import ua.nure.fedorenko.SummaryTask4.db.DBManager;
import ua.nure.fedorenko.SummaryTask4.db.Fields;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
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
public class MySqlServiceDAO extends ServiceDAO {

    private static final String SELECT_SERVICE_BY_ID="SELECT * FROM provider.services WHERE serviceId=?";
    private static final String SELECT_ALL_SERVICES="SELECT * FROM provider.services";

    private static Service getServiceFromResultSet(ResultSet set) throws SQLException {
        return new Service(set.getInt(Fields.SERVICE_ID),set.getString(Fields.SERVICE_NAME));

    }

    @Override
    public List<Service> getAll() throws DBException {
        List<Service> services = new LinkedList<>();
        Statement statement = null;
        Connection connection = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SERVICES);
            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(getServiceFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_SERVICES, e);
        } finally {
            DBManager.getInstance().close(connection, statement, resultSet);
        }
        return services;

    }

    @Override
    public Service getById(Integer id) throws DBException {

        Service service = null;
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(SELECT_SERVICE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                service = getServiceFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SERVICE_BY_ID, e);
        } finally {
            DBManager.getInstance().close(con, statement, resultSet);
        }
        return service;
    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public void create(Service entity) {

    }

    @Override
    public void update(Service entity) {

    }
}
