package seedu.uninurse.model;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.PatientPair;

/**
 * A snapshot of a UninurseBook after a command.
 */
public class UninurseBookSnapshot {
    private final ReadOnlyUninurseBook uninurseBook;
    private final PatientPair patientPair;

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied}
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.patientPair = new PatientPair(Optional.empty(), Optional.empty());
    }

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied} and the affected {@code patientPair}.
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied, PatientPair patientPair) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.patientPair = patientPair;
    }

    /**
     * Returns the person list stored in uninurseBook.
     */
    public List<Patient> getPersonList() {
        return uninurseBook.getPersonList();
    }

    public PatientPair getPatientPair() {
        return patientPair;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBookSnapshot // instanceof handles nulls
                && uninurseBook.equals(((UninurseBookSnapshot) other).uninurseBook));
    }
}
