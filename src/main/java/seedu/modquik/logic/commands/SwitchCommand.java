package seedu.modquik.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_FIELD;

import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;

/**
 * Switches between model list in ModQuik.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show data in ModQuik\n"
            + "Parameters: "
            + PREFIX_FIELD + "FIELD (student, tutorial, consultation, grade)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FIELD + "student";

    public static final String MESSAGE_SUCCESS_STUDENT = "Showing student list";
    public static final String MESSAGE_SUCCESS_CONSULTATION = "Showing consultation list";
    public static final String MESSAGE_SUCCESS_TUTORIAL = "Showing tutorial list";
    public static final String MESSAGE_SUCCESS_GRADE = "Showing grade";

    private final ModelType modelType;

    public SwitchCommand(ModelType modelType) {
        this.modelType = modelType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (modelType) {
        case STUDENT:
            return new CommandResult(MESSAGE_SUCCESS_STUDENT, ModelType.STUDENT);

        case TUTORIAL:
            return new CommandResult(MESSAGE_SUCCESS_TUTORIAL, ModelType.TUTORIAL);

        case CONSULTATION:
            return new CommandResult(MESSAGE_SUCCESS_CONSULTATION, ModelType.CONSULTATION);

        case GRADE_CHART:
            return new CommandResult(MESSAGE_SUCCESS_GRADE, ModelType.GRADE_CHART);

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchCommand // instanceof handles nulls
                && modelType.equals(((SwitchCommand) other).modelType));
    }
}
