/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.Landlord;
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
public class LandlordDAOTest {
    
    public LandlordDAOTest() {
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
     * Test of insert method, of class LandlordDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object ob = null;
        LandlordDAO instance = new LandlordDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class LandlordDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object ob = null;
        LandlordDAO instance = new LandlordDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class LandlordDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        LandlordDAO instance = new LandlordDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class LandlordDAO.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int id = 0;
        LandlordDAO instance = new LandlordDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class LandlordDAO.
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String email = "";
        LandlordDAO instance = new LandlordDAO();
        Object expResult = null;
        Object result = instance.get(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class LandlordDAO.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String email = "";
        LandlordDAO instance = new LandlordDAO();
        String expResult = "";
        String result = instance.getName(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class LandlordDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        LandlordDAO instance = new LandlordDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll2 method, of class LandlordDAO.
     */
    @Test
    public void testGetAll2() {
        System.out.println("getAll2");
        LandlordDAO instance = new LandlordDAO();
        ArrayList<Landlord> expResult = null;
        ArrayList<Landlord> result = instance.getAll2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNxtId method, of class LandlordDAO.
     */
    @Test
    public void testGetNxtId() {
        System.out.println("getNxtId");
        LandlordDAO instance = new LandlordDAO();
        int expResult = 0;
        int result = instance.getNxtId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
