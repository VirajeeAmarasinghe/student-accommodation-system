/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.View;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Virajee
 */
public class ViewDAOTest {
    
    public ViewDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class ViewDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object ob = null;
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ViewDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object ob = null;
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateConfirm method, of class ViewDAO.
     */
    @Test
    public void testUpdateConfirm() {
        System.out.println("updateConfirm");
        int ID = 0;
        String userType = "";
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.updateConfirm(ID, userType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateConfirmDateTime method, of class ViewDAO.
     */
    @Test
    public void testUpdateConfirmDateTime() {
        System.out.println("updateConfirmDateTime");
        int ID = 0;
        String userType = "";
        Date date = null;
        Time time = null;
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.updateConfirmDateTime(ID, userType, date, time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ViewDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class ViewDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int id = 0;
        ViewDAO instance = new ViewDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByLandlordAndConfirmation method, of class ViewDAO.
     */
    @Test
    public void testGetByLandlordAndConfirmation() {
        System.out.println("getByLandlordAndConfirmation");
        int lID = 0;
        ViewDAO instance = new ViewDAO();
        ArrayList<View> expResult = null;
        ArrayList<View> result = instance.getByLandlordAndConfirmation(lID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByStudentAndConfirmation method, of class ViewDAO.
     */
    @Test
    public void testGetByStudentAndConfirmation() {
        System.out.println("getByStudentAndConfirmation");
        int sLocalID = 0;
        int sInternationalID = 0;
        ViewDAO instance = new ViewDAO();
        ArrayList<View> expResult = null;
        ArrayList<View> result = instance.getByStudentAndConfirmation(sLocalID, sInternationalID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNxtId method, of class ViewDAO.
     */
    @Test
    public void testGetNxtId() {
        System.out.println("getNxtId");
        ViewDAO instance = new ViewDAO();
        int expResult = 0;
        int result = instance.getNxtId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ViewDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ViewDAO instance = new ViewDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
