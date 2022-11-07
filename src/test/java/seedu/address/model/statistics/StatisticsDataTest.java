package seedu.address.model.statistics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatisticsDataTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StatisticData(null, null));
    }

    @Test
    public void isSameName() {
        StatisticData modelData = new StatisticData("Test", 5.0);

        //null StatisticData
        assertThrows(NullPointerException.class, () -> modelData.isSameName(new StatisticData(null, null)));

        //different StatisticData
        assertFalse(modelData.isSameName(new StatisticData("Not test", 1.0)));
        assertFalse(modelData.isSameName(new StatisticData("Not test 2", 5.0)));

        //same StatisticData
        assertTrue(modelData.isSameName(modelData));
        assertTrue(modelData.isSameName(new StatisticData("Test", 1.0)));
        assertTrue(modelData.isSameName(new StatisticData("Test", 5.0)));
    }

}
