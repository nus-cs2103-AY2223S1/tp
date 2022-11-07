package seedu.uninurse.model;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.CommandType;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * A snapshot of a UninurseBook after a command.
 */
public class UninurseBookSnapshot {
    private final ReadOnlyUninurseBook uninurseBook;
    private final CommandResult commandResult;

    /**
     * Creates an UninurseBookSnapshot using the Persons in the toBeCopied.
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.commandResult = new CommandResult("", CommandType.EMPTY);
    }

    /**
     * Creates an UninurseBookSnapshot using the Persons in the toBeCopied
     * and the commandResult.
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied, CommandResult commandResult) {
        this.uninurseBook = new UninurseBook(toBeCopied);
        this.commandResult = commandResult;
    }

    /**
     * Returns the person list stored in uninurseBook.
     */
    public List<Person> getPersonList() {
        return uninurseBook.getPersonList();
    }

    /**
     * Returns the patient list stored in uninurseBook.
     */
    public List<Patient> getPatientList() {
        return uninurseBook.getPatientList();
    }

    public Optional<PersonListTracker> getPersonListTracker() {
        return commandResult.getPersonListTracker();
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBookSnapshot // instanceof handles nulls
                && uninurseBook.equals(((UninurseBookSnapshot) other).uninurseBook))
                && commandResult.equals(((UninurseBookSnapshot) other).commandResult);
    }
}
