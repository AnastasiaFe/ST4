package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Role;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.util.sort.SortByAlphabetAsc;
import ua.nure.fedorenko.SummaryTask4.util.sort.SortByAlphabetDesc;
import ua.nure.fedorenko.SummaryTask4.util.sort.SortByPriceAsc;
import ua.nure.fedorenko.SummaryTask4.util.sort.SortByPriceDesc;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

class SortCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String sort = request.getParameter(Constant.SORT);
        int serviceId = Integer.parseInt(request.getParameter(Constant.SERVICE_ID));
        DAOFactory daoFactory = new MySqlDAOFactory();
        TariffDAO tariffDAO = daoFactory.getTariffDAO();
        List<Tariff> tariffsToSort = tariffDAO.getAllServiceTariffs(serviceId);
        switch (sort) {
            case Constant.ALPH_ASC:
                Collections.sort(tariffsToSort, new SortByAlphabetAsc());
                break;
            case Constant.ALPH_DESC:
                Collections.sort(tariffsToSort, new SortByAlphabetDesc());
                break;
            case Constant.PRICE_ASC:
                Collections.sort(tariffsToSort, new SortByPriceAsc());
                break;
            case Constant.PRICE_DESC:
                Collections.sort(tariffsToSort, new SortByPriceDesc());
                break;

        }
        request.setAttribute(Constant.SERVICE_ID, serviceId);
        request.setAttribute(Constant.TARIFFS, tariffsToSort);
       User currentUser=(User)request.getSession().getAttribute(Constant.USER);
        if(currentUser.getRole()== Role.ADMIN)
        {
            return Path.ADMIN_SERVICE_TARIFF;
        }
        else
        {
            return Path.CLIENT_SERVICE_TARIFF;
        }

    }
}
