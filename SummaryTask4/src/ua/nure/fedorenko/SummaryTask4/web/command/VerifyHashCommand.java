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


class VerifyHashCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String login=request.getParameter(Constant.LOGIN);
        String hash=request.getParameter("hash");
        String scope = request.getParameter("scope");
        DAOFactory daoFactory=new MySqlDAOFactory();
        String message=null;
        UserDAO userDAO=daoFactory.getUserDAO();
        User user=userDAO.getById(login);
        if(userDAO.verifyEmailHash(login,hash)&&scope.equals("activation"))
        {
            user.setStatus(Status.UNBLOCKED);
            userDAO.update(user);
            message = "Email verified successfully. Account was activated. Clic <a href=\"http://localhost:8080/login.jsp\">here</a> to login";

        }
        else if(userDAO.verifyEmailHash(login,hash)&& scope.equals("reset_password"))
        {
            user.setStatus(Status.UNBLOCKED);
            userDAO.update(user);
            request.getSession().setAttribute(Constant.USER,user);
            return Path.RESET_PASSWORD_PAGE;

        }
        request.setAttribute(Constant.MESSAGE, message);
        return Path.ADD_TARIFF_RESULT;


    }
}
