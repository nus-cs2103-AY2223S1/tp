package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
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
            + "Use the list command to restore all columns.\n";

    public static final String INVALID_SUBSET = "Please only specify columns that are present in the current table.\n"
            + "Use the list command to restore all columns.";

    public static final String LAST_COLUMN_REACHED = "(Please note that you have reached your last column. You must"
            + " have at least one column visible at all times)";

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

        if (!isValidList(fieldsToShow, model)) {
            throw new CommandException(INVALID_SUBSET);
        }

        List<String> hiddenFields = getHiddenFields();

        model.setHiddenFields(hiddenFields);
        model.setVisibleFields(fieldsToShow);

        if (isShowingLastColumn()) {
            return new CommandResult(MESSAGE_SUCCESS + LAST_COLUMN_REACHED);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
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

    /**
     * Returns the list of fields to be set to hidden by taking the complement of the updated {@code fieldsToShow}.
     * @return The updated list of hidden fields
     */
    private List<String> getHiddenFields() {
        List<String> allFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        allFields.removeAll(fieldsToShow);
        return allFields;
    }

    /**
     * Checks if the given fieldsToShow list is valid, i.e. if it is a subset of the current visible field list.
     * @param fieldsToShow The restricted list of fields to show
     * @return True if valid
     */
    private boolean isValidList(List<String> fieldsToShow, Model model) {
        List<String> visibleFields = model.getVisibleFields();
        return visibleFields.containsAll(fieldsToShow);
    }

    /**
     * Checks if there is currently only one column being shown.
     * @return True if there is only column being shown.
     */
    private boolean isShowingLastColumn() {
        return fieldsToShow.size() == 1;
    }
}
