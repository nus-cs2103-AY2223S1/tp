package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.student.StudentExpelCommand;
import seedu.address.logic.commands.student.StudentExpelCommand.EditStudentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.tag.Tag;


/**
 * Adds a tutorial group to the address book.
 */
public class TutorialGroupDeleteCommand extends Command {
    public static final String COMMAND_WORD = "tutorial delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tutorial group from the address book. "
        + "Parameters: index of the tutorial group";

    public static final String MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS = "This tutorial group deleted: %1$s";

    private Index targetIndex;

    /**
     * Creates an TaskAddCommand to add the specified {@code Person}
     */
    public TutorialGroupDeleteCommand(Index index) {
        targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX);
        }

        TutorialGroup toDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Student> studentsInGroup = findStudentInGroup(model.getFilteredStudentList(), toDelete);
        String res = expelStudentsFromGroup(model, studentsInGroup);
        model.deleteTutorialGroup(toDelete);
        String message = String.format(MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS, toDelete)
                + "\n"
                + "Students being expelled from this group \n"
                + res;

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                    || (other instanceof TutorialGroupDeleteCommand // instanceof handles nulls
                    && targetIndex.equals(((TutorialGroupDeleteCommand) other).targetIndex));
    }

    private List<Student> findStudentInGroup(List<Student> students, TutorialGroup tutorialGroup) {
        List<Student> selectedStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.belongsTo(tutorialGroup)) {
                selectedStudents.add(student);
            }
        }
        return selectedStudents;
    }

    private String expelStudentsFromGroup(Model model, List<Student> students) {
        String res = "";
        for (Student student: students) {
            StudentExpelCommand.EditStudentDescriptor editStudentDescriptor =
                    new StudentExpelCommand.EditStudentDescriptor();
            editStudentDescriptor.resetTutorialGroup(null);
            Student editedStudent = createEditedStudent(student, editStudentDescriptor);
            model.setStudent(student, editedStudent);
            res += editedStudent + "\n";
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return res;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = studentToEdit.getName();
        Phone updatedPhone = studentToEdit.getPhone();
        Email updatedEmail = studentToEdit.getEmail();
        Set<Tag> updatedTags = studentToEdit.getTags();
        TutorialGroup updatedTutorialGroup = null;
        return new Student(updatedName, updatedPhone, updatedEmail, updatedTags, updatedTutorialGroup);
    }
}
