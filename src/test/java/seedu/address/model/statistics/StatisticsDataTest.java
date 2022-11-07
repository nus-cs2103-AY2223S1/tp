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
    public void isSameStatisticData() {
        StatisticData modelData = new StatisticData("Test", 5.0);

        //null StatisticData
        assertThrows(NullPointerException.class, () -> modelData.isSameStatisticData(new StatisticData(null, null)));

        //different StatisticData
        assertFalse(modelData.isSameStatisticData(new StatisticData("Not test", 1.0)));
        assertFalse(modelData.isSameStatisticData(new StatisticData("Not test 2", 5.0)));

        //same StatisticData
        assertTrue(modelData.isSameStatisticData(modelData));
        assertTrue(modelData.isSameStatisticData(new StatisticData("Test", 1.0)));
        assertTrue(modelData.isSameStatisticData(new StatisticData("Test", 5.0)));
    }

}
