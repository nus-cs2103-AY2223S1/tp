package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPatient("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Patient} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPatient(Patient patient) {
        addressBook.addPatient(patient);
        return this;
    }

    /**
     * Adds a new {@code Appointment} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
        return this;
    }

    /**
     * Adds a new {@code Bill} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withBill(Bill bill) {
        addressBook.addBill(bill);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
