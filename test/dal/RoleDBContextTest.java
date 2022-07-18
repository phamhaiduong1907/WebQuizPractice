/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Role;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hai Duong
 */
public class RoleDBContextTest {
    
    public RoleDBContextTest() {
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
     * Test of getRoles method, of class RoleDBContext.
     */
    @Test
    public void testGetRoles() {
        RoleDBContext instance = new RoleDBContext();
        int expResult = 5;
        ArrayList<Role> result = instance.getRoles();
        assertEquals(expResult, result.size());
        for (Role role : result) {
            switch (role.getRoleID()) {
                case 1: assertEquals("Admin", role.getRoleName()); break;
                case 2: assertEquals("Expert", role.getRoleName()); break;
                case 3: assertEquals("Sale", role.getRoleName()); break;
                case 4: assertEquals("Marketing", role.getRoleName()); break;
                case 5: assertEquals("Customer", role.getRoleName()); break;
            }
        }
    }

    /**
     * Test of getRole method, of class RoleDBContext.
     */
    @Test
    public void testGetRole() {
        int roleid = 1;
        RoleDBContext instance = new RoleDBContext();
        Role result = instance.getRole(roleid);
        assertNotNull(result);
        assertEquals("admin", result.getRoleName().toLowerCase());
    }

    /**
     * Test of getRoleById method, of class RoleDBContext.
     */
    @Test
    public void testGetRoleById() {
        int roleid = 1;
        RoleDBContext instance = new RoleDBContext();
        Role result = instance.getRoleById(roleid);
        assertNotNull(result);
        assertEquals("admin", result.getRoleName().toLowerCase());
        assertEquals(6, result.getFeatures().size());
    }
    
}
