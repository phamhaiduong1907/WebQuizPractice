/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slider;
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
public class SliderDBContextTest {
    
    public SliderDBContextTest() {
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
     * Test of getSliders method, of class SliderDBContext.
     */
    @Test
    public void testGetSliders_0args() {
        SliderDBContext instance = new SliderDBContext();
        int expResult = 7;
        ArrayList<Integer> listIds = new ArrayList<>(Arrays.asList(1,2,9,10,11,22,24));
        ArrayList<Slider> result = instance.getSliders();
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(listIds.contains(slider.getSliderID()));
        }
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getSliderByID method, of class SliderDBContext.
     */
    @Test
    public void testGetSliderByID() {
        SliderDBContext instance = new SliderDBContext();
        
        // find with an unexisted id
        Slider result = instance.getSliderByID(25);
        assertNull(result);
        
        // find with an existed key
        result = instance.getSliderByID(1);
        assertNotNull(result);
        assertEquals("Case Study", result.getTitle());
        assertEquals("https://digitellinc.com/", result.getBacklink());
        assertEquals(true, result.isStatus());
        assertEquals("images/slider/slider_thumbnail_id1.png", result.getImageUrl());
        assertEquals("Note", result.getNote());
        
    }

    /**
     * Test of count method, of class SliderDBContext.
     */
    @Test
    public void testCount() {
        SliderDBContext instance = new SliderDBContext();
        int expResult = 22;
        int result = instance.count();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of countByStatus method, of class SliderDBContext.
     */
    @Test
    public void testCountByStatus() {
        SliderDBContext instance = new SliderDBContext();
        
        // number of active
        boolean status = true;
        int expResult = 7;
        int result = instance.countByStatus(status);
        assertEquals(expResult, result);
        
        //number of inactive
        status = false;
        expResult = 15;
        result = instance.countByStatus(status);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of countByTitle method, of class SliderDBContext.
     */
    @Test
    public void testCountByTitle() {
        SliderDBContext instance = new SliderDBContext();
        
        String title = "Education Banner";
        int expResult = 4;
        int result = instance.countByTitle(title);
        assertEquals(expResult, result);
        
        
        title = "E-learning";
        expResult = 2;
        result = instance.countByTitle(title);
        assertEquals(expResult, result);
        
        title = "Education";
        expResult = 0;
        result = instance.countByTitle(title);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllSliders method, of class SliderDBContext.
     */
    @Test
    public void testGetAllSliders() {
        SliderDBContext instance = new SliderDBContext();
        int expResult = 22;
        ArrayList<Slider> result = instance.getAllSliders();
        assertNotNull(result);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPaginatedSlidersByTitle method, of class SliderDBContext.
     */
    @Test
    public void testGetPaginatedSlidersByTitle() {
        SliderDBContext instance = new SliderDBContext();
        
        // Get page 1 of sliders with title "Education Banner"
        int pagesize = 3;
        int pageindex = 1;
        String title = "Education Banner";
        int expResult = 3;
        ArrayList<Slider> result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(slider.getTitle().equals(title));
        }
        
        // Get page 2 of sliders with title "Education Banner"
        pageindex = 2;
        title = "Education Banner";
        expResult = 1;
        result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(slider.getTitle().equals(title));
        }
        
        // Get page 3 of sliders with title "Education Banner"
        pageindex = 3;
        title = "Education Banner";
        expResult = 0;
        result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        
        

        // Get page 1 of sliders with title "Education Banner" when pagesize = 5
        pagesize = 5;
        pageindex = 1;
        title = "Education Banner";
        expResult = 4;
        result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(slider.getTitle().equals(title));
        }
        
        // Get page 2 of sliders with title "Education Banner" when pagesize = 5
        pagesize = 5;
        pageindex = 2;
        title = "Education Banner";
        expResult = 0;
        result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        
        // Get page 1 of sliders with title "Education" when pagesize is whatever
        pagesize = 3;
        pageindex = 1;
        title = "Education";
        expResult = 0;
        result = instance.getPaginatedSlidersByTitle(pageindex, pagesize, title);
        assertNotNull(result);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPaginatedSlidersByStatus method, of class SliderDBContext.
     */
    @Test
    public void testGetPaginatedSlidersByStatus() {
        SliderDBContext instance = new SliderDBContext();
        
        
        // Get page 1 of active sliders
        int pagesize = 3;
        int pageindex = 1;
        boolean status = true;
        int expResult = 3;
        ArrayList<Slider> result = instance.getPaginatedSlidersByStatus(pageindex, pagesize, status);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(slider.isStatus());
        }
        
        // Get page 3 of active sliders
        pageindex = 3;
        status = true;
        expResult = 1;
        result = instance.getPaginatedSlidersByStatus(pageindex, pagesize, status);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(slider.isStatus());
        }
        
        // Get page 1 of inactive sliders
        pageindex = 1;
        status = false;
        expResult = 3;
        result = instance.getPaginatedSlidersByStatus(pageindex, pagesize, status);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(!slider.isStatus());
        }
        
        //Get page 6 of inactive sliders
        pageindex = 6;
        status = false;
        expResult = 0;
        result = instance.getPaginatedSlidersByStatus(pageindex, pagesize, status);
        assertNotNull(result);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPaginatedSliders method, of class SliderDBContext.
     */
    @Test
    public void testGetPaginatedSliders() {
        ArrayList<Integer> listIds = new ArrayList<>(Arrays.asList(1,2,3));
        int pageindex = 1;
        int pagesize = 3;
        SliderDBContext instance = new SliderDBContext();
        int expResult = 3;
        ArrayList<Slider> result = instance.getPaginatedSliders(pageindex, pagesize);
        assertNotNull(result);
        assertEquals(expResult, result.size());
        for (Slider slider : result) {
            assertTrue(listIds.contains(slider.getSliderID()));
        }
        
        
        pageindex = 9;
        expResult = 0;
        result = instance.getPaginatedSliders(pageindex, pagesize);
        assertNotNull(result);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of insertSlider method, of class SliderDBContext.
     */
    @Test
    public void testInsertSlider() {
        SliderDBContext db = new SliderDBContext();
        Connection connection = db.connection;
        try {
            connection.setAutoCommit(false);
            String newTitle = "New slider";
            String newBacklink = "newbacklink";
            String newUrl = "newurl";
            String newNote = "newnote";
            db.insertSlider(newTitle, newBacklink, true, newUrl, newNote);
            
            int sliderID = new CommonDBContext().getIdentity("Slider");
            assertTrue(sliderID > 24);
            assertEquals(23, db.getAllSliders().size());
            
            Slider sliderByID = db.getSliderByID(sliderID);
            assertEquals(newTitle, sliderByID.getTitle());
            assertEquals(newBacklink, sliderByID.getBacklink());
            assertEquals(newUrl, sliderByID.getImageUrl());
            assertEquals(newNote, sliderByID.getNote());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SliderDBContextTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of updateSlider method, of class SliderDBContext.
     */
    @Test
    public void testUpdateSlider() {
        SliderDBContext db = new SliderDBContext();
        Connection connection = db.connection;
        try {
            connection.setAutoCommit(false);
            db.insertSlider("", "", true, "", "");
            String newTitle = "New Title";
            String newBacklink = "newbacklink";
            String newUrl = "newurl";
            String newNote = "newNote";
            int sliderID = new CommonDBContext().getIdentity("Slider");
            db.updateSlider(sliderID, newTitle, newBacklink, true, newUrl, newNote);
            
            Slider sliderByID = db.getSliderByID(sliderID);
            assertEquals(newTitle, sliderByID.getTitle());
            assertEquals(newBacklink, sliderByID.getBacklink());
            assertEquals(newUrl, sliderByID.getImageUrl());
            assertEquals(newNote, sliderByID.getNote());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SliderDBContextTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of changeStatus method, of class SliderDBContext.
     */
    @Test
    public void testChangeStatus() {
        SliderDBContext db = new SliderDBContext();
        Connection connection = db.connection;
        try {
            connection.setAutoCommit(false);
            String newTitle = "New Title";
            String newBacklink = "newbacklink";
            String newUrl = "newurl";
            String newNote = "newNote";
            db.insertSlider(newTitle, newBacklink, true, newUrl, newNote);
            int sliderID = new CommonDBContext().getIdentity("Slider");
            db.changeStatus(false, sliderID);
            
            Slider sliderByID = db.getSliderByID(sliderID);
            assertTrue(!sliderByID.isStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SliderDBContextTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of getSliders method, of class SliderDBContext.
     */
    @Test
    public void testGetSliders_4args() {
        SliderDBContext db = new SliderDBContext();
        
        // page 1 - size 3 - get all
        String combination = null;
        Boolean status = null;
        int pageindex = 1;
        int pagesize = 3;
        int expResult = 3;
        ArrayList<Slider> sliders = db.getSliders(combination, status, pageindex, pagesize);
        assertEquals(expResult, sliders.size());
        
        // page 1 - size 3 - get "Education Banner"
        combination = "Education Banner";
        status = null;
        expResult = 3;
        sliders = db.getSliders(combination, status, pageindex, pagesize);
        assertEquals(expResult, sliders.size());
        for (Slider slider : sliders) {
            assertTrue(slider.getTitle().equalsIgnoreCase(combination));
        }
        
        // page 1 - size 3 - get active sliders
        combination = null;
        status = true;
        expResult = 3;
        sliders = db.getSliders(combination, status, pageindex, pagesize);
        assertEquals(expResult, sliders.size());
        for (Slider slider : sliders) {
            assertTrue(slider.isStatus());
        }
        
        // page 1 - size 3 - get "Banner" + inactive
        combination = "Banner";
        status = false;
        expResult = 3;
        sliders = db.getSliders(combination, status, pageindex, pagesize);
        assertEquals(expResult, sliders.size());
        for (Slider slider : sliders) {
            assertTrue(slider.getTitle().toLowerCase().contains(combination.toLowerCase()));
            assertTrue(!slider.isStatus());
        }
        
        
    }

    /**
     * Test of countSlider method, of class SliderDBContext.
     */
    @Test
    public void testCountSlider() {
        SliderDBContext db = new SliderDBContext();
        
        String combination = null;
        Boolean status = null;
        int expResult = 22;
        int countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = "";
        status = null;
        expResult = 22;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = "Education Banner";
        status = null;
        expResult = 4;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = "";
        status = true;
        expResult = 7;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = null;
        status = false;
        expResult = 15;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = "Education Banner";
        status = false;
        expResult = 3;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
        
        combination = "Some Slider ???";
        status = null;  
        expResult = 0;
        countSlider = db.countSlider(combination, status);
        assertEquals(expResult, countSlider);
    }

    /**
     * Test of getSliderStatusById method, of class SliderDBContext.
     */
    @Test
    public void testGetSliderStatusById() {
        SliderDBContext db = new SliderDBContext();
        int sliderID = 100; 
        boolean status = db.getSliderStatusById(sliderID);
        assertTrue(!status);
        
        sliderID = 1; 
        status = db.getSliderStatusById(sliderID);
        assertTrue(status);
        
        sliderID = 6;
        status = db.getSliderStatusById(sliderID);
        assertTrue(!status);
    }
    
}
