package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.ALICE;
import static seedu.address.testutil.TypicalRemark.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RemarkBuilder;

public class RemarkTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Remark company = new RemarkBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> company.getTags().remove(0));
    }

    @Test
    public void isSameRemark() {
        // same object -> returns true
        assertTrue(ALICE.isSameRemark(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRemark(null));

        // same name, all other attributes different -> returns true
        Remark editedAlice = new RemarkBuilder(ALICE).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRemark(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new RemarkBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameRemark(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Remark editedBob = new RemarkBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameRemark(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new RemarkBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameRemark(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Remark aliceCopy = new RemarkBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different Remark -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Remark editedAlice = new RemarkBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new RemarkBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new RemarkBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toString_company_returnsValueInRemark() {
        Remark company = new RemarkBuilder().withName("Alice Pauline")
                .withAddress("WCP Macs")
                .withTags("friends").build();
        assertEquals(company.toString(), "Alice Pauline; Address: WCP Macs; Tags: [friends]");
    }
}
