package ua.nure.fedorenko.SummaryTask4.web.tag;

import ua.nure.fedorenko.SummaryTask4.db.entity.User;

/**
 * Created by Anastasia on 08.01.2017.
 */
public class UserNameFunction {
    public static String fullName(User user) {
        return user.getName() + " " + user.getSurname();
    }
}
