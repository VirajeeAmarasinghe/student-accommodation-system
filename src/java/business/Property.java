/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.InputStream;

/**
 *
 * @author Virajee
 */
public class Property {

    private int propID;
    private String addNo;
    private String addStreet;
    private String addCity;
    private int numOfTenants;
    private int noOfBedrooms;
    private int noOfBathrooms;
    private String kitchen;
    private String electricity;
    private String water;
    private double rent;
    private String addInfo;
    private String image;
    private int lID;
    private int tID;
    private String availability;
    private InputStream imageStream;

    public Property(int propID, String addNo, String addStreet, String addCity, int numOfTenants, int noOfBedrooms, int noOfBathrooms, String kitchen, String electricity, String water, double rent, String addInfo,int lID, int tID) {
      this.propID = propID;
        this.addNo = addNo;
        this.addStreet = addStreet;
        this.addCity = addCity;
        this.numOfTenants = numOfTenants;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.kitchen = kitchen;
        this.electricity = electricity;
        this.water = water;
        this.rent = rent;
        this.addInfo = addInfo;       
        this.lID = lID;
        this.tID = tID;        
    }
    
    public Property(){}

    public Property(int propID, String addNo, String addStreet, String addCity, int numOfTenants, int noOfBedrooms, int noOfBathrooms, String kitchen, String electricity, String water, double rent, String addInfo, String image, int lID, int tID, String availability) {
        this.propID = propID;
        this.addNo = addNo;
        this.addStreet = addStreet;
        this.addCity = addCity;
        this.numOfTenants = numOfTenants;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.kitchen = kitchen;
        this.electricity = electricity;
        this.water = water;
        this.rent = rent;
        this.addInfo = addInfo;
        this.image = image;
        this.lID = lID;
        this.tID = tID;
        this.availability = availability;
    }

    public Property(int propID, String addNo, String addStreet, String addCity, int numOfTenants, int noOfBedrooms, int noOfBathrooms, String kitchen, String electricity, String water, double rent, String addInfo, int lID, int tID, String availability, InputStream imageStream) {
        this.propID = propID;
        this.addNo = addNo;
        this.addStreet = addStreet;
        this.addCity = addCity;
        this.numOfTenants = numOfTenants;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.kitchen = kitchen;
        this.electricity = electricity;
        this.water = water;
        this.rent = rent;
        this.addInfo = addInfo;        
        this.lID = lID;
        this.tID = tID;
        this.availability = availability;
        this.imageStream = imageStream;
    }    
    

    public void setPropID(int propID) {
        this.propID = propID;
    }

    public void setAddNo(String addNo) {
        this.addNo = addNo;
    }

    public void setAddStreet(String addStreet) {
        this.addStreet = addStreet;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public void setNumOfTenants(int numOfTenants) {
        this.numOfTenants = numOfTenants;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setlID(int lID) {
        this.lID = lID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public InputStream getImageStream() {
        return imageStream;
    }
    
    
    public int getPropID() {
        return propID;
    }

    public String getAddNo() {
        return addNo;
    }

    public String getAddStreet() {
        return addStreet;
    }

    public String getAddCity() {
        return addCity;
    }

    public int getNumOfTenants() {
        return numOfTenants;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public String getKitchen() {
        return kitchen;
    }

    public String getElectricity() {
        return electricity;
    }

    public String getWater() {
        return water;
    }

    public double getRent() {
        return rent;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public String getImage() {
        return image;
    }

    public int getlID() {
        return lID;
    }

    public int gettID() {
        return tID;
    }

    public String getAvailability() {
        return availability;
    }

}
