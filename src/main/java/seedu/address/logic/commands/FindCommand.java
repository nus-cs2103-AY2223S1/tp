package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Finds and lists all students in student list whose details contain any of the argument keywords/number in a prefix.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students with keywords in a specific prefix. "
            + "Prefixes supported by this search include: n/, p/, np/, e/, a/, dt/, t/. "
            + "The matched students are displayed in a list on the left hand panel with index numbers.\n"
            + "The search keywords which are after the prefixes (NAME in the case of n/NAME) are case-insensitive.\n"
            + "Acceptable parameters: [n/NAME] [p/PHONE] [np/NOK_PHONE] [e/EMAIL] [a/ADDRESS] [dt/DATE] [t/TAG].\n"
            + "Exactly one type of prefix should be used in a single find command. "
            + "However, find by multiple tags is allowed.\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " t/python t/beginner";

    public static final String ONLY_ONE_PREFIX_MESSAGE = "You can only search with 1 prefix, "
            + "either n/, p/, np/, e/, a/, dt/ or t/";

    private final Predicate<Student> predicate;

    public FindCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
