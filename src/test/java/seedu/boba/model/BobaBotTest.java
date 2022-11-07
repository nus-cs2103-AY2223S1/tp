package seedu.boba.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.ALICE;
import static seedu.boba.testutil.TypicalCustomers.BOB;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.customer.exceptions.DuplicatePersonException;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;
import seedu.boba.testutil.CustomerBuilder;

public class BobaBotTest {

    private final BobaBot bobaBot = new BobaBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bobaBot.getPersonList());
    }

    @Test
    public void overloadedConstructor() {
        bobaBot.addPerson(ALICE);

        BobaBot result = new BobaBot(bobaBot);
        assertEquals(bobaBot.getPersonList(), result.getPersonList());
        assertEquals(bobaBot, result);
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

    @Test
    public void findNum_returnsTrue() {
        bobaBot.addPerson(ALICE);
        assertEquals(bobaBot.findNum(new Phone("94351253")), 0);
    }

    @Test
    public void findNum_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> bobaBot.findNum(new Phone("94351253")));
    }

    @Test
    public void findEmail_returnsTrue() {
        bobaBot.addPerson(ALICE);
        assertEquals(bobaBot.findEmail(new Email("alice@example.com")), 0);
    }

    @Test
    public void findEmail_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> bobaBot.findEmail(new Email("alice@example.com")));
    }

    @Test
    public void getCurrentRewardViaPhone_returnsTrue() {
        bobaBot.addPerson(ALICE);
        assertEquals(bobaBot.getCurrentReward(new Phone("94351253")), new Reward("123"));
    }

    @Test
    public void getCurrentRewardViaEmail_returnsTrue() {
        bobaBot.addPerson(ALICE);
        assertEquals(bobaBot.getCurrentReward(new Email("alice@example.com")), new Reward("123"));
    }

    @Test
    public void strictlyEquals_returnsTrue() {
        BobaBot sampleBobaBot = new BobaBot();

        assertTrue(bobaBot.strictlyEquals(bobaBot));
        assertTrue(bobaBot.strictlyEquals(sampleBobaBot));
    }

    @Test
    public void hashCode_returnsTrue() {
        BobaBot sampleBobaBot = new BobaBot();
        //The hashcode method is dependent on the ObservableList of Customers within bobaBot, thus since both
        //instances of bobaBot has an empty list, their hashcode would be the same
        assertEquals(bobaBot.hashCode(), bobaBot.hashCode());
        assertEquals(bobaBot.hashCode(), sampleBobaBot.hashCode());

        //After adding the customer ALICE into sampleBobaBot, the ObservableList within now differs from the one
        //in bobaBot, therefore their hashcode would be different
        sampleBobaBot.addPerson(ALICE);
        assertNotEquals(bobaBot.hashCode(), sampleBobaBot.hashCode());
    }

    @Test
    public void toString_returnsTrue() {
        String expectedMessage = "0 persons";
        assertEquals(bobaBot.toString(), expectedMessage);
        bobaBot.addPerson(ALICE);
        String expectedMessage1 = "1 persons";
        assertEquals(bobaBot.toString(), expectedMessage1);
    }

    @Test
    public void removePerson_returnsTrue() {
        BobaBot expectedBobaBot = new BobaBot();
        //Initially bobaBot and expectedBobaBot are both empty so equals method returns true
        assertEquals(bobaBot, expectedBobaBot);

        //After adding Customer ALICE to bobaBot, the ObservableList within both differs so equals method returns false
        bobaBot.addPerson(ALICE);
        assertNotEquals(bobaBot, expectedBobaBot);

        //After removing the added Customer (ALICE) from bobaBot, both ObservableList should be the same again
        bobaBot.removePerson(ALICE);
        assertEquals(bobaBot, expectedBobaBot);
    }

    @Test
    public void setPerson_returnsTrue() {
        bobaBot.addPerson(BOB);
        Customer editedCustomer = new CustomerBuilder().withPhone(VALID_PHONE_BOB).build();
        bobaBot.setPerson(BOB, editedCustomer);

        BobaBot expectedBobaBot = new BobaBot();
        expectedBobaBot.addPerson(editedCustomer);

        assertEquals(bobaBot, expectedBobaBot);
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
