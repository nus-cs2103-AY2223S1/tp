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
 * Adds a tutorial group to the address book.
 */
public class TutorialGroupFilterCommand extends Command {
    public static final String COMMAND_WORD = "tutorialFilter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters students based on tutorial groups. "
        + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "Students filtered in this tutorial group: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book";
    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "Tutorial group not found";
    private final TutorialGroup toFilter;

    /**
     * Creates an TaskAddCommand to add the specified {@code Person}
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

        //model.addTutorialGroup(toFilter);
        List<Student> students = model.getFilteredStudentList();
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
