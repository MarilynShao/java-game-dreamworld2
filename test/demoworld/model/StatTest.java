package demoworld.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatTest {

    private Stat stat;

    @Before
    public void setUp() {
        stat = new Stat("Force", "Strength stat", 0, 100, 50);
    }

    @Test
    public void testConstructor() {
        assertEquals("Force", stat.getName());
        assertEquals("Strength stat", stat.getDescription());
        assertEquals(50, stat.getBase().current());
        assertEquals(0, stat.getModifier().current());
    }

    @Test
    public void testSetCurrentBase() {
        stat.setCurrentBase(80);
        assertEquals(80, stat.getBase().current());
        assertEquals(80, stat.getTotal());

        stat.setCurrentBase(150);
        assertEquals(100, stat.getBase().current());
    }

    @Test
    public void testSetCurrentModifier() {
        stat.setCurrentModifier(20);
        assertEquals(20, stat.getModifier().current());
        assertEquals(70, stat.getTotal());

        stat.setCurrentModifier(120);
        assertEquals(100, stat.getModifier().current());
    }

    @Test
    public void testAddToCurrentBase() {
        stat.addToCurrentBase(20);
        assertEquals(70, stat.getBase().current());
        assertEquals(70, stat.getTotal());

        stat.addToCurrentBase(50);
        assertEquals(100, stat.getBase().current());
        assertEquals(100, stat.getTotal());
    }

    @Test
    public void testAddToCurrentModifier() {
        stat.addToCurrentModifier(30);
        assertEquals(30, stat.getModifier().current());
        assertEquals(80, stat.getTotal());

        stat.addToCurrentModifier(80);
        assertEquals(100, stat.getModifier().current());
        assertEquals(150, stat.getTotal());
    }

    @Test
    public void testAddToMaxBase() {
        stat.addToMaxBase(20);
        assertEquals(120, stat.getBase().max());

        assertEquals(50, stat.getBase().current());
    }

    @Test
    public void testGetTotal() {
        assertEquals(50, stat.getTotal());

        stat.addToCurrentBase(10);
        stat.addToCurrentModifier(15);
        assertEquals(75, stat.getTotal());
    }

    @Test
    public void testToString() {
        String expectedString = "FORCE 50 : (50 + 0)";
        assertEquals(expectedString, stat.toString());

        stat.addToCurrentModifier(10);
        expectedString = "FORCE 60 : (50 + 10)";
        assertEquals(expectedString, stat.toString());
    }

    @Test
    public void testSetMinBase() {
        stat.setMinBase(20);
        assertEquals(20, stat.getBase().min());

        stat.setCurrentBase(10);
        assertEquals(20, stat.getBase().current());
    }
}

