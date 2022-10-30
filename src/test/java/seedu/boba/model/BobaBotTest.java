package seedu.boba.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.exceptions.DuplicatePersonException;
import seedu.boba.testutil.CustomerBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.ALICE;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

public class BobaBotTest {

    private final BobaBot bobaBot = new BobaBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bobaBot.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bobaBot.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBobaBot_replacesData() {
        BobaBot newData = getTypicalBobaBot();
        bobaBot.resetData(newData);
        assertEquals(newData, bobaBot);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two customers with the same identity fields
        Customer editedAlice = new CustomerBuilder(ALICE).withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD)
                .build();
        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        BobaBotStub newData = new BobaBotStub(newCustomers);

        assertThrows(DuplicatePersonException.class, () -> bobaBot.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bobaBot.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInBobaBot_returnsFalse() {
        assertFalse(bobaBot.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInBobaBot_returnsTrue() {
        bobaBot.addPerson(ALICE);
        assertTrue(bobaBot.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInBobaBot_returnsTrue() {
        bobaBot.addPerson(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD)
                .build();
        assertTrue(bobaBot.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bobaBot.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyBobaBot whose customers list can violate interface constraints.
     */
    private static class BobaBotStub implements ReadOnlyBobaBot {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();

        BobaBotStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getPersonList() {
            return customers;
        }
    }

}
