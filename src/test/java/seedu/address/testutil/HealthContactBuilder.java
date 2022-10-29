package seedu.address.testutil;

import seedu.address.model.HealthContact;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building HealthContact objects.
 * Example usage: <br>
 *     {@code HealthContact ab = new HealthContactBuilder().withPatient("John", "Doe").build();}
 */
public class HealthContactBuilder {

    private HealthContact healthContact;

    public HealthContactBuilder() {
        healthContact = new HealthContact();
    }

    public HealthContactBuilder(HealthContact healthContact) {
        this.healthContact = healthContact;
    }

    /**
     * Adds a new {@code Patient} to the {@code HealthContact} that we are building.
     */
    public HealthContactBuilder withPatient(Patient patient) {
        healthContact.addPatient(patient);
        return this;
    }

    /**
     * Adds a new {@code Appointment} to the {@code HealthContact} that we are building.
     */
    public HealthContactBuilder withAppointment(Appointment appointment) {
        healthContact.addAppointment(appointment);
        return this;
    }

    /**
     * Adds a new {@code Bill} to the {@code HealthContact} that we are building.
     */
    public HealthContactBuilder withBill(Bill bill) {
        healthContact.addBill(bill);
        return this;
    }

    public HealthContact build() {
        return healthContact;
    }
}
