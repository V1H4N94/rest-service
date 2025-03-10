/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package admin;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class DBUtilsAdminTest {
    
    public DBUtilsAdminTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAdmin method, of class DBUtilsAdmin.
     */
    @Test
    public void testGetAdmin() throws Exception {
        System.out.println("getAdmin");
        int id = 1;
        DBUtilsAdmin instance = new DBUtilsAdmin();
        //Admin expResult = null;
        Admin result = instance.getAdmin(id);
        assertEquals(id, result.getId());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAdmins method, of class DBUtilsAdmin.
     */
//    @Test
//    public void testGetAdmins() {
//        System.out.println("getAdmins");
//        DBUtilsAdmin instance = new DBUtilsAdmin();
//        List<Admin> expResult = null;
//        List<Admin> result = instance.getAdmins();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of addAdmin method, of class DBUtilsAdmin.
     */
//    @Test
//    public void testAddAdmin() {
//        System.out.println("addAdmin");
//        Admin adm = null;
//        DBUtilsAdmin instance = new DBUtilsAdmin();
//        boolean expResult = false;
//        boolean result = instance.addAdmin(adm);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateAdmin method, of class DBUtilsAdmin.
//     */
//    @Test
//    public void testUpdateAdmin() {
//        System.out.println("updateAdmin");
//        Admin adm = null;
//        DBUtilsAdmin instance = new DBUtilsAdmin();
//        boolean expResult = false;
//        boolean result = instance.updateAdmin(adm);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteAdmin method, of class DBUtilsAdmin.
//     */
//    @Test
//    public void testDeleteAdmin() {
//        System.out.println("deleteAdmin");
//        int id = 0;
//        DBUtilsAdmin instance = new DBUtilsAdmin();
//        boolean expResult = false;
//        boolean result = instance.deleteAdmin(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//    
}
