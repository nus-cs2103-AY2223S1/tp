package seedu.address.ui;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

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
            // TODO: Replace this function with Person.getMoneyOwed()
            moneyOwed += 2;
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
            // TODO: Replace this function with Person.getMoneyPaid()
            moneyPaid += 3;
        }
        return moneyPaid;
    }
}
