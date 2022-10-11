package seedu.address.testutil;

import seedu.address.model.PennyWise;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.entry.Income;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PennyWise ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PennyWise pennyWise;

    public AddressBookBuilder() {
        pennyWise = new PennyWise();
    }

    public AddressBookBuilder(PennyWise pennyWise) {
        this.pennyWise = pennyWise;
    }

    // public AddressBookBuilder withPerson(Person person) {
    //     addressBook.addPerson(person);
    //     return this;
    // }
    /**
     * Adds a new {@code Person} to the {@code PennyWise} that we are building.
     */
    //    public AddressBookBuilder withPerson(Person person) {
    //        pennyWise.addPerson(person);
    //        return this;
    //    }

    public AddressBookBuilder withExpenditure(Expenditure expenditure) {
        pennyWise.addExpenditure(expenditure);
        return this;
    }

    /**
     * Adds a new {@code Income} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withIncome(Income income) {
        pennyWise.addIncome(income);
        return this;
    }

    public PennyWise build() {
        return pennyWise;
    }
}
