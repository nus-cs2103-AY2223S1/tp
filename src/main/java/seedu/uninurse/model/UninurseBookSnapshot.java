package seedu.uninurse.model;

import java.util.List;

import seedu.uninurse.model.person.Patient;

/**
 * A snapshot of a UninurseBook after a command.
 */
public class UninurseBookSnapshot {
    private final ReadOnlyUninurseBook uninurseBook;
    private final PatientListTracker patientListTracker;

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied}
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.patientListTracker = new PatientListTracker();
    }

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied}
     * and the {@code patientListTracker}.
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied, PatientListTracker patientListTracker) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.patientListTracker = patientListTracker;
    }

    /**
     * Returns the person list stored in uninurseBook.
     */
    public List<Patient> getPersonList() {
        return uninurseBook.getPersonList();
    }

    public PatientListTracker getPatientListTracker() {
        return patientListTracker;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBookSnapshot // instanceof handles nulls
                && uninurseBook.equals(((UninurseBookSnapshot) other).uninurseBook))
                && patientListTracker.equals(((UninurseBookSnapshot) other).patientListTracker);
    }
}
