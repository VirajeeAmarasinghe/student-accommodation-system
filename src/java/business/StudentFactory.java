/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Virajee
 */
public class StudentFactory {

    public Student getStudent(int stuId, String stuFName, String stuLName, String stuAdd, String stuTele, String email, String nic, String passport, String country, String student_type) {
        switch (student_type) {
            case "Local":
                return new Local(stuId, stuFName, stuLName, stuAdd, stuTele, email, nic);
            case "International":
                return new International(stuId, stuFName, stuLName, stuAdd, stuTele, email, passport, country);
            default:
                return null;
        }
    }
}
