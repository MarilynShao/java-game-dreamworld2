package demoworld.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValueTest {

    @Test
    public void testConstructor() {
        Value value = new Value(0, 100, 50);
        assertEquals(0, value.min());
        assertEquals(100, value.max());
        assertEquals(50, value.current());

        value = new Value(0, 100, 150);
        assertEquals(100, value.current());

        value = new Value(0, 100, -10);
        assertEquals(0, value.current());
    }

    @Test
    public void testSetMin() {
        Value value = new Value(10, 100, 50);
        value.setMin(20);
        assertEquals(20, value.min());
        assertEquals(50, value.current());

        value.setMin(60);
        assertEquals(60, value.current());
    }

    @Test
    public void testSetMax() {
        Value value = new Value(0, 100, 50);
        value.setMax(80);
        assertEquals(80, value.max());
        assertEquals(50, value.current());

        value.setMax(40);
        assertEquals(40, value.current());
    }

    @Test
    public void testSetCurrent() {
        Value value = new Value(0, 100, 50);
        value.setCurrent(80);
        assertEquals(80, value.current());

        value.setCurrent(120);
        assertEquals(100, value.current());

        value.setCurrent(-10);
        assertEquals(0, value.current());
    }

    @Test
    public void testAddToCurrent() {
        Value value = new Value(0, 100, 50);
        value.addToCurrent(10);
        assertEquals(60, value.current());

        value.addToCurrent(50);
        assertEquals(100, value.current());

        value.addToCurrent(-30);
        assertEquals(70, value.current());

        value.addToCurrent(-100);
        assertEquals(0, value.current());
    }

    @Test
    public void testAddToMax() {
        Value value = new Value(0, 100, 50);
        value.addToMax(20);
        assertEquals(120, value.max());
        assertEquals(50, value.current());

        value.setCurrent(110);
        value.addToMax(-50);
        assertEquals(70, value.current());
        assertEquals(70, value.max());
    }
}

