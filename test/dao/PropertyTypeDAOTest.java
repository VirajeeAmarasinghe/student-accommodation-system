/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.PropertyType;
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
public class PropertyTypeDAOTest {
    
    public PropertyTypeDAOTest() {
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
     * Test of insert method, of class PropertyTypeDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object ob = null;
        PropertyTypeDAO instance = new PropertyTypeDAO();
        int expResult = 0;
        int result = instance.insert(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PropertyTypeDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object ob = null;
        PropertyTypeDAO instance = new PropertyTypeDAO();
        int expResult = 0;
        int result = instance.update(ob);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PropertyTypeDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        PropertyTypeDAO instance = new PropertyTypeDAO();
        int expResult = 0;
        int result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class PropertyTypeDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int id = 0;
        PropertyTypeDAO instance = new PropertyTypeDAO();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class PropertyTypeDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        PropertyTypeDAO instance = new PropertyTypeDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllObj method, of class PropertyTypeDAO.
     */
    @Test
    public void testGetAllObj() {
        System.out.println("getAllObj");
        PropertyTypeDAO instance = new PropertyTypeDAO();
        ArrayList<PropertyType> expResult = null;
        ArrayList<PropertyType> result = instance.getAllObj();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
