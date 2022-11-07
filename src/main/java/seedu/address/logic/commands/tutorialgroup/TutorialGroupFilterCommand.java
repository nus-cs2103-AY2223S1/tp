package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;



/**
 * Filters a tutorial group from the address book.
 */
public class TutorialGroupFilterCommand extends Command {
    public static final String COMMAND_WORD = "tutorial filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters students based on tutorial groups. "
        + "Parameters: tutorial group name";

    public static final String MESSAGE_SUCCESS = "All students from the tutorial group %1$s:";
    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "Tutorial group not found";
    private final TutorialGroup toFilter;

    /**
     * Creates an TutorialGroupFilterCommand to add the specified {@code TutorialGroup}
     */
    public TutorialGroupFilterCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toFilter = tutorialGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTutorialGroup(toFilter)) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }

        List<Student> students = model.getFilteredStudentList();
        model.updateFilteredStudentListByTg(toFilter);
        String result = "";
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.belongsTo(toFilter)) {
                result += student + "\n";
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toFilter) + "\n" + result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                    || (other instanceof TutorialGroupFilterCommand // instanceof handles nulls
                    && toFilter.equals(((TutorialGroupFilterCommand) other).toFilter));
    }
}
