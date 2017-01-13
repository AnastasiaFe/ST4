package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.util.mail.MailUtil;
import ua.nure.fedorenko.SummaryTask4.util.verification.StringGenerator;
import ua.nure.fedorenko.SummaryTask4.web.Constant;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class ResetPasswordCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String login=request.getParameter(Constant.LOGIN);
        DAOFactory daoFactory=new MySqlDAOFactory();
        UserDAO userDAO=daoFactory.getUserDAO();
        User user=userDAO.getById(login);
        String newHash= StringGenerator.prepareRandomString(30);
        user.setVerif_hash(newHash);
        userDAO.update(user);
        try {
            MailUtil.sendResetPasswordLink(login,user.getEmail(),user.getVerif_hash());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        request.setAttribute(Constant.MESSAGE,"Go to email please");
        return Path.ADD_TARIFF_RESULT;
    }
}
