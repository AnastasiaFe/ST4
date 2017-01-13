package ua.nure.fedorenko.SummaryTask4.db.dao.generics;

import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public  abstract class UserDAO extends AbstractDAO<String,User> {

    public abstract boolean isUserExists(String login) throws DBException;
    public abstract List<User>getClients() throws DBException;
    public abstract boolean verifyEmailHash(String login, String hash)throws DBException;

}
