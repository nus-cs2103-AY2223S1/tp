package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.workbook.model.Model;

/**
 * Lists all internships in the WorkBook to the user.
 */
public class ListCommand extends Command {

    /** Command word to execute the list command */
    public static final String COMMAND_WORD = "list";

    /** Message string displaying successful execution of the list command */
    public static final String MESSAGE_SUCCESS = "Listed all internships";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
