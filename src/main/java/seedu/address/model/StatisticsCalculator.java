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
    public String getAmountOwed() {
        ObservableList<Person> personList = addressBook.getPersonList();
        int moneyOwed = 0;
        try {
            for (Person person : personList) {
                moneyOwed = Math.addExact(moneyOwed, person.getMoneyOwed().value);
            }
        } catch (ArithmeticException e) {
            return "Owed amount too large to calculate.";
        }
        return "$" + String.valueOf(moneyOwed);
    }

    /**
     * Sums up the total money paid by the people in AddressBook.
     */
    public String getAmountPaid() {
        ObservableList<Person> personList = addressBook.getPersonList();
        int moneyPaid = 0;
        try {
            for (Person person : personList) {
                moneyPaid = Math.addExact(moneyPaid, person.getMoneyPaid().value);
            }
        } catch (ArithmeticException e) {
            return "Paid amount too large to calculate.";
        }
        return "$" + String.valueOf(moneyPaid);
    }
}
