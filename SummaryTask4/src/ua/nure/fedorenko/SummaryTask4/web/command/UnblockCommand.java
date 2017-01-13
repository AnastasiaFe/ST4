package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Status;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia on 09.01.2017.
 */
public class UnblockCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String login=request.getParameter(Constant.LOGIN);
        DAOFactory daoFactory=new MySqlDAOFactory();
        UserDAO userDAO=daoFactory.getUserDAO();
        User userToUnblock=userDAO.getById(login);
       userToUnblock.setStatus(Status.UNBLOCKED);
        userDAO.update(userToUnblock);
        return Path.USERS_LIST_PAGE;
    }
}
