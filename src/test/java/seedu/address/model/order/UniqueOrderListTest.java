package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.exceptions.DuplicateOrderException;
import seedu.address.model.order.exceptions.OrderNotFoundException;
import seedu.address.testutil.TypicalOrders;

public class UniqueOrderListTest {
    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(TypicalOrders.ORDER_1));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        assertTrue(uniqueOrderList.contains(TypicalOrders.ORDER_1));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void add_duplicateOrder_throwsDuplicateOrderException() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.add(TypicalOrders.ORDER_1));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(null, TypicalOrders.ORDER_1));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(TypicalOrders.ORDER_1, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.setOrder(TypicalOrders.ORDER_1,
                TypicalOrders.ORDER_2));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.setOrder(TypicalOrders.ORDER_1, TypicalOrders.ORDER_1);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(TypicalOrders.ORDER_1);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasSameIdentity_success() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.setOrder(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(TypicalOrders.ORDER_2);
        assertEquals(uniqueOrderList, expectedUniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasDifferentIdentity_success() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.setOrder(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(TypicalOrders.ORDER_2);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasNonUniqueIdentity_throwsDuplicateOrderException() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.add(TypicalOrders.ORDER_2);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrder(TypicalOrders.ORDER_1,
                TypicalOrders.ORDER_2));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.remove(TypicalOrders.ORDER_1));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.remove(TypicalOrders.ORDER_1);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_nullUniqueOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((UniqueOrderList) null));
    }

    @Test
    public void setOrder_uniqueOrderList_replacesOwnListWithProvidedUniqueOrderList() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(TypicalOrders.ORDER_1);
        uniqueOrderList.setOrders(expectedUniqueOrderList);
        assertEquals(uniqueOrderList, expectedUniqueOrderList);
    }

    @Test
    public void setOrder_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((List<Order>) null));
    }

    @Test
    public void setOrder_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(TypicalOrders.ORDER_1);
        List<Order> orderList = Collections.singletonList(TypicalOrders.ORDER_1);
        uniqueOrderList.setOrders(orderList);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(TypicalOrders.ORDER_1);
        assertEquals(uniqueOrderList, expectedUniqueOrderList);
    }

    @Test
    public void setOrders_listWithDuplicateOrders_throwsDuplicateOrderException() {
        List<Order> listWithDuplicateOrders = Arrays.asList(TypicalOrders.ORDER_1, TypicalOrders.ORDER_1);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrders(listWithDuplicateOrders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueOrderList.asUnmodifiableObservableList().remove(0));
    }

}
