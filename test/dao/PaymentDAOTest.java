/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.Payment;
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
public class PaymentDAOTest {
    
    public PaymentDAOTest() {
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
     * Test of insert method, of class PaymentDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object ob = null;
        PaymentDAO instance = new PaymentDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PaymentDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object ob = null;
        PaymentDAO instance = new PaymentDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PaymentDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        PaymentDAO instance = new PaymentDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class PaymentDAO.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int id = 0;
        PaymentDAO instance = new PaymentDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByProperty method, of class PaymentDAO.
     */
    @Test
    public void testGetByProperty() {
        System.out.println("getByProperty");
        int pId = 0;
        PaymentDAO instance = new PaymentDAO();
        Object expResult = null;
        Object result = instance.getByProperty(pId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPayments method, of class PaymentDAO.
     */
    @Test
    public void testGetPayments() {
        System.out.println("getPayments");
        int lID = 0;
        PaymentDAO instance = new PaymentDAO();
        ArrayList<Payment> expResult = null;
        ArrayList<Payment> result = instance.getPayments(lID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class PaymentDAO.
     */
    @Test
    public void testGet_int_int() {
        System.out.println("get");
        int propID = 0;
        int lID = 0;
        PaymentDAO instance = new PaymentDAO();
        Payment expResult = null;
        Payment result = instance.get(propID, lID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class PaymentDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        PaymentDAO instance = new PaymentDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNxtId method, of class PaymentDAO.
     */
    @Test
    public void testGetNxtId() {
        System.out.println("getNxtId");
        PaymentDAO instance = new PaymentDAO();
        int expResult = 0;
        int result = instance.getNxtId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
