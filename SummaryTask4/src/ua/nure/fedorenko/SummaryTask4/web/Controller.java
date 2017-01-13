package ua.nure.fedorenko.SummaryTask4.web;

import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.command.Command;
import ua.nure.fedorenko.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia on 04.01.2017.
 */
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        //  LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        System.out.println(commandName);
        //  LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        //   LOG.trace("Obtained command --> " + command);
        System.out.println(command);

        // execute command and get forward address
        String forward = Path.ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        System.out.println(forward);
        //    LOG.trace("Forward address --> " + forward);

        //     LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        if (forward != null) {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }
}
