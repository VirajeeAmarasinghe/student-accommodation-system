/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
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
public class DAOConnectTest {
    
    public DAOConnectTest() {
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
     * Test of getInsatnce method, of class DAOConnect.
     */
    @Test
    public void testGetInsatnce() {
        System.out.println("getInsatnce");
        //expected result is a DAOConnect object
        DAOConnect expResult = DAOConnect.getInsatnce(); 
        //acutal result 
        DAOConnect result = DAOConnect.getInsatnce();  
        //compare the expected result and actual result
        assertEquals(expResult, result);         
    }

    /**
     * Test of connect method, of class DAOConnect.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        //get DAOConnect object using getInstance() method
        DAOConnect instance =DAOConnect.getInsatnce(); 
        //get Connection object
        Connection result = instance.connect();   
        assertNotNull(result);        
    }
    
}
