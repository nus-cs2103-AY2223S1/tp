package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Company company = new CompanyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> company.getTags().remove(0));
    }

    @Test
    public void isSameCompany() {
        // same object -> returns true
        assertTrue(ALICE.isSameCompany(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCompany(null));

        // same name, all other attributes different -> returns true
        Company editedAlice = new CompanyBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameCompany(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCompany(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Company editedBob = new CompanyBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCompany(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CompanyBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCompany(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Company aliceCopy = new CompanyBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different company -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Company editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));


        // different address -> returns false
        editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CompanyBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
