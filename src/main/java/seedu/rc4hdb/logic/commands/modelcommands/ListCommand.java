package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand implements ModelCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all residents.";

    public static final String SHOW_ONLY_SPECIFIED = "Listed all residents. Only specified fields are shown.";

    private final List<String> fieldsToShow;
    private final List<String> fieldsToHide;

    /**
     * Constructor for a ListCommand instance.
     */
    public ListCommand() {
        this.fieldsToShow = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        this.fieldsToHide = new ArrayList<>();
    }

    /**
     * Constructor for a ListCommand instance.
     * @param fieldsToHide The fields to be shown when listing the data
     * @param fieldsToHide The fields to be hidden when listing the data
     */
    public ListCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        requireAllNonNull(fieldsToShow, fieldsToHide);
        this.fieldsToShow = fieldsToShow;
        this.fieldsToHide = fieldsToHide;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);

        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

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
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide)
                    && this.fieldsToShow.containsAll(otherCommand.fieldsToShow)
                    && otherCommand.fieldsToShow.containsAll(this.fieldsToShow);
        }
        return false;
    }
}
