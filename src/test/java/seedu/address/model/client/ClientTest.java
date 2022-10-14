package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.BOB;
import static seedu.address.testutil.TypicalPoc.AMY;
import static seedu.address.testutil.TypicalPoc.BENSON;
import static seedu.address.testutil.TypicalPoc.ELLE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.poc.Poc;
import seedu.address.model.poc.UniquePocList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.TransactionLog;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.PocBuilder;
import seedu.address.testutil.TypicalCompanies;
import seedu.address.testutil.TypicalPoc;

public class ClientTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        UniquePocList pocs = new UniquePocList();
        TransactionLog transactions = new TransactionLog();
        Set<Tag> tags = new HashSet<>();
        assertThrows(NullPointerException.class, () ->
                new Client(null, new Address(VALID_ADDRESS_BOB), tags, pocs, transactions));
    }

    @Test
    public void constructor_nullAddress_throwsNullPointerException() {
        UniquePocList pocs = new UniquePocList();
        TransactionLog transactions = new TransactionLog();
        Set<Tag> tags = new HashSet<>();
        assertThrows(NullPointerException.class, () ->
                new Client(new Name(VALID_NAME_BOB), null, tags, pocs, transactions));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, TypicalPoc.ALICE));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new CompanyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSameCompany() {
        // same object -> returns true
        assertTrue(ALICE.isSameCompany(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCompany(null));

        // same name, all other attributes different -> returns true
        Client editedAlice = new CompanyBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameCompany(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCompany(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Client editedBob = new CompanyBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCompany(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CompanyBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCompany(editedBob));
    }

    @Test
    public void addPoc() {
        Client aliceCopy = new CompanyBuilder(ALICE).build();
        Poc elleCopy = new PocBuilder(ELLE).build();
        aliceCopy.addPoc(elleCopy);
        assertTrue(aliceCopy.getPocs().contains(elleCopy));

        assertFalse(aliceCopy.getPocs().contains(AMY));
    }

    @Test
    public void containsPoc() {
        Client client = new CompanyBuilder(TypicalCompanies.BENSON).build();
        assertTrue(client.hasPoc(BENSON));

        // Invalid Poc
        assertFalse(client.hasPoc(AMY));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new CompanyBuilder(ALICE).build();
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
        Client editedAlice = new CompanyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CompanyBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringTest() {
        Client client = new CompanyBuilder(TypicalCompanies.BENSON).build();
        assertEquals(client.toString(), "Benson Meier; Address: 311, Clementi Ave 2, "
                + "#02-25; Tags: [owesMoney][friends]; POCs: Benson Meier; Total transactions: $0");
    }
}
