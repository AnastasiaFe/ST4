package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Role;
import ua.nure.fedorenko.SummaryTask4.db.entity.Status;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.db.security.PasswordHasher;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.exception.Messages;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anastasia on 04.01.2017.
 */
public class LoginCommand extends Command {
    // private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        // LOG.debug("Command starts");
        DAOFactory factory = new MySqlDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        HttpSession session = request.getSession();
        String login = request.getParameter(Constant.LOGIN);
        String password = request.getParameter(Constant.PASSWORD);
        //   LOG.trace("Request parameter: loging --> " + login);

        if (!isDataValid(login, password)) {
            throw new AppException(Messages.ERR_INVALID_DATA);
        }
        String forward = Path.ERROR_PAGE;
        if (userDAO.isUserExists(login)) {
            User currentUser = userDAO.getById(login);
            try {
                if (PasswordHasher.hash(password).equals(currentUser.getPassword())) {
                    session.setAttribute(Constant.USER, currentUser);
                    session.setAttribute(Constant.USER_ROLE,currentUser.getRole().getName());
                    Role role = currentUser.getRole();
                    if (role == Role.ADMIN) {
                        List<User>allUsers=userDAO.getClients();
                        session.setAttribute(Constant.USERS,allUsers);
                        forward = Path.USERS_LIST_PAGE;

                    } else {
                        if(currentUser.getStatus()== Status.UNVERIFIED)
                        {
                            forward=Path.UNVERIFIED_CLIENT_PAGE;
                        }
                        else {
                            session.setAttribute(Constant.SELECTED_TARIFFS, new ArrayList<Tariff>());
                            forward = Path.CLIENT_MAIN_PAGE;
                        }
                    }
                } else {
                    response.sendRedirect(Path.NO_SUCH_USER_PAGE);
                    return null;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect(Path.NO_SUCH_USER_PAGE);
            return null;
        }

        return forward;


    }

    private boolean isDataValid(String login, String password) {
        return login.length() >= 3 && password.length() >= 3;
    }
}
