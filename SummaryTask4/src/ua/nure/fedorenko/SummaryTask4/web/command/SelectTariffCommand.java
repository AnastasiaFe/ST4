package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
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


public class SelectTariffCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        User currentUser=(User)request.getSession().getAttribute(Constant.USER);
        DAOFactory daoFactory=new MySqlDAOFactory();
        TariffDAO tariffDAO=daoFactory.getTariffDAO();
        Tariff tariff=tariffDAO.getById(tariffId);
        List<Tariff>selected=(List<Tariff>)request.getSession().getAttribute(Constant.SELECTED_TARIFFS);
        selected.add(tariff);
        double price=0;
        for (Tariff t:selected
             ) {
            price+=t.getPrice();

        }
        if(price>currentUser.getScore())
        {
            currentUser.setStatus(Status.BLOCKED);
            request.setAttribute(Constant.MESSAGE,"Sorry, you were blocked. Please fill your score");
        }


        request.setAttribute(Constant.SERVICE_ID,tariff.getService());
        return Path.CLIENT_SERVICE_TARIFF;
    }
}
