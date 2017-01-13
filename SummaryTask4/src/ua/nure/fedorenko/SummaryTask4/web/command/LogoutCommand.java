package ua.nure.fedorenko.SummaryTask4.web.command;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Anastasia on 05.01.2017.
 */
public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

       // LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }
}
