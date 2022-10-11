package seedu.rc4hdb.testutil;

import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;

/**
 * A utility class to help with building ResidentBook objects.
 * Example usage: <br>
 *     {@code ResidentBook ab = new ResidentBookBuilder().withResident("John", "Doe").build();}
 */
public class ResidentBookBuilder {

    private ResidentBook residentBook;

    public ResidentBookBuilder() {
        residentBook = new ResidentBook();
    }

    public ResidentBookBuilder(ResidentBook residentBook) {
        this.residentBook = residentBook;
    }

    /**
     * Adds a new {@code Resident} to the {@code AddressBook} that we are building.
     */
    public ResidentBookBuilder withResident(Resident resident) {
        residentBook.addResident(resident);
        return this;
    }

    public ResidentBook build() {
        return residentBook;
    }
}
