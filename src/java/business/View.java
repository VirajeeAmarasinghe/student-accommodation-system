/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Virajee
 */
public class View {
     private int viewID;
     private Date viewDate;
     private Time viewTime;
     private String confirmStu;
     private String confirmLandlord;
     private int pID;
     private int stuLocalID;
     private int stuInternationalID;

    public View() {
    }

    public View(int viewID, Date viewDate, Time viewTime, String confirmStu, String confirmLandlord, int pID, int stuLocalID, int stuInternationalID) {
        this.viewID = viewID;
        this.viewDate = viewDate;
        this.viewTime = viewTime;
        this.confirmStu = confirmStu;
        this.confirmLandlord = confirmLandlord;
        this.pID = pID;
        this.stuLocalID = stuLocalID;
        this.stuInternationalID = stuInternationalID;
    }

    public void setViewID(int viewID) {
        this.viewID = viewID;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public void setViewTime(Time viewTime) {
        this.viewTime = viewTime;
    }

    public void setConfirmStu(String confirmStu) {
        this.confirmStu = confirmStu;
    }

    public void setConfirmLandlord(String confirmLandlord) {
        this.confirmLandlord = confirmLandlord;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public void setStuLocalID(int stuLocalID) {
        this.stuLocalID = stuLocalID;
    }

    public void setStuInternationalID(int stuInternationalID) {
        this.stuInternationalID = stuInternationalID;
    }

    public int getViewID() {
        return viewID;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public Time getViewTime() {
        return viewTime;
    }

    public String getConfirmStu() {
        return confirmStu;
    }

    public String getConfirmLandlord() {
        return confirmLandlord;
    }

    public int getpID() {
        return pID;
    }

    public int getStuLocalID() {
        return stuLocalID;
    }

    public int getStuInternationalID() {
        return stuInternationalID;
    }     
     
}
