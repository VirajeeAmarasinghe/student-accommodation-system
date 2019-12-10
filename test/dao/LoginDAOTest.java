/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class LoginDAOTest {
    
    public LoginDAOTest() {
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
     * Test of insert method, of class LoginDAO.
     */
    @Test
    public void testInsert_Object() {
        System.out.println("insert");
        Object ob = null;
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class LoginDAO.
     */
    @Test
    public void testInsert_Object_String() {
        System.out.println("insert");
        Object ob = null;
        String tableName = "";
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.insert(ob, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class LoginDAO.
     */
    @Test
    public void testUpdate_Object() {
        System.out.println("update");
        Object ob = null;
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class LoginDAO.
     */
    @Test
    public void testUpdate_Object_String() {
        System.out.println("update");
        Object ob = null;
        String tableName = "";
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.update(ob, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class LoginDAO.
     */
    @Test
    public void testDelete_int() {
        System.out.println("delete");
        int id = 0;
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class LoginDAO.
     */
    @Test
    public void testDelete_String_String() {
        System.out.println("delete");
        String email = "";
        String tableName = "";
        LoginDAO instance = new LoginDAO();
        int expResult = 0;
        int result = instance.delete(email, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class LoginDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        LoginDAO instance = new LoginDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class LoginDAO.
     */
    @Test
    public void testGet_String_String() {
        System.out.println("get");
        String email = "";
        String tableName = "";
        LoginDAO instance = new LoginDAO();
        Object expResult = null;
        Object result = instance.get(email, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class LoginDAO.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int id = 0;
        LoginDAO instance = new LoginDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alreadyHasEmail method, of class LoginDAO.
     */
    @Test
    public void testAlreadyHasEmail() {
        System.out.println("alreadyHasEmail");
        String email = "";
        String tableName = "";
        LoginDAO instance = new LoginDAO();
        boolean expResult = false;
        boolean result = instance.alreadyHasEmail(email, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
