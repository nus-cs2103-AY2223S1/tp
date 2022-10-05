package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

public class StatisticsCalculatorTest {

    private final AddressBookStub addressBookStub = new AddressBookStub();
    private final StatisticsCalculator statisticsCalculator = new StatisticsCalculator(addressBookStub);

    @Test
    public void calculates_emptyAddressBook_size() {
        assertEquals(0, statisticsCalculator.getSize());
    }

    @Test
    public void calculates_emptyAddressBook_moneyOwed() {
        assertEquals(0, statisticsCalculator.getAmountOwed());
    }

    @Test
    public void calculates_emptyAddressBook_moneyPaid() {
        assertEquals(0, statisticsCalculator.getAmountPaid());
    }

    @Test
    public void calculates_filledAddressBook_size() {
        List<Person> newPersons = Arrays.asList(ALICE, BOB);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals(2, newCalculator.getSize());
    }

    @Test
    public void calculates_filledAddressBook_moneyOwed() {
        List<Person> newPersons = Arrays.asList(ALICE, BOB);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        //TODO: Update the value of expected once the moneyOwed attributes are added in the person.
        assertEquals(4, newCalculator.getAmountOwed());
    }

    @Test
    public void calculates_filledAddressBook_moneyPaid() {
        List<Person> newPersons = Arrays.asList(ALICE, BOB);
        StatisticsCalculatorTest.AddressBookStub newData = new StatisticsCalculatorTest.AddressBookStub(newPersons);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        //TODO: Update the value of expected once the moneyPaid attributes are added in the person.
        assertEquals(6, newCalculator.getAmountPaid());
    }

    /**
     * A stub ReadOnlyAddressBook that contains a persons list.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        AddressBookStub() {
            this(Collections.emptyList());
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }
}
