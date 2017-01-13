package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.db.security.PasswordHasher;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


class ChangePasswordCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String pas1Hash=null;
        String pas2Hash=null;
        String message=null;
        String forward= Path.ERROR_PAGE;
        try {
           pas1Hash = PasswordHasher.hash(request.getParameter("password1"));
            pas2Hash=PasswordHasher.hash(request.getParameter("password2"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user=(User)request.getSession().getAttribute(Constant.USER);
        if(pas1Hash!=null&& pas2Hash!=null&&pas1Hash.equals(pas2Hash))
        {
            DAOFactory daoFactory=new MySqlDAOFactory();
            UserDAO userDAO=daoFactory.getUserDAO();
            user.setPassword(pas1Hash);
            userDAO.update(user);
            message="Your password has been changed successfully";
            forward=Path.ADD_TARIFF_RESULT;
        }
        else if(!pas1Hash.equals(pas2Hash))
        {
            message="Passwords don't match";
            forward=Path.ADD_TARIFF_RESULT;
        }
        request.setAttribute(Constant.MESSAGE,message);
        return forward;


    }
}
