package ua.nure.fedorenko.SummaryTask4.web.command;
import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Status;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

class FillScoreCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        double money=Double.parseDouble(request.getParameter("money"));
        DAOFactory daoFactory=new MySqlDAOFactory();
        UserDAO userDAO=daoFactory.getUserDAO();
        User currentUser=(User)request.getSession().getAttribute(Constant.USER);
        double score=currentUser.getScore()+money;
        currentUser.setScore(score);
        userDAO.update(currentUser);
        List<Tariff>selected=(List<Tariff>)request.getSession().getAttribute(Constant.SELECTED_TARIFFS);
        double price=0;
        for (Tariff t:selected)
        {
            price+=t.getPrice();
        }
        if(currentUser.getScore()>=price)
        {
            currentUser.setStatus(Status.UNBLOCKED);
        }
        return Path.SCORE_PAGE;

    }
}
