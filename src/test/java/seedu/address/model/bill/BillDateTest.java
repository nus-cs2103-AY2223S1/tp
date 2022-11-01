package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BillDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BillDate(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidBillDate = "";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void constructor_invalidBillDate1_throwsIllegalArgumentException() {
        String invalidBillDate = "2022/01-12";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void constructor_invalidBillDate2_throwsIllegalArgumentException() {
        String invalidBillDate = "2022/01-12 12:34";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void constructor_invalidBillDate3_throwsIllegalArgumentException() {
        String invalidBillDate = "2022/01/12 12:61";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void constructor_invalidBillDate4_throwsIllegalArgumentException() {
        String invalidBillDate = "22/01/12 12:01";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void constructor_invalidBillDate5_throwsIllegalArgumentException() {
        String invalidBillDate = "1922/01/12 1201";
        assertThrows(IllegalArgumentException.class, () -> new BillDate(invalidBillDate));
    }

    @Test
    public void equals() {
        BillDate billDate1 = new BillDate("2022-11-13");
        BillDate billDate2 = new BillDate("2022-11-13");
        BillDate billDate3 = new BillDate("2022-11-14");
        BillDate billDate4 = new BillDate("2022-11-13");
        assertTrue(billDate1.equals(billDate1));
        assertTrue(billDate1.equals(billDate2));
        assertTrue(billDate1.equals(billDate4));
        assertFalse(billDate2.equals(billDate3));
        assertFalse(billDate2.equals("12345"));
        assertFalse(billDate2.equals(null));
    }

    @Test
    public void isValidDate() {
        assertThrows(NullPointerException.class, () -> BillDate.isValidBillDate(null));
        assertFalse(BillDate.isValidBillDate(""));
        assertTrue(BillDate.isValidBillDate("2012-12-31"));
        assertTrue(BillDate.isValidBillDate("2012-12-31"));
        assertTrue(BillDate.isValidBillDate("2023-06-30"));
        assertFalse(BillDate.isValidBillDate("2021/12/25"));
        assertFalse(BillDate.isValidBillDate("2021-13-01"));
        assertFalse(BillDate.isValidBillDate("12-25-2022"));
        assertFalse(BillDate.isValidBillDate("2021-1-10 01:00"));
        assertFalse(BillDate.isValidBillDate("0000-12-10 00:00"));
    }

    @Test
    public void hashCodeTest() {
        BillDate billDate1 = new BillDate("2022-11-13");
        BillDate billDate2 = new BillDate("2022-11-12");
        BillDate billDate3 = new BillDate("2022-11-13");
        assertTrue(billDate1.hashCode() == billDate1.hashCode());
        assertFalse(billDate1.hashCode() == billDate2.hashCode());
        assertTrue(billDate1.hashCode() == billDate3.hashCode());
    }
}
