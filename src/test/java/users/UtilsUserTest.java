/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package users;

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
public class UtilsUserTest {
    
    public UtilsUserTest() {
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
     * Test of getUser method, of class UtilsUser.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        int id = 0;
        UtilsUser instance = new UtilsUser();
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class UtilsUser.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        UtilsUser instance = new UtilsUser();
        List<User> expResult = null;
        List<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UtilsUser.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = null;
        UtilsUser instance = new UtilsUser();
        boolean expResult = false;
        boolean result = instance.addUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UtilsUser.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user = null;
        UtilsUser instance = new UtilsUser();
        boolean expResult = false;
        boolean result = instance.updateUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UtilsUser.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int id = 0;
        UtilsUser instance = new UtilsUser();
        boolean expResult = false;
        boolean result = instance.deleteUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
