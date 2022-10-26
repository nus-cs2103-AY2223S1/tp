package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

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
