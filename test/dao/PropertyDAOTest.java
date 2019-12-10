/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.Property;
import java.util.ArrayList;
import java.util.Set;
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
public class PropertyDAOTest {
    
    public PropertyDAOTest() {
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
     * Test of insert method, of class PropertyDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        /*create Property object
         *Property record under the property id=12 isn't available
         *in the database
         */
        Object ob = new Property(12,"13","Cambridge Rd","United Kingdom",
                5,5,1,"Yes","Yes","Yes",100,"Good","",1,1,"No");
        //create PropertyDAO object
        PropertyDAO instance = new PropertyDAO();
        //expected result
        int expResult = 1;
        //actual result
        int result = instance.insert(ob);
        /*
         * If the data is inserted into the database result=1
         * Then the test will be passed.
         * That means insert method is working properly. 
        */
        assertEquals(expResult, result);        
    }

    /**
     * Test of updateAvailability method, of class PropertyDAO.
     */
    @Test
    public void testUpdateAvailability() {
        System.out.println("updateAvailability");
        int pID = 1;
        String availability = "No";
        PropertyDAO instance = new PropertyDAO();
        int expResult = 1;
        int result = instance.updateAvailability(pID, availability);
        assertEquals(expResult, result);        
    }

    /**
     * Test of update method, of class PropertyDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        /*create Property object
         *Property record under the property id=12 is available
         *in the database
         */        
        Object ob = new Property(12,"13","Church Rd","United Kingdom",
                6,6,1,"Yes","Yes","No",100,"Good","",1,1,"No");
        
        //create PropertyDAO object 
        PropertyDAO instance = new PropertyDAO();
        
        //expected result
        int expResult = 1;
        
        /*
         * If the data is updated result=1
         * Then the test will be passed.
         * That means update method is working properly. 
        */
        int result = instance.update(ob);
        assertEquals(expResult, result);        
    }

    

    /**
     * Test of updateImage method, of class PropertyDAO.
     */
    @Test
    public void testUpdateImage() {
        System.out.println("updateImage");
        String saveFile = "";
        int pID = 1;
        PropertyDAO instance = new PropertyDAO();
        int expResult = 1;
        int result = instance.updateImage(saveFile, pID);
        assertEquals(expResult, result);        
    }

    /**
     * Test of delete method, of class PropertyDAO.
     */
    @Test
    public void testDelete_int() {
        System.out.println("delete");
        //property id
        int id = 12;
        //create a PropertyDAO object
        PropertyDAO instance = new PropertyDAO();
        //expected result 
        int expResult = 1;
        //actual result
        int result = instance.delete(id);
        /*
         * If the data is deleted result=1
         * Then the test will be passed.
         * That means delete method is working properly. 
        */
        assertEquals(expResult, result);        
    }
    

    /**
     * Test of delete method, of class PropertyDAO.
     */
    @Test
    public void testDelete_int_int() {
        System.out.println("delete");
        int id = 1;
        int lID = 1;
        PropertyDAO instance = new PropertyDAO();
        int expResult = 1;
        int result = instance.delete(id, lID);
        assertEquals(expResult, result);        
    }

    /**
     * Test of get method, of class PropertyDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        //property id=1 is availble in the database
        int id = 1;
        PropertyDAO instance = new PropertyDAO();
        /*an object is returned because a record for property id=1
         *is available in the database. So result won't be null
        */
        Object result = instance.get(id);
        /*test will be passed because result isn't null
         * That means get method is working properly 
        */        
        assertNotNull(result);        
    }

    /**
     * Test of getByPIDAndLID method, of class PropertyDAO.
     */
    @Test
    public void testGetByPIDAndLID() {
        System.out.println("getByPIDAndLID");
        int PId = 1;
        int LId = 1;
        PropertyDAO instance = new PropertyDAO();        
        Property result = instance.getByPIDAndLID(PId, LId);
        assertNotNull(result);        
    }

    /**
     * Test of getByLandordID method, of class PropertyDAO.
     */
    @Test
    public void testGetByLandordID() {
        System.out.println("getByLandordID");
        int lId = 1;
        PropertyDAO instance = new PropertyDAO();      
        ArrayList<Property> result = instance.getByLandordID(lId);
        assertNotNull(result);        
    }

    /**
     * Test of getStreets method, of class PropertyDAO.
     */
    @Test
    public void testGetStreets() {
        System.out.println("getStreets");
        PropertyDAO instance = new PropertyDAO();       
        Set<String> result = instance.getStreets();
        assertNotNull(result);        
    }

    /**
     * Test of getPropertiesByStreetAndType method, of class PropertyDAO.
     */
    @Test
    public void testGetPropertiesByStreetAndType() {
        System.out.println("getPropertiesByStreetAndType");
        String street = "De Villers St";
        int typeID = 1;
        PropertyDAO instance = new PropertyDAO();       
        ArrayList<Property> result = instance.getPropertiesByStreetAndType(street, typeID);
        assertNotNull(result);        
    }

    /**
     * Test of getPropertiesByStreetAndTypeAndMinRent method, of class PropertyDAO.
     */
    @Test
    public void testGetPropertiesByStreetAndTypeAndMinRent() {
        System.out.println("getPropertiesByStreetAndTypeAndMinRent");
        String street = "De Villers St";
        int typeID = 1;
        double rent =50;
        PropertyDAO instance = new PropertyDAO();       
        ArrayList<Property> result = instance.getPropertiesByStreetAndTypeAndMinRent(street, typeID, rent);
        assertNotNull(result);        
    }

    /**
     * Test of getPropertiesByStreetAndTypeAndMinMaxRent method, of class PropertyDAO.
     */
    @Test
    public void testGetPropertiesByStreetAndTypeAndMinMaxRent() {
        System.out.println("getPropertiesByStreetAndTypeAndMinMaxRent");
        String street = "De Villers St";
        int typeID = 1;
        double minRent =50;
        double maxRent = 200;
        PropertyDAO instance = new PropertyDAO();        
        ArrayList<Property> result = instance.getPropertiesByStreetAndTypeAndMinMaxRent(street, typeID, minRent, maxRent);
        assertNotNull(result);        
    }

    /**
     * Test of getNxtId method, of class PropertyDAO.
     */
    @Test
    public void testGetNxtId() {
        System.out.println("getNxtId");
        PropertyDAO instance = new PropertyDAO();
        int expResult = 14;
        int result = instance.getNxtId();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getAll method, of class PropertyDAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        PropertyDAO instance = new PropertyDAO(); 
        //expected result
        boolean expResult = false;
        /*there is data records in the property table
         *So returned ArrayList of getAll method won't be
         *empty. ArrayList-result won't be empty 
         */        
        ArrayList<Object> result = instance.getAll();
        /*test will be passed, becuase result isn't empty
         *and result.isEmpty() is returning false
         *So expected result=result(actual result)
         */
        assertEquals(expResult,result.isEmpty());        
    }

    /**
     * Test of getAllProps method, of class PropertyDAO.
     */
    @Test
    public void testGetAllProps() {
        System.out.println("getAllProps");
        PropertyDAO instance = new PropertyDAO();       
        ArrayList<Property> result = instance.getAllProps();
        assertNotNull(result);        
    }
    
}
