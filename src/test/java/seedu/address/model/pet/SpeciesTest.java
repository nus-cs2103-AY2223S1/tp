package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class SpeciesTest {
    @Test
    public void constructor_null_emptyString() {
        Species species = new Species(null);
        Species expected = new Species("");
        assertEquals(species, expected);
    }

    @Test
    public void getSpecies() {
        Species species = new Species("fish");
        String expected = "fish";
        assertEquals(species.getSpecies(), expected);
    }

    @Test
    public void isSameSpecies() {
        Species species = new Species("hedgehog");
        assertEquals(species, species);
    }

    @Test
    public void equals() {
        Species species1 = new Species("hedgehog");
        Species species2 = new Species("hedgehog");
        assertEquals(species1, species2);
    }

    @Test
    public void notEquals() {
        Species species1 = new Species("hedgehog");
        Species species2 = new Species("dog");
        assertNotEquals(species1, species2);
    }

    @Test
    public void toStringTest() {
        Species species = new Species("hedgehog");
        String expected = "hedgehog";
        assertEquals(species.toString(), expected);
    }

    @Test
    public void hashcode() {
        int expected = "hedgehog".hashCode();
        Species species = new Species("hedgehog");
        assertEquals(species.hashCode(), expected);
    }
}
