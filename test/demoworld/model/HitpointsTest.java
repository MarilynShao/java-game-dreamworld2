package demoworld.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HitpointsTest {

    private Hitpoints hitpoints;

    @Before
    public void setUp() {
        hitpoints = new Hitpoints(9, 5);
    }

    @Test
    public void testInitialHitpoints() {
        assertEquals(5, hitpoints.getBase().current());
        assertEquals(0, hitpoints.getTempHp());
        assertEquals(9, hitpoints.getBase().max());
        assertFalse(hitpoints.isDead());
    }

    @Test
    public void testDamage() {
        hitpoints.damage(2);
        assertEquals(3, hitpoints.getTotal());
    }

    @Test
    public void testDamageExceedingCurrentHp() {
        hitpoints.damage(6);
        assertEquals(0, hitpoints.getTotal());
        assertTrue(hitpoints.isDead());
    }

    @Test
    public void testDamageWithTempHp() {
        hitpoints.setTempHp(2);
        hitpoints.damage(3);
        assertEquals(4, hitpoints.getTotal());
        assertEquals(0, hitpoints.getTempHp());
    }

    @Test
    public void testHeal() {
        hitpoints.damage(2);
        hitpoints.heal(1);
        assertEquals(4, hitpoints.getTotal());
    }

    @Test
    public void testHealBeyondMax() {
        hitpoints.heal(10);
        assertEquals(9, hitpoints.getTotal());
    }

    @Test
    public void testSetTempHp() {
        hitpoints.setTempHp(5);
        assertEquals(5, hitpoints.getTempHp());
    }

    @Test
    public void testSetNegativeTempHp() {
        hitpoints.setTempHp(-2);
        assertEquals(0, hitpoints.getTempHp());
    }

    @Test
    public void testResetTempHp() {
        hitpoints.setTempHp(5);
        hitpoints.resetTempHp();
        assertEquals(0, hitpoints.getTempHp());
    }

    @Test
    public void testToString() {
        hitpoints.setTempHp(3);
        String expected = "HITPOINTS:8/9\n[ ][ ][ ][ ][ ][ ][ ][ ][ ]\nTEMP HP: 3";
        assertEquals(expected, hitpoints.toString());
    }
}

