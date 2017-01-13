package ua.nure.fedorenko.SummaryTask4.exception;

/**
 * Holder for messages of exceptions.
 *
 * @author Dmytro_Kolesnikov
 */
public class Messages {

    private Messages() {
        // no op
    }


    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain user by login";
    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert user";
    public static final String ERR_CANNOT_INSERT_PAYMENT_TARIFF="Cannot insert payment_tariff";
    public static final String ERR_CANNOT_BLOCK_USER = "Cannot block user";
    public static final String ERR_CANNOT_UNBLOCK_USER = "Cannot unblock user";
    public static final String EER_CANNOT_OBTAIN_ALL_USERS = "Cannot obtain all users";
    public static final String ERR_CANNOT_OBTAIN_ALL_TARIFFS="Cannot obtain all tariffs";
    public static final String ERR_CANNOT_INSERT_TARIFF="Cannot insert tariff";
    public static final String ERR_CANNOT_UPDATE_USER="Cannot update user";
    public static final String ERR_CANNOT_DO_TRANSACTION="Cannot do transaction";
    public static final String ERR_CANNOT_UPDATE_TARIFF="Cannot update tariff";
    public static final String ERR_CANNOT_DELETE_TARIFF="Cannot delete tariff";
    public static final String ERR_CANNOT_OBTAIN_TARIFF_BY_ID="Cannot obtain tariff by id";
    public static final String ERR_CANNOT_OBTAIN_TARIFFS_BY_SERVICE="Cannot obtain tariffs by service";
    public static final String ERR_CANNOT_OBTAIN_SERVICE_BY_ID="Cannot obtain service by id";
    public static final String ERR_CANNOT_OBTAIN_ALL_SERVICES="Cannot obtain all services";
    public static final String ERR_CANNOT_OBTAIN_PAYMENT_BY_ID="Cannot obtain payment by id";
    public static final String ERR_CANNOT_INSERT_PAYMENT="Cannot insert payment";
    public static final String ERR_INVALID_DATA="Invalid input data!";
    public static final String ERR_CANNOT_OBTAIN_PAYMENT_KEY="Cannot obtain payment id";




}