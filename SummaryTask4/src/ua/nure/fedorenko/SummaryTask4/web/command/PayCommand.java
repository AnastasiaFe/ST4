package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.PaymentDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class PayCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<Tariff>tariffs=(List<Tariff>)request.getSession().getAttribute(Constant.SELECTED_TARIFFS);
        User user=(User)request.getSession().getAttribute(Constant.USER);
        DAOFactory daoFactory=new MySqlDAOFactory();
        PaymentDAO paymentDAO=daoFactory.getPaymentDAO();
        paymentDAO.doPayment(user,tariffs);
        UserDAO userDAO=daoFactory.getUserDAO();
        double paymentSum=0;
        for (Tariff t:tariffs
             ) {
            paymentSum+=t.getPrice();

        }
        user.setScore(user.getScore()-paymentSum);
        userDAO.update(user);
        request.getSession().setAttribute(Constant.SELECTED_TARIFFS,new ArrayList<>());
        request.setAttribute(Constant.MESSAGE,"Payment was successfully made. Thank you");

        return Path.ADD_TARIFF_RESULT;
    }
}
