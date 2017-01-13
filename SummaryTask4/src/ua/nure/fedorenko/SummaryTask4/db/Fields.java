package ua.nure.fedorenko.SummaryTask4.db;

/**
 * Created by Anastasia on 02.01.2017.
 */
public class Fields {

    /**
     * user fields
     */

    public static final String LOGIN="login";
    public static final String PASSWORD="password";
    public static final String NAME="name";
    public static final String SURNAME="surname";
    public static final String EMAIL="email";
    public static final String ROLE_ID="roleId";
    public static final String SCORE="score";
    public static final String VERIF_ATTEMPTS="verif_attempts";
    public static final String VERIF_HASH="verif_hash";

    /**
     * service fields
     */

    public static final String SERVICE_ID="serviceId";
    public static final String SERVICE_NAME="name";

    /**
     * tariff fields
     */

    public static final String TARIFF_ID="tariffId";
    public static final String TARIFF_NAME="name";
    public static final String TARIFF_DESC="description";
    public static final String TARIFF_PRICE="price";
    public static final String SERVICE="service";


    /**
     * statuses fields
     */
    public static final String STATUS_ID="statusId";
    public static final String STATUS_NAME="name";



    /**
     * roles fields
     */
    public static final String ROLE_NAME="name";

/**
 * payment fields
 */

   public static final String PAYMENT_ID="paymentId";
    public static final String TIME="time";
    public static final String PAYMENT_TARIFF_ID="payment_tariffId";


}
