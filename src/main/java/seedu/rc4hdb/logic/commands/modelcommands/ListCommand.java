package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand implements ModelCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String SHOW_ONLY_SPECIFIED = "Listed all persons. Only specified fields are shown.";

    private final List<String> fieldsToHide;

    /**
     * Constructor for a ListCommand instance.
     */
    public ListCommand() {
        // Set the list of fields hide to an empty list
        this.fieldsToHide = new ArrayList<>();
    }

    /**
     * Constructor for a ListCommand instance.
     * @param fieldsToHide The fields to be hidden when listing the data
     */
    public ListCommand(List<String> fieldsToHide) {
        requireNonNull(fieldsToHide);
        this.fieldsToHide = fieldsToHide;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        model.setObservableFields(fieldsToHide);

        // Determine which ListCommand constructor was invoked
        if (fieldsToHide.isEmpty()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(SHOW_ONLY_SPECIFIED);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ListCommand) {
            ListCommand otherCommand = (ListCommand) other;
            return this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);
        }
        return false;
    }
}
