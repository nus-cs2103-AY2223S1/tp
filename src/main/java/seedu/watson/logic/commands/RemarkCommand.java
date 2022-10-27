package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.watson.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.watson.commons.core.Messages;
import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.Remark;

/**
 * Adds a remark to a student.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a remark for a student "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "He participates often in class";

    public static final String MESSAGE_SUCCESS = "Added remark for %s: %s";

    private final Index index;
    private final Remark remark;

    /**
     * Creates a remark command.
     * @param index of the person in the filtered person list to edit
     * @param remark remark to add
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    /**
     * Adds a remark to a student.
     * @param personToAddRemark student to add remark
     * @param remark remark to add
     */
    private static Person addRemark(Person personToAddRemark, Remark remark) {
        assert personToAddRemark != null;
        Set<Remark> remarksList = personToAddRemark.getRemarks();
        if (remarksList == null) {
            remarksList = new HashSet<>();
        }
        remarksList.add(remark);
        personToAddRemark.setRemarks(remarksList);
        return personToAddRemark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddRemark = lastShownList.get(index.getZeroBased());
        Person personAddedRemark = addRemark(personToAddRemark, remark);

        model.setPerson(personToAddRemark, personAddedRemark);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personAddedRemark.getName(), remark));
    }
}
