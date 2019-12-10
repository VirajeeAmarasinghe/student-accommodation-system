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
public class Rent {
    private int rID;
    private int pID; 
    private int sIDLocal;
    private int sIDInternational;
    private Date startDate;
    private Date endDate;

    public Rent() {
    }

    public Rent(int rID, int pID, int sIDLocal, int sIDInternational, Date startDate, Date endDate) {
        this.rID = rID;
        this.pID = pID;
        this.sIDLocal = sIDLocal;
        this.sIDInternational = sIDInternational;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    

    public int getrID() {
        return rID;
    }

    public int getpID() {
        return pID;
    }    

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getsIDInternational() {
        return sIDInternational;
    }

    public int getsIDLocal() {
        return sIDLocal;
    }
    
    
    public void setrID(int rID) {
        this.rID = rID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }    

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    } 

    public void setsIDInternational(int sIDInternational) {
        this.sIDInternational = sIDInternational;
    }

    public void setsIDLocal(int sIDLocal) {
        this.sIDLocal = sIDLocal;
    }    
        
}
