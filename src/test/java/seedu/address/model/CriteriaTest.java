package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CriteriaBuilder;

/**
 * Unit testing for Criteria.
 */
public class CriteriaTest {

    @Test
    public void constructor_nullCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Criteria(null));
    }

    @Test
    public void constructor_invalidCriteria_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Criteria("status"));
    }

    @Test
    public void isValidCriteria_nullCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Criteria.isValidCriteria(null));
    }

    @Test
    public void isValidCriteria_invalidCriteria_returnsFalse() {
        //Invalid word selected for criteira
        assertFalse(Criteria.isValidCriteria("status"));

        //Criteria word with trailing whitespace
        assertFalse(Criteria.isValidCriteria("priority "));
    }

    @Test
    public void isValidCriteria_validCriteria_returnsTrue() {
        //Valid criteria (priority)
        assertTrue(Criteria.isValidCriteria("priority"));

        //Valid criteria with different casing
        assertTrue(Criteria.isValidCriteria("PrIoRiTy"));

        //Valid criteria (deadline)
        assertTrue(Criteria.isValidCriteria("deadline"));

        //Valid Criteria (module)
        assertTrue(Criteria.isValidCriteria("module"));

        //Valid Criteria (description)
        assertTrue(Criteria.isValidCriteria("description"));
    }

    @Test
    public void testEquals() {
        Criteria criteria = new CriteriaBuilder().build();
        //Same object
        assertTrue(criteria.equals(criteria));

        Criteria sameCriteria = new CriteriaBuilder(criteria).build();
        //Different object but same criteria string
        assertTrue(criteria.equals(sameCriteria));

        Criteria differentCriteria = new CriteriaBuilder().withCriteria("deadline").build();
        //Different objects with different criterias
        assertFalse(criteria.equals(differentCriteria));

        //Different object type
        assertFalse(criteria.equals(121212));

        //null value returns false
        assertFalse(criteria.equals(null));
    }
}
