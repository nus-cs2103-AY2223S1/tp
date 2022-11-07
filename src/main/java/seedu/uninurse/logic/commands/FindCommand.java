package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.person.PatientMatchPredicate;

/**
 * Finds and lists all persons in uninurse book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose details contain any of "
            + "the specified keywords (case-insensitive).\n"
            + "Format: [KEYWORD]... "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_EMAIL + "EMAIL]... "
            + "[" + PREFIX_ADDRESS + "ADDRESS]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION] ... "
            + "[" + PREFIX_CONDITION + "CONDITION]... "
            + "[" + PREFIX_MEDICATION + "MEDICATION_TYPE]... "
            + "[" + PREFIX_REMARK + "REMARK]...\n"
            + "Example: " + COMMAND_WORD + " insulin "
            + PREFIX_NAME + "Jo " + PREFIX_NAME + "Betty "
            + PREFIX_EMAIL + "@example.com";
    public static final String MESSAGE_SUCCESS = "%1$d persons listed!";
    public static final String MESSAGE_FAILURE = "Keywords cannot be empty.";
    public static final CommandType COMMAND_TYPE = CommandType.FIND;

    private final PatientMatchPredicate predicate;

    public FindCommand(PatientMatchPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()),
                COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
