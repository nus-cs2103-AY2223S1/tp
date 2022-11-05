package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_BAD_BUYER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_GOOD_BUYER;
import static seedu.address.testutil.TypicalRemark.GOOD_BUYER;
import static seedu.address.testutil.TypicalRemark.SHORT_REMARK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RemarkBuilder;

public class RemarkTest {

    @Test
    public void isSameRemark() {
        // same object -> returns true
        assertTrue(SHORT_REMARK.isSameRemark(SHORT_REMARK));

        // null -> returns false
        assertFalse(SHORT_REMARK.isSameRemark(null));

        // same text -> returns true
        Remark editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_GOOD_BUYER).build();
        assertTrue(GOOD_BUYER.isSameRemark(editedRemark));

        // different text -> returns false
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_BAD_BUYER).build();
        assertFalse(GOOD_BUYER.isSameRemark(editedRemark));

        // text differs in case -> returns true
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_GOOD_BUYER.toLowerCase()).build();
        assertTrue(GOOD_BUYER.isSameRemark(editedRemark));

        // name has trailing spaces -> returns true
        String nameWithTrailingSpaces = VALID_TEXT_GOOD_BUYER + " ";
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(nameWithTrailingSpaces).build();
        assertTrue(GOOD_BUYER.isSameRemark(editedRemark));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Remark aliceCopy = new RemarkBuilder(GOOD_BUYER).build();
        assertTrue(GOOD_BUYER.equals(aliceCopy));

        // same object -> returns true
        assertTrue(GOOD_BUYER.equals(GOOD_BUYER));

        // null -> returns false
        assertFalse(GOOD_BUYER.equals(null));

        // different type -> returns false
        assertFalse(GOOD_BUYER.equals(5));

        // different Remark -> returns false
        assertFalse(GOOD_BUYER.equals(SHORT_REMARK));

        // different name -> returns false
        Remark editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_BAD_BUYER).build();
        assertFalse(GOOD_BUYER.equals(editedRemark));

    }

    @Test
    public void toString_remark_returnsValueInRemark() {
        Remark remark = new RemarkBuilder().withText("This is a long remark that should be shortened").build();
        assertEquals(remark.toString(), "This is a long ...");
    }
}
