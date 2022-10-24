package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_BAD_BUYER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_GOOD_BUYER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.GOOD_BUYER;
import static seedu.address.testutil.TypicalRemark.SHORT_REMARK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RemarkBuilder;

public class RemarkTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Remark remark = new RemarkBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> remark.getTags().remove(0));
    }

    @Test
    public void isSameRemark() {
        // same object -> returns true
        assertTrue(SHORT_REMARK.isSameRemark(SHORT_REMARK));

        // null -> returns false
        assertFalse(SHORT_REMARK.isSameRemark(null));

        // same text, all other attributes different -> returns true
        Remark editedRemark = new RemarkBuilder(GOOD_BUYER)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(GOOD_BUYER.isSameRemark(editedRemark));

        // different text, all other attributes same -> returns false
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_BAD_BUYER).build();
        assertFalse(GOOD_BUYER.isSameRemark(editedRemark));

        // name differs in case, all other attributes same -> returns false
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(VALID_TEXT_GOOD_BUYER.toLowerCase()).build();
        assertFalse(GOOD_BUYER.isSameRemark(editedRemark));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TEXT_GOOD_BUYER + " ";
        editedRemark = new RemarkBuilder(GOOD_BUYER).withText(nameWithTrailingSpaces).build();
        assertFalse(GOOD_BUYER.isSameRemark(editedRemark));
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

        // different tags -> returns false
        editedRemark = new RemarkBuilder(GOOD_BUYER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(GOOD_BUYER.equals(editedRemark));
    }

    @Test
    public void toString_remark_returnsValueInRemark() {
        Remark remark = new RemarkBuilder().withText("This is a long remark that should be shortened")
                .withTags("friends").build();
        assertEquals(remark.toString(), "This is a long ...; Tags: [friends]");
    }
}
