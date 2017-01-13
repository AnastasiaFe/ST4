package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


class AddTarrifCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int serviceId=Integer.parseInt(request.getParameter("serviceId"));
        String name=request.getParameter("tariffName");
        String desc=request.getParameter("tariffDesc");
        double price=Double.parseDouble(request.getParameter("tariffPrice"));
        DAOFactory daoFactory=new MySqlDAOFactory();
        ServiceDAO serviceDAO=daoFactory.getServiceDAO();
        Tariff tariff=new Tariff(name,desc,price,serviceDAO.getById(serviceId));
        TariffDAO tariffDAO=daoFactory.getTariffDAO();
        tariffDAO.create(tariff);
        request.setAttribute("serviceId",serviceId);
        request.setAttribute("message","The tariff was successfully added");
        return Path.ADD_TARIFF_RESULT;
    }
}
