package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia on 08.01.2017.
 */
public class DeleteTariffCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        int serviceId=Integer.parseInt(request.getParameter(Constant.SERVICE_ID));
        DAOFactory factory=new MySqlDAOFactory();
        TariffDAO tariffDAO=factory.getTariffDAO();
        Tariff tariffToDelete=tariffDAO.getById(tariffId);
        tariffDAO.delete(tariffToDelete);
        request.setAttribute(Constant.MESSAGE,Constant.TARIFF_DELETED_RESULT);
        request.setAttribute(Constant.SERVICE_ID,serviceId);
        return Path.ADD_TARIFF_RESULT;
    }
}
