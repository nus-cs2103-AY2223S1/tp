package foodwhere.model.stall.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StallsComparatorListTest {

    @Test
    public void getCriteria_generalTesting_success() {
        String nameCriteria = "name (0 to 9, then A to Z)";
        assertEquals(nameCriteria, StallsComparatorList.NAME.getCriteria());

        String reversedCriteria = "name (Z to A, then 9 to 0)";
        assertEquals(reversedCriteria, StallsComparatorList.REVERSEDNAME.getCriteria());
    }

    @Test
    public void getComparator_generalTesting_success() {
        assertTrue(new NameComparator().equals(StallsComparatorList.NAME.getComparator()));
        assertTrue(new NameComparator().reversed().equals(StallsComparatorList.REVERSEDNAME.getComparator()));
    }
}
