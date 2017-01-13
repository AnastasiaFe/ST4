package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Service;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



class EditTariffCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String name=request.getParameter(Constant.TARIFF_NAME);
        String desc=request.getParameter(Constant.TARIFF_DESC);
        double price=Double.parseDouble(request.getParameter(Constant.TARIFF_PRICE));
        int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        int serviceId=Integer.parseInt(request.getParameter(Constant.SERVICE));
        DAOFactory factory=new MySqlDAOFactory();
        ServiceDAO serviceDAO=factory.getServiceDAO();
        Service s=serviceDAO.getById(serviceId);
        Tariff tariff=new Tariff(tariffId,name,desc,price,s);
        TariffDAO tariffDAO=factory.getTariffDAO();
        tariffDAO.update(tariff);
        request.setAttribute(Constant.SERVICE_ID,serviceId);
        request.setAttribute(Constant.MESSAGE,Constant.TARIFF_EDITED_RESULT);
        return Path.ADD_TARIFF_RESULT;


    }
}
