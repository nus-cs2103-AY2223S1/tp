package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Mod;

public class ModComparatorTest {

    private final ModComparator comparator = new ModComparator();

    @Test
    public void compare_compareBothNull() {
        assertThrows(NullPointerException.class, () -> comparator.compare(null, null));
    }

    @Test
    public void compare_compareOneNull() {
        assertThrows(NullPointerException.class, () -> comparator.compare(new Mod("CS2103T"), null));
        assertThrows(NullPointerException.class, () -> comparator.compare(null, new Mod("CS2103T")));
    }

    @Test
    public void compare_completionStatus() {
        assertTrue(comparator.compare(new Mod("CS2103T", true), new Mod("CS2103T")) > 0);
        assertTrue(comparator.compare(new Mod("CS2103T"), new Mod("CS2103T", true)) < 0);
    }

    @Test
    public void compare_modName() {
        assertTrue(comparator.compare(new Mod("DS2103T"), new Mod("CS2103T")) > 0);
        assertTrue(comparator.compare(new Mod("AS2103T"), new Mod("CS2103T")) < 0);
    }

    @Test
    public void compare_modNameAndCompletion() {
        assertTrue(comparator.compare(new Mod("DS2103T", true), new Mod("CS2103T", false)) > 0);
        assertTrue(comparator.compare(new Mod("CS2103T", true), new Mod("DS2103T", false)) > 0);
        assertTrue(comparator.compare(new Mod("DS2103T", false), new Mod("CS2103T", true)) < 0);
        assertTrue(comparator.compare(new Mod("CS2103T", false), new Mod("DS2103T", true)) < 0);
    }
}
