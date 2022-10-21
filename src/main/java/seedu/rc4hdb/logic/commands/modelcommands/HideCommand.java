package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

public class HideCommand implements ModelCommand {

    public static final String COMMAND_WORD = "hide";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides the table columns according to the fields\n"
            + "specified (case-insensitive) after the command word\n"
            + "Parameters: FIELD [MORE_FIELDS]...\n"
            + "Example: " + COMMAND_WORD + " gender room matric";

    public static final String MESSAGE_SUCCESS = "Showing only the specified columns";

    private final List<String> fieldsToShow;

    public HideCommand(List<String> fieldsToShow) {
        requireNonNull(fieldsToShow);
        this.fieldsToShow = fieldsToShow;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        model.setObservableFields(fieldsToShow);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof HideCommand) {
            HideCommand otherCommand = (HideCommand) other;
            return this.fieldsToShow.containsAll(otherCommand.fieldsToShow)
                    && otherCommand.fieldsToShow.containsAll(this.fieldsToShow);
        }
        return false;
    }

}
