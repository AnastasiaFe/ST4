package ua.nure.fedorenko.SummaryTask4;

import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import javax.mail.MessagingException;

/**
 * Created by Anastasia on 05.01.2017.
 */
public class M {
    public static void main(String[] args) throws DBException, MessagingException {
      // MailHelper2.sendMail("anastasiia.fedorenko@nure.ua","help","Hello, Nastia");
        DAOFactory daoFactory=new MySqlDAOFactory();


    }
}
