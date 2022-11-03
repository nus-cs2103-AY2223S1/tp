package tracko.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static tracko.tracko.commands.logic.CommandTestUtil.VALID_ADDRESS_BOB;
// import static tracko.tracko.commands.logic.CommandTestUtil.VALID_TAG_HUSBAND;
import static tracko.testutil.Assert.assertThrows;
// import static tracko.testutil.TypicalOrders.ORDER_1;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

// import java.util.Arrays;
// import java.util.Collection;
import java.util.Collections;
// import java.util.List;

import org.junit.jupiter.api.Test;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import tracko.model.person.Person;
// import tracko.model.person.exceptions.DuplicatePersonException;
// import tracko.testutil.OrderBuilder;

public class TrackOTest {

    private final TrackO addressBook = new TrackO();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getOrderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TrackO newData = getTrackOWithTypicalOrders();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }
}
