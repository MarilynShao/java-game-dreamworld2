package demoworld.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    private Character character;
    private DemoWorld rulebook;

    @Before
    public void setUp() {
        rulebook = new DemoWorld();
        character = new Character("Test Character", rulebook);
    }

    @Test
    public void testCharacterInitialization() {
        assertEquals("Test Character", character.getName());
        assertNotNull(character.getHitpoints());
        assertEquals(6, character.getHitpoints().getTotal());
    }

    @Test
    public void testAddSpecialty() {
        Specialty specialty = rulebook.getSpecialtyByName("farmer");
        character.addSpecialty(specialty);
        assertTrue(character.getSpecialty().all().contains(specialty));
    }

    @Test
    public void testRemoveSpecialty() {
        Specialty specialty = rulebook.getSpecialtyByName("farmer");
        character.addSpecialty(specialty);
        character.removeSpecialty(specialty);
        assertFalse(character.getSpecialty().all().contains(specialty));
    }

    @Test
    public void testDamageCharacter() {
        character.damage(3);
        assertEquals(3, character.getHitpoints().getTotal());
    }

    @Test
    public void testHealCharacter() {
        character.damage(3);
        character.heal(2);
        assertEquals(5, character.getHitpoints().getTotal());
    }

    @Test
    public void testCharacterDeath() {
        character.damage(6);
        assertTrue(character.getHitpoints().isDead());
    }

    @Test
    public void testAdjustXp() {
        character.adjustXp(5);
        assertEquals(0, character.getExperience().current());
        assertEquals(7, character.getHitpoints().getBase().max());
    }
}

