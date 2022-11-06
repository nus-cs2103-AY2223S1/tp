package seedu.travelr.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.travelr.model.ReadOnlyTravelr;

class SampleDataUtilTest {

    @Test
    void testGetSampleTrips() {
        int length = SampleDataUtil.getSampleTrips().length;
        assertEquals(SampleDataUtil.getSampleTrips().length, length);
    }

    @Test
    void testGetSampleEvents() {
        int length = SampleDataUtil.getSampleEvents().length;
        assertEquals(SampleDataUtil.getSampleEvents().length, length);
    }

    @Test
    void testGetSampleTravelr() {
        ReadOnlyTravelr readOnlyTravelr = SampleDataUtil.getSampleTravelr();
        assertEquals(readOnlyTravelr, SampleDataUtil.getSampleTravelr());
    }

    @Test
    void testGetEventSet() {
        assertEquals(SampleDataUtil.getEventSet("friends").size(), 1);
    }
}
