package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Role;
import ua.nure.fedorenko.SummaryTask4.db.entity.Status;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.db.security.PasswordHasher;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.util.mail.MailUtil;
import ua.nure.fedorenko.SummaryTask4.util.verification.StringGenerator;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Anastasia on 09.01.2017.
 */
public class AddClientCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String login=request.getParameter(Constant.LOGIN);
        String password=request.getParameter(Constant.PASSWORD);
        String name=request.getParameter(Constant.NAME);
        String surname=request.getParameter(Constant.SURNAME);
        String email=request.getParameter(Constant.EMAIL);
        String passwordHash=null;
        try {
           passwordHash = PasswordHasher.hash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String verif_hash= StringGenerator.prepareRandomString(30);
        DAOFactory daoFactory=new MySqlDAOFactory();
        UserDAO userDAO=daoFactory.getUserDAO();
        if(!userDAO.isUserExists(login))
        {
            User newUser=new User(login,passwordHash,name,surname,email, Role.CLIENT, Status.UNVERIFIED,0,0,verif_hash);
            userDAO.create(newUser);
            //here should be email sending
            try {
                MailUtil.sendEmailRegistrationLink(newUser.getLogin(),newUser.getEmail(),newUser.getVerif_hash());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            request.setAttribute(Constant.MESSAGE,Constant.CLIENT_ADDED_RESULT);
            return Path.ADD_TARIFF_RESULT;
        }
        else
        {
            request.setAttribute(Constant.MESSAGE,Constant.SUCH_LOGIN_ALREADY_REGISTERED);
            return Path.ERROR_PAGE;
        }
    }
}
