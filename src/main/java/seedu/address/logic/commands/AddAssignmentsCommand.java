package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Student;

/**
 * Changes the grade of an existing student in the address book.
 */
public class AddAssignmentsCommand extends Command {
    public static final String COMMAND_WORD = "assignments";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds assignments. Format: [name] w/[weightage]";
    public static final String MESSAGE_ADD_ASSIGNMENTS_SUCCESS = "Added Assignments to all students.";

    private final String assignments;

    /**
     * @param assignments of the student to be updated to
     */
    public AddAssignmentsCommand(String assignments) {
        requireAllNonNull(assignments);

        this.assignments = assignments;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        int numOfPeople = lastShownList.size();

        for (int i = 0; i < numOfPeople; i++) {

            Person personToEdit = lastShownList.get(i);
            if ((personToEdit.getPosition() instanceof Student)) {
                Student currPosition = (Student) personToEdit.getPosition();
                Student editedPosition = new Student(currPosition.getAttendance(),
                        currPosition.getOverallGrade(),
                        currPosition.setAssignments(assignments));
                Person editedPerson = new Person(personToEdit.getName(),
                        personToEdit.getPhone(),
                        personToEdit.getEmail(),
                        editedPosition,
                        personToEdit.getAddress(),
                        personToEdit.getRemark(),
                        personToEdit.getTags());

                model.setPerson(personToEdit, editedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            }

        }

        return new CommandResult(generateSuccessMessage());
    }

    /**
     * Generates a command execution success message based on whether the availability is edited for
     * {@code personToEdit}.
     */
    private String generateSuccessMessage() {
        return MESSAGE_ADD_ASSIGNMENTS_SUCCESS;
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailabilityCommand)) {
            return false;
        }

        // state check
        AddAssignmentsCommand e = (AddAssignmentsCommand) other;
        return assignments.equals(e.assignments);
    }

}
