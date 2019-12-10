/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import java.sql.Date;

/**
 *
 * @author Virajee
 */
public class Payment {
    private int payID;
    private int pID;
    private Date payDate;
    private double amount;

    public Payment() {
    }

    public Payment(int payID, int pID, Date payDate, double amount) {
        this.payID = payID;
        this.pID = pID;
        this.payDate = payDate;
        this.amount = amount;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayID() {
        return payID;
    }

    public int getpID() {
        return pID;
    }

    public Date getPayDate() {
        return payDate;
    }

    public double getAmount() {
        return amount;
    }
    
    
}
