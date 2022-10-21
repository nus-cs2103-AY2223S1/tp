package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

public class ShowCommand implements ModelCommand {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows only the columns specified "
            + "(case-insensitive) after the command word\n"
            + "Parameters: FIELD [MORE_FIELDS]...\n"
            + "Example: " + COMMAND_WORD + " name phone email";

    public static final String MESSAGE_SUCCESS = "Showing only the specified columns. "
            + "Use the list command to restore all columns.";

    public final List<String> fieldsToHide;

    public ShowCommand(List<String> fieldsToHide) {
        requireNonNull(fieldsToHide);
        this.fieldsToHide = fieldsToHide;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        model.setObservableFields(fieldsToHide);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ShowCommand) {
            ShowCommand otherCommand = (ShowCommand) other;
            return this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);
        }
        return false;
    }
}
