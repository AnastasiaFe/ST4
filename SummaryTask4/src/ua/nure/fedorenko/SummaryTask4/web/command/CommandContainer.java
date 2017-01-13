package ua.nure.fedorenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Anastasia on 04.01.2017.
 */
public class CommandContainer {

   // private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("updateSettings",new UpdateSettingsCommand());
        commands.put("logout",new LogoutCommand());
        commands.put("viewPage",new ViewPageCommand());
        commands.put("editTariff",new EditTariffCommand());
        commands.put("deleteTariff",new DeleteTariffCommand());
        commands.put("addTariff",new AddTarrifCommand());
        commands.put("blockUser",new BlockCommand());
        commands.put("unblockUser",new UnblockCommand());
        commands.put("sort",new SortCommand());
        commands.put("addClient",new AddClientCommand());
        commands.put("download",new DownloadCommand());
        commands.put("fillScore",new FillScoreCommand());
        commands.put("selectTariff",new SelectTariffCommand());
        commands.put("pay",new PayCommand());
        commands.put("verifyHash",new VerifyHashCommand());
        commands.put("resetPassword",new ResetPasswordCommand());
        commands.put("changePassword",new ChangePasswordCommand());


     //   LOG.debug("Command container was successfully initialized");
       // LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
          //  LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
