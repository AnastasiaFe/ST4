package ua.nure.fedorenko.SummaryTask4.db.entity;

import java.util.Date;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class Payment {


    private int paymentId;
    private User user;

    public Payment(int paymentId, User user) {
        this.paymentId = paymentId;
        this.user = user;

    }
   public Payment(User user)
   {
       this.user=user;
   }
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
