package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Updates the table view to show only the columns specified by the user.
 */
public class ShowCommand implements ModelCommand {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows only the columns specified "
            + "(case-insensitive) after the command word\n"
            + "Parameters: FIELD [MORE_FIELDS]...\n"
            + "Example: " + COMMAND_WORD + " name phone email";

    public static final String MESSAGE_SUCCESS = "Showing only the specified columns.\n"
            + "Use the list command to restore all columns.";

    public static final String INVALID_SUBSET = "Please enter columns to show based on the current table.\n"
            + "(Note: You must exclude at least one of the current columns from your specified columns to show)";

    /**
     * The list of fields to pass to the TableView for hiding.
     */
    public final List<String> fieldsToShow;

    /**
     * Constructor for a ShowCommand instance.
     * @param fieldsToShow The list of fields to hide in the table
     */
    public ShowCommand(List<String> fieldsToShow) {
        requireNonNull(fieldsToShow);
        this.fieldsToShow = fieldsToShow;
    }

    /**
     * Implements the execute method in the ModelCommand interface.
     * The model field list is updated with the internal field list.
     * @param model {@code Model} which the command should operate on.
     * @return A CommandResult if the execution was successful.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!isValidSubsetOfVisibleFields(model, fieldsToShow)) {
            throw new CommandException(INVALID_SUBSET);
        }

        List<String> complement = getComplementOfVisibleFields(fieldsToShow);

        model.setObservableFields(complement);
        model.setHiddenFields(complement);
        model.setVisibleFields(fieldsToShow);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Overrides the equals method in the Object class.
     * Checks if the field lists have the same content.
     * @param other The object to check for equality
     * @return True if the object is equals to this instance
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ShowCommand) {
            ShowCommand otherCommand = (ShowCommand) other;
            return this.fieldsToShow.containsAll(otherCommand.fieldsToShow)
                    && otherCommand.fieldsToShow.containsAll(this.fieldsToShow);
        }
        return false;
    }

    private List<String> getComplementOfVisibleFields(List<String> fieldsToShow) {
        List<String> complement = ResidentFields.FIELDS.stream().map(String::toLowerCase).collect(Collectors.toList());
        complement.removeAll(fieldsToShow);
        return complement;
    }

    private boolean isValidSubsetOfVisibleFields(Model model, List<String> fieldsToShow) {
        List<String> lowerCaseVisibleFieldList = model.getVisibleFields()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return lowerCaseVisibleFieldList.containsAll(fieldsToShow)
                && !fieldsToShow.containsAll(lowerCaseVisibleFieldList);
    }
}
