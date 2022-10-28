package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.fields.ResidentField;

/**
 * Represents a command that is able to tell the model which columns in the UI table to manipulate.
 */
public abstract class ColumnManipulatorCommand implements ModelCommand {

    public static final String RESTORE_FULL_VIEW = "Use the reset command to restore the full table view.";

    public static final String MESSAGE_SUCCESS_FORMAT = "Successfully %s the specified columns. ";

    public static final String MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW = MESSAGE_SUCCESS_FORMAT + RESTORE_FULL_VIEW;

    public static final String AT_LEAST_ONE_VISIBLE_COLUMN = "You must have at least one column visible at all times!";

    public static final String INVALID_FIELDS_ENTERED = "Invalid column fields entered.";

    public static final String INVALID_SUBSET = "Please enter columns to show or hide "
            + "that are currently in the table view.\n"
            + "To display columns outside of the current view, use the reset command.\n";

    public static final List<String> ALL_FIELDS = new ArrayList<>(ResidentField.LOWERCASE_FIELDS);
    protected final List<String> fieldsToShow;
    protected final List<String> fieldsToHide;

    /**
     * Constructor for a ColumnManipulatorCommand instance.
     * @param fieldsToShow The fields to set to visible in the UI table
     * @param fieldsToHide The fields to set to not visible in the UI table
     */
    public ColumnManipulatorCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        requireAllNonNull(fieldsToShow, fieldsToHide);

        List<String> lowerCaseFieldsToShow = generateLowerCaseListFrom(fieldsToShow);
        List<String> lowerCaseFieldsToHide = generateLowerCaseListFrom(fieldsToHide);

        this.fieldsToShow = lowerCaseFieldsToShow;
        this.fieldsToHide = lowerCaseFieldsToHide;
    }

    /**
     * Generates the complement of the input list based on the global list of possible fields.
     * @param inputList The base list whose complement is to be generated
     * @return A complement list containing the fields that are not in the input list.
     */
    public static List<String> generateComplementListFrom(List<String> inputList) {
        requireNonNull(inputList);
        List<String> complementList = new ArrayList<>(ALL_FIELDS);
        complementList.removeAll(inputList);
        return complementList;
    }

    /**
     * Validates the field list against the list of possible fields.
     * @param fieldList The field list to be validated
     * @throws CommandException if there are invalid fields
     */
    public static void requireAllFieldsValid(List<String> fieldList) throws CommandException {
        requireNonNull(fieldList);
        for (String field : fieldList) {
            if (!ALL_FIELDS.contains(field.toLowerCase())) {
                throw new CommandException(INVALID_FIELDS_ENTERED);
            }
        }
    }

    private static List<String> generateLowerCaseListFrom(List<String> inputList) {
        requireNonNull(inputList);
        return inputList.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ColumnManipulatorCommand) {
            ColumnManipulatorCommand otherCommand = (ColumnManipulatorCommand) other;

            // Bi-directional subset relationship implies equality
            boolean hasEqualFieldsToShow = this.fieldsToShow.containsAll(otherCommand.fieldsToShow)
                    && otherCommand.fieldsToShow.containsAll(this.fieldsToShow);
            boolean hasEqualFieldsToHide = this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);

            return hasEqualFieldsToShow && hasEqualFieldsToHide;
        }
        return false;
    }

    private static List<String> getAlreadyVisibleFields(Model model) {
        requireNonNull(model);
        return model.getVisibleFields();
    }

    private static List<String> getAlreadyHiddenFields(Model model) {
        requireNonNull(model);
        return model.getHiddenFields();
    }

    private static boolean isValidSubsetOfAlreadyVisibleFields(Model model, List<String> inputList) {
        requireAllNonNull(model, inputList);
        List<String> alreadyVisibleFields = getAlreadyVisibleFields(model);
        return alreadyVisibleFields.containsAll(inputList);
    }

    /**
     * Enforces the need for at least one visible column in the UI table.
     * @param fieldsToShow The list of fields to be set to visible in the UI table
     * @throws CommandException if there are no fields in the field list
     */
    public static void requireAtLeastOneVisibleColumn(List<String> fieldsToShow) throws CommandException {
        requireNonNull(fieldsToShow);
        if (fieldsToShow.isEmpty()) {
            throw new CommandException(AT_LEAST_ONE_VISIBLE_COLUMN);
        }
    }

    /**
     * Enforces the need for subsequent showOnly and hideOnly commands to have
     * a fieldsToShow and fieldsToHide list that is a subset of the fields
     * already visible in the UI table.
     * @param model The model that contains the UI table
     * @param inputList The fieldsToShow or fieldsToHide list depending on the command
     *                  invoking this method
     * @throws CommandException if the field list is not a valid subset
     */
    public static void requireValidSubsetOfAlreadyVisibleFields(Model model,
                                                                List<String> inputList) throws CommandException {
        requireAllNonNull(model, inputList);
        if (!isValidSubsetOfAlreadyVisibleFields(model, inputList)) {
            throw new CommandException(INVALID_SUBSET);
        }
    }

    /**
     * Returns the union of the fieldsToHide list and the existing list of already
     * hidden fields in the model.
     * @param model The model that contains the UI table
     * @param inputList The fields to take union with
     * @return A list of fields representing the larger list of fields to be hidden
     */
    public static List<String> getUnionOfFieldsToHideAndAlreadyHiddenFields(Model model, List<String> inputList) {
        requireAllNonNull(model, inputList);
        List<String> unionList = new ArrayList<>(getAlreadyHiddenFields(model));
        unionList.addAll(inputList);
        return unionList;
    }
}
