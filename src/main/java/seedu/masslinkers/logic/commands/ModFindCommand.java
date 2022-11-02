package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.ModContainsKeywordsPredicate;

//@author chm252
/**
 * Finds and lists those in mass linkers who have taken or are taking module(s) that contain(s)
 * the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class ModFindCommand extends ModCommand {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD_TAKEN = "taken";
    public static final String COMMAND_WORD_TAKING = "taking";
    public static final String MESSAGE_SUCCESS = "Successfully found those who are taking or have taken this mod!";
    public static final String MESSAGE_USAGE = "Find batchmates taking or have taken specified modules in this manner: "
            +
            "\nmod find MOD [MORE_MODS]...";
    private final ModContainsKeywordsPredicate predicate;

    /**
     * Constructs the ModFindCommand.
     * @param predicate Boolean condition for whether modules match all the given user inputs and the given condition,
     *     if any.
     */
    public ModFindCommand(ModContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        int numOfStudents = model.getFilteredStudentList().size();
        if (numOfStudents != 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()),
                    false, false, true, false);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ONE_STUDENT_LISTED_OVERVIEW,
                            numOfStudents), false, false, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.masslinkers.logic.commands.ModFindCommand // instanceof handles nulls
                && predicate.equals(((seedu.masslinkers.logic.commands.ModFindCommand) other)
                .predicate)); // state check
    }
}
