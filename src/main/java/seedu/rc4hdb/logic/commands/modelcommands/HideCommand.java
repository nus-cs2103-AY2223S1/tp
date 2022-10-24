package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Updates the table view by hiding the columns specified by the user.
 */
public class HideCommand implements ModelCommand {

    public static final String COMMAND_WORD = "hide";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides the table columns according to the fields\n"
            + "specified (case-insensitive) after the command word\n"
            + "Parameters: FIELD [MORE_FIELDS]...\n"
            + "Example: " + COMMAND_WORD + " room gender matric";

    public static final String MESSAGE_SUCCESS = "Hidden some columns from the table view.\n"
            + "Use the list command to restore all columns.\n";

    public static final String INVALID_SUBSET = "Please only specify columns that are present in the current table.\n"
            + "Use the list command to restore all columns.";

    public static final String LAST_COLUMN_REACHED = "(Please note that you have reached your last column. You must"
            + " have at least one column visible at all times)";

    public static final String CANNOT_HIDE_ALL_COLUMNS = "(Please note that you must have at least"
            + " one column visible at all times)";

    /**
     * The list of fields to pass to the TableView for hiding.
     */
    private final List<String> fieldsToHide;

    /**
     * Constructor for a HideCommand instance.
     * @param fieldsToHide The list of fields to hide in the table
     */
    public HideCommand(List<String> fieldsToHide) {
        requireNonNull(fieldsToHide);
        this.fieldsToHide = fieldsToHide;
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

        if (!isValidList(fieldsToHide, model)) {
            throw new CommandException(INVALID_SUBSET);
        }

        // Take the union of the newly specified fieldsToHide and the existing fieldsToHide in Model
        fieldsToHide.addAll(model.getHiddenFields());
        if (isHidingAllColumns()) {
            throw new CommandException(CANNOT_HIDE_ALL_COLUMNS);
        }

        // Take the complement of the updated fieldsToHide
        List<String> visibleFields = getVisibleFields();

        model.setVisibleFields(visibleFields);
        model.setHiddenFields(fieldsToHide);

        if (isShowingLastColumn(visibleFields)) {
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
        if (other instanceof HideCommand) {
            HideCommand otherCommand = (HideCommand) other;
            return this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);
        }
        return false;
    }

    /**
     * Returns the list of fields to be set to visible by taking the complement of the updated {@code fieldsToHide}.
     * @return The updated list of visible fields
     */
    private List<String> getVisibleFields() {
        List<String> allFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        allFields.removeAll(fieldsToHide);
        return allFields;
    }

    /**
     * Checks if the given fieldsToHide list is valid, i.e. if it is a subset of the current visible field list.
     * @param fieldsToHide The list of additional fields to hide
     * @return True if valid
     */
    private boolean isValidList(List<String> fieldsToHide, Model model) {
        List<String> visibleFields = model.getVisibleFields();
        return visibleFields.containsAll(fieldsToHide);
    }

    /**
     * Checks if there is currently only one column being shown.
     * @return True if there is only column being shown.
     */
    private boolean isShowingLastColumn(List<String> visibleFields) {
        return visibleFields.size() == 1;
    }

    private boolean isHidingAllColumns() {
        return fieldsToHide.size() == ResidentFields.LOWERCASE_FIELDS.size();
    }
}
