package ua.nure.fedorenko.SummaryTask4.web.command;
import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.ServiceDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Role;
import ua.nure.fedorenko.SummaryTask4.db.entity.Service;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

class ViewPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
     String page=request.getParameter(Constant.PAGE);
        switch (page)
        {
            case Constant.USERS:return viewAllUsers(request);
            case Constant.ALL_SERVICES:return viewAllServices(request);
            case Constant.SETTINGS:return viewSettings();
            case Constant.TARIFFS:return viewServiceTariffs(request);
            case Constant.ADD_TARIFF:return viewAddTariff(request);
            case Constant.EDIT_TARIFF:return viewEdit(request);
            case Constant.CONFIRM_DELETE:return viewDeleteConfirm(request);
            case Constant.ADD_CLIENT:return viewAddClientPage(request);
            case Constant.SCORE:return viewScorePage(request,response);
            case Constant.BASKET:return viewBasketPage(request);
            case Constant.FORGET_PASSWORD_PAGE:return viewForgetPassword();
            default:request.setAttribute(Constant.MESSAGE,Constant.UNKNOWN_COMMAND); return Path.ERROR_PAGE;
        }
    }

    private String viewForgetPassword()
    {
        return Path.FORGET_PASSWORD_PAGE;
    }
    private String viewBasketPage(HttpServletRequest request)
    {
        return Path.BASKET_PAGE;
    }
    private String viewScorePage(HttpServletRequest request,HttpServletResponse response)
    {
        return Path.SCORE_PAGE;
    }
    private String viewAddClientPage(HttpServletRequest request)
    {
        return Path.ADD_CLIENT_PAGE;
    }
    private String viewDeleteConfirm(HttpServletRequest request) throws DBException {
        int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        DAOFactory daoFactory=new MySqlDAOFactory();
        TariffDAO tariffDAO=daoFactory.getTariffDAO();
        Tariff tariff=tariffDAO.getById(tariffId);
       request.setAttribute(Constant.TARIFF,tariff);
        return Path.CONFIRM_TARIFF_DELETE_PAGE;
    }
    private String viewAddTariff(HttpServletRequest request)
    {
        int serviceId=Integer.parseInt(request.getParameter(Constant.SERVICE_ID));
        request.setAttribute(Constant.SERVICE_ID,serviceId);
        return Path.ADD_TARIFF_PAGE;
    }
    private String viewAllServices(HttpServletRequest request) throws DBException {
        DAOFactory factory=new MySqlDAOFactory();
        ServiceDAO serviceDAO=factory.getServiceDAO();
        List<Service> allServices=serviceDAO.getAll();
        request.setAttribute(Constant.SERVICES,allServices);
        User currentUser=(User)request.getSession().getAttribute(Constant.USER);
        String forward=null;
        if(currentUser!=null)
        {
            if(currentUser.getRole()==Role.ADMIN)
            {
                forward= Path.ADMIN_SERVICE_PAGE;
            }
            else
            {
                forward=Path.CLIENT_SERVICE_PAGE;
            }
        }

        return forward;

    }
    private String viewAllUsers(HttpServletRequest request) throws DBException {
        DAOFactory factory=new MySqlDAOFactory();
        UserDAO userDAO=factory.getUserDAO();
        List<User>allUsers=userDAO.getClients();
        request.setAttribute(Constant.USERS,allUsers);
       return Path.USERS_LIST_PAGE;

    }
    private String viewEdit(HttpServletRequest request) throws DBException {
        int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        DAOFactory factory=new MySqlDAOFactory();
        TariffDAO tariffDAO=factory.getTariffDAO();
        Tariff tariff=tariffDAO.getById(tariffId);
        request.setAttribute(Constant.TARIFF,tariff);
        return Path.EDIT_TARIFF_FORM;
    }
    private String viewServiceTariffs(HttpServletRequest request) throws DBException {
        int serviceId=Integer.parseInt(request.getParameter(Constant.SERVICE_ID));
        DAOFactory factory=new MySqlDAOFactory();
        TariffDAO tariffDAO=factory.getTariffDAO();
        List<Tariff>serviceTariffs=tariffDAO.getAllServiceTariffs(serviceId);
        request.setAttribute(Constant.SERVICE_ID,serviceId);
        request.setAttribute(Constant.TARIFFS,serviceTariffs);
        User currentUser=(User)request.getSession().getAttribute(Constant.USER);
        if(currentUser.getRole()==Role.ADMIN)
        {
            return Path.ADMIN_SERVICE_TARIFF;
        }
        else
        {
            return Path.CLIENT_SERVICE_TARIFF;
        }
    }
    private String viewSettings()
    {
        return Path.PAGE_SETTINGS;
    }
}
