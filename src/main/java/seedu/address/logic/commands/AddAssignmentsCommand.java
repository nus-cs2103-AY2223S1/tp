package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Student;

/**
 * Adds assignments to all existing students in the address book.
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
        Predicate<Person> predicate = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return true;
            }
        };
        model.updateFilteredPersonList(predicate);
        List<Person> lastShownList = model.getFilteredPersonList();
        int numOfPeople = lastShownList.size();
        String relativePath = "./data/";
        String txt = ".txt";
        String tag = "";
        String filePath = "";
        for (int i = 0; i < numOfPeople; i++) {

            Person personToEdit = lastShownList.get(i);
            if ((personToEdit.getPosition() instanceof Student)) {
                tag = personToEdit.getTags().toString();
                String[] handleTag = tag.split("-");
                String module = handleTag[0].replace("[", "");
                String filePath1 = relativePath + module + txt;
                if (filePath == "") {
                    filePath = filePath1;
                }
                Student currPosition = (Student) personToEdit.getPosition();
                Student editedPosition = new Student(currPosition.getAttendance(),
                        currPosition.getOverallGrade(),
                        currPosition.setAssignments(assignments), filePath1);


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

        File file = new File(filePath);

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(assignments);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new CommandResult(generateSuccessMessage());
    }

    /**
     * Generates a command execution success message based on whether the assignments are added to all existing
     * students in the address book
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
        if (!(other instanceof AddAssignmentsCommand)) {
            return false;
        }

        // state check
        AddAssignmentsCommand e = (AddAssignmentsCommand) other;
        return assignments.equals(e.assignments);
    }

}
