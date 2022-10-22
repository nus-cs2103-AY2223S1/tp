package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalRemark.AMY;
import static seedu.address.testutil.TypicalRemark.BENSON;
import static seedu.address.testutil.TypicalRemark.ELLE;
import static seedu.address.testutil.TypicalTransaction.BUY_BURGERS;
import static seedu.address.testutil.TypicalTransaction.BUY_ORANGE;
import static seedu.address.testutil.TypicalTransaction.SELL_PANTS;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.UniqueRemarkList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.RemarkBuilder;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalRemark;

public class ClientTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        TransactionLog transactions = new TransactionLog();
        Set<Tag> tags = new HashSet<>();
        assertThrows(NullPointerException.class, () ->
                new Client(null, new Address(VALID_ADDRESS_BOB), new ClientPhone(VALID_PHONE_BOB),
                        new ClientEmail(VALID_EMAIL_BOB), tags, remarks, transactions));
    }

    @Test
    public void constructor_nullAddress_throwsNullPointerException() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        TransactionLog transactions = new TransactionLog();
        Set<Tag> tags = new HashSet<>();
        assertThrows(NullPointerException.class, () ->
                new Client(new Name(VALID_NAME_BOB), null, new ClientPhone(VALID_PHONE_BOB),
                        new ClientEmail(VALID_EMAIL_BOB), tags, remarks, transactions));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, TypicalRemark.ALICE));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new ClientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(ALICE.isSameClient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameClient(null));

        // same name, all other attributes different -> returns true
        Client editedAlice = new ClientBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameClient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Client editedBob = new ClientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameClient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ClientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameClient(editedBob));
    }

    @Test
    public void addRemark() {
        Client aliceCopy = new ClientBuilder(ALICE).build();
        Remark elleCopy = new RemarkBuilder(ELLE).build();
        aliceCopy.addRemark(elleCopy);
        assertTrue(aliceCopy.getRemarks().contains(elleCopy));

        assertFalse(aliceCopy.getRemarks().contains(AMY));
    }

    @Test
    public void containsRemark() {
        Client client = new ClientBuilder(TypicalClients.BENSON).build();
        assertTrue(client.hasRemark(BENSON));

        // Invalid Remark
        assertFalse(client.hasRemark(AMY));
    }

    @Test
    public void getSellTransactionList() {
        Client alice = new ClientBuilder().build();
        alice.addTransaction(SELL_PANTS);
        alice.addTransaction(BUY_BURGERS);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(SELL_PANTS);
        assertTrue(alice.getSellTransactionList().equals(internalList));

        ObservableList<Transaction> list2 = FXCollections.observableArrayList();
        list2.add(BUY_BURGERS);
        assertFalse(alice.getSellTransactionList().equals(list2));

    }

    @Test
    public void getBuyTransactionList() {
        Client alice = new ClientBuilder().build();
        alice.addTransaction(SELL_PANTS);
        alice.addTransaction(BUY_BURGERS);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(BUY_BURGERS);
        assertTrue(alice.getBuyTransactionList().equals(internalList));

        ObservableList<Transaction> list2 = FXCollections.observableArrayList();
        list2.add(BUY_ORANGE);
        assertFalse(alice.getBuyTransactionList().equals(list2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different client -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringTest() {
        Client client = new ClientBuilder(TypicalClients.BENSON).build();

        assertEquals(client.toString(), "Benson Meier; Address: 311, Clementi Ave 2, "
                + "#02-25; Phone: 12112121; Email: ben@gmail.com; "
                + "Tags: [owesMoney][friends]; Remarks: Benson Meier; Total transactions: -$423.0");
    }
}
