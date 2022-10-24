package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_SUCCESS = "Hidden some columns from the table view. "
            + "Use the list command to restore all columns.";

    public static final String INVALID_SUBSET = "Please enter columns to hide based on the current table.\n"
            + "(Note: You must exclude at least one of the current columns from your specified columns to hide)";

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

        if (!isValidSubsetOfVisibleFields(model, fieldsToHide)) {
            throw new CommandException(INVALID_SUBSET);
        }

        fieldsToHide.addAll(model.getHiddenFields());

        List<String> visibleFields = getComplementOfHiddenFieldsInModel(model);
        visibleFields.removeAll(fieldsToHide);

        model.setObservableFields(fieldsToHide);
        model.setVisibleFields(visibleFields);
        model.setHiddenFields(fieldsToHide);

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
        if (other instanceof HideCommand) {
            HideCommand otherCommand = (HideCommand) other;
            return this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);
        }
        return false;
    }

    private List<String> getComplementOfHiddenFieldsInModel(Model model) {
        List<String> allFields = ResidentFields.FIELDS.stream().map(String::toLowerCase).collect(Collectors.toList());
        allFields.removeAll(model.getHiddenFields());
        return allFields;
    }

    private boolean isValidSubsetOfVisibleFields(Model model, List<String> fieldsToHide) {
        List<String> visibleFields = getComplementOfHiddenFieldsInModel(model);
        return visibleFields.containsAll(fieldsToHide) && !fieldsToHide.containsAll(visibleFields);
    }
}
