package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.AVA;
import static seedu.address.testutil.TypicalPersons.BEN;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class StatisticsCalculatorTest {

    private final AddressBookStub addressBookStub = new AddressBookStub();
    private final StatisticsCalculator statisticsCalculator = new StatisticsCalculator(addressBookStub);

    @Test
    public void calculates_emptyAddressBook_size() {
        assertEquals(0, statisticsCalculator.getSize());
    }

    @Test
    public void calculates_emptyAddressBook_moneyOwed() {
        assertEquals("$0", statisticsCalculator.getAmountOwed());
    }

    @Test
    public void calculates_emptyAddressBook_moneyPaid() {
        assertEquals("$0", statisticsCalculator.getAmountPaid());
    }

    @Test
    public void calculates_filledAddressBook_size() {
        List<Person> newPersons = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals(2, newCalculator.getSize());
    }

    @Test
    public void calculates_filledAddressBook_moneyOwed() {
        List<Person> newPersons = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("$80", newCalculator.getAmountOwed());
    }

    @Test
    public void calculates_filledAddressBook_moneyPaid() {
        List<Person> newPersons = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("$700", newCalculator.getAmountPaid());
    }

    @Test
    public void calculates_amountOwedOverflow() {
        // Edits Ava to have the maximum possible amount of money owed by a single person.
        Person editedAva = new PersonBuilder(AVA).withMoneyOwed(Integer.MAX_VALUE).build();
        Person editedBen = new PersonBuilder(BEN).withMoneyOwed(1).build();
        List<Person> newPersons = Arrays.asList(editedAva, editedBen);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("Owed amount too large to calculate.", newCalculator.getAmountOwed());

    }

    @Test
    public void calculates_amountPaidOverflow() {
        // Edits Ava to have the maximum possible amount of money paid by a single person.
        Person editedAva = new PersonBuilder(AVA).withMoneyPaid(Integer.MAX_VALUE).build();
        Person editedBen = new PersonBuilder(BEN).withMoneyPaid(1).build();
        List<Person> newPersons = Arrays.asList(editedAva, editedBen);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("Paid amount too large to calculate.", newCalculator.getAmountPaid());

    }

    /**
     * A stub ReadOnlyAddressBook that contains a persons list.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Person> schedule = FXCollections.observableArrayList();
        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            this.schedule.setAll(persons);
        }

        AddressBookStub() {
            this(Collections.emptyList());
        }
        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
        @Override
        public ObservableList<Person> getScheduleList() {
            return schedule;
        }
    }
}
