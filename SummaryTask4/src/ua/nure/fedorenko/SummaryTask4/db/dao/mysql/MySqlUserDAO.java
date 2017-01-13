package ua.nure.fedorenko.SummaryTask4.db.dao.mysql;

import ua.nure.fedorenko.SummaryTask4.db.DBManager;
import ua.nure.fedorenko.SummaryTask4.db.Fields;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.entity.Role;
import ua.nure.fedorenko.SummaryTask4.db.entity.Status;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;
import ua.nure.fedorenko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class MySqlUserDAO extends UserDAO {


    private static final String SELECT_USER_BY_ID = "SELECT * FROM provider.users WHERE login=?";
    private static final String INSERT_USER = "INSERT INTO provider.users (login, password, name, surname, email, statusId, roleId,score, verif_attempts, verif_hash) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_CLIENTS = "SELECT * FROM provider.users WHERE roleId=1 ";
    private static final String SELECT_ALL_USERS = "SELECT * FROM provider.users";
    private static final String UPDATE = "UPDATE provider.users SET password=?,name=?,surname=?,email=?,statusId=?,roleId=?,score=?,verif_attempts=?,verif_hash=? WHERE login=?";


    private static User getUserFromResultSet(ResultSet set) throws SQLException {

        return new User(set.getString(Fields.LOGIN), set.getString(Fields.PASSWORD), set.getString(Fields.NAME), set.getString(Fields.SURNAME),
                set.getString(Fields.EMAIL), Role.values()[set.getInt(Fields.ROLE_ID)], Status.values()[set.getInt(Fields.STATUS_ID)], set.getDouble(Fields.SCORE), set.getInt(Fields.VERIF_ATTEMPTS), set.getString(Fields.VERIF_HASH));

    }


    @Override
    public List<User> getAll() throws DBException {
        List<User> users = new LinkedList<>();
        Statement statement = null;
        Connection connection = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_USERS);
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(getUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DBException(Messages.EER_CANNOT_OBTAIN_ALL_USERS, e);
        } finally {
            DBManager.getInstance().close(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public User getById(String login) throws DBException {
        User user = null;
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(SELECT_USER_BY_ID);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DBException(Messages.CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            DBManager.getInstance().close(con, statement, resultSet);
        }
        return user;
    }

    @Override
    public void delete(User entity) {

    }

    //дописать!!!
    @Override
    public void create(User entity) throws DBException {

        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {
            statement = con.prepareStatement(INSERT_USER);
            int k = 1;
            statement.setString(k++, entity.getLogin());
            statement.setString(k++, entity.getPassword());
            statement.setString(k++, entity.getName());
            statement.setString(k++, entity.getSurname());
            statement.setString(k++, entity.getEmail());
            statement.setInt(k++, entity.getStatus().getStatusId());
            statement.setInt(k++, entity.getRole().getRoleId());
            statement.setDouble(k++, entity.getScore());
            statement.setInt(k++, entity.getVerif_attempts());
            statement.setString(k, entity.getVerif_hash());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }
    }

    @Override
    public void update(User user) throws DBException {
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        try {
            statement = con.prepareStatement(UPDATE);
            int k = 1;
            statement.setString(k++, user.getPassword());
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getSurname());
            statement.setString(k++, user.getEmail());
            statement.setInt(k++, user.getStatus().getStatusId());
            statement.setInt(k++, user.getRole().getRoleId());
            statement.setDouble(k++, user.getScore());
            statement.setInt(k++, user.getVerif_attempts());
            statement.setString(k++, user.getVerif_hash());
            statement.setString(k, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
        } finally {
            DBManager.getInstance().close(statement);
            DBManager.getInstance().close(con);
        }
    }

    @Override
    public boolean isUserExists(String login) throws DBException {
        boolean isUserExists = false;
        PreparedStatement statement = null;
        Connection con = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(SELECT_USER_BY_ID);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                isUserExists = true;
            }

        } catch (SQLException e) {
            throw new DBException(Messages.CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            DBManager.getInstance().close(con, statement, resultSet);
        }
        return isUserExists;
    }

    @Override
    public List<User> getClients() throws DBException {
        List<User> users = new LinkedList<>();
        Statement statement = null;
        Connection connection = DBManager.getInstance().getConnection();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_CLIENTS);
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(getUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DBException(Messages.EER_CANNOT_OBTAIN_ALL_USERS, e);
        } finally {
            DBManager.getInstance().close(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public boolean verifyEmailHash(String login, String hash) throws DBException {
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = null;
            boolean verified = false;
            ResultSet res=null;
            try {
                ps = conn.prepareStatement("SELECT 1 FROM provider.users WHERE login = ? AND verif_hash = ?");
                ps.setString(1, login);
                ps.setString(2, hash);
                res = ps.executeQuery();
                if (res != null) {
                    while (res.next()) {
                        verified = true;
                    }
                }

            } catch (SQLException e) {

                throw new DBException();
            }
            finally {
                DBManager.getInstance().close(conn,ps,res);
            }
            return verified;
        }
    }

