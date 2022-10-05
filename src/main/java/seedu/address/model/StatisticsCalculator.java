package seedu.address.model;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * A calculator that calculates the statistics of a particular AddressBook.
 */
public class StatisticsCalculator {
    private static final Logger logger = LogsCenter.getLogger(StatisticsCalculator.class);

    private final ReadOnlyAddressBook addressBook;

    /**
     * Creates a new StatisticsCalculator.
     *
     * @param addressBook AddressBook used to calculate the statistics.
     */
    public StatisticsCalculator(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Returns the number of people in AddressBook.
     */
    public int getSize() {
        return addressBook.getPersonList().size();
    }

    /**
     * Sums up the total money owed by the people in AddressBook.
     */
    public int getAmountOwed() {
        ObservableList<Person> personList = addressBook.getPersonList();
        int moneyOwed = 0;
        for (Person person : personList) {
            moneyOwed += person.getMoneyOwed().value;
        }
        return moneyOwed;
    }

    /**
     * Sums up the total money paid by the people in AddressBook.
     */
    public int getAmountPaid() {
        ObservableList<Person> personList = addressBook.getPersonList();
        int moneyPaid = 0;
        for (Person person : personList) {
            moneyPaid += person.getMoneyPaid().value;
        }
        return moneyPaid;
    }
}
