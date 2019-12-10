/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Date;
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
public class RentDAOTest {
    
    public RentDAOTest() {
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
     * Test of insert method, of class RentDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object ob = null;
        RentDAO instance = new RentDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class RentDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object ob = null;
        RentDAO instance = new RentDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class RentDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        RentDAO instance = new RentDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class RentDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int id = 0;
        RentDAO instance = new RentDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNxtId method, of class RentDAO.
     */
    @Test
    public void testGetNxtId() {
        System.out.println("getNxtId");
        RentDAO instance = new RentDAO();
        int expResult = 0;
        int result = instance.getNxtId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canRent method, of class RentDAO.
     */
    @Test
    public void testCanRent() {
        System.out.println("canRent");
        int pID = 0;
        Date startDate = null;
        Date endDate = null;
        RentDAO instance = new RentDAO();
        boolean expResult = false;
        boolean result = instance.canRent(pID, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class RentDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        RentDAO instance = new RentDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
