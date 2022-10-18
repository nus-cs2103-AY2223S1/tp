package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.condition.Condition;

/**
 * Add a medical condition to an existing patient in the patient list.
 */
public class AddConditionCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a medical condition to the patient identified "
            + "by the index number used in the last patient listing.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + PREFIX_CONDITION + "CONDITION\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + " 2 " + PREFIX_CONDITION + "Hypertension";

    public static final String MESSAGE_ADD_CONDITION_SUCCESS = "New condition added to %1$s: %2$s";

    private final Index index;
    private final Condition condition;

    /**
     * Creates an AddConditionCommand to add a {@code Condition} to the specified person.
     * @param index The index of the person in the filtered person list to add the condition.
     * @param condition The condition of the person to be added to.
     */
    public AddConditionCommand(Index index, Condition condition) {
        requireAllNonNull(index, condition);

        this.index = index;
        this.condition = condition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        return null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddConditionCommand)) {
            return false;
        }

        // state check
        AddConditionCommand e = (AddConditionCommand) other;
        return index.equals(e.index) && condition.equals((e.condition));
    }
}
