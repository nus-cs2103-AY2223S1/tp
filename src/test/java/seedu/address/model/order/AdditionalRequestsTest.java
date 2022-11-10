package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AdditionalRequestsTest {
    @Test
    public void constructor_invalidAdditionalRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AdditionalRequests((ArrayList<String>) null));
    }

    @Test
    public void equals() {
        AdditionalRequests firstAdditionalRequests = new AdditionalRequests(List.of("fluffy"));
        AdditionalRequests secondAdditionalRequests = new AdditionalRequests(Arrays.asList("pink", "floyd"));

        assertEquals(firstAdditionalRequests, firstAdditionalRequests);
        assertNotEquals(firstAdditionalRequests, null);
        assertNotEquals(firstAdditionalRequests, 1);

        AdditionalRequests firstAdditionalRequestsCopy = new AdditionalRequests(List.of("fluffy"));
        assertEquals(firstAdditionalRequests, firstAdditionalRequestsCopy);

        assertNotEquals(firstAdditionalRequests, secondAdditionalRequests);
    }

    @Test
    public void hashcode_sameObject_returnsTrue() {
        AdditionalRequests firstAdditionalRequests = new AdditionalRequests(List.of("fluffy"));
        assertEquals(firstAdditionalRequests.hashCode(), firstAdditionalRequests.hashCode());
    }

    @Test
    public void hashcode_differentObject_returnsFalse() {
        AdditionalRequests firstAdditionalRequests = new AdditionalRequests(List.of("fluffy"));
        AdditionalRequests secondAdditionalRequests = new AdditionalRequests(Arrays.asList("pink", "floyd"));
        assertNotEquals(firstAdditionalRequests.hashCode(), secondAdditionalRequests.hashCode());
    }

    @Test
    public void toString_sameObject_returnsTrue() {
        AdditionalRequests firstAdditionalRequests = new AdditionalRequests(List.of("fluffy"));
        assertEquals(firstAdditionalRequests.toString(), firstAdditionalRequests.toString());
    }

    @Test
    public void toString_differentObject_returnsFalse() {
        AdditionalRequests firstAdditionalRequests = new AdditionalRequests(List.of("fluffy"));
        AdditionalRequests secondAdditionalRequests = new AdditionalRequests(Arrays.asList("pink", "floyd"));
        assertNotEquals(firstAdditionalRequests.toString(), secondAdditionalRequests.toString());
    }
}
