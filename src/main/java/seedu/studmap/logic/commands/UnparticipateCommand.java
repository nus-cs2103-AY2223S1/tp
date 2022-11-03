package seedu.studmap.logic.commands;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PARTICIPATION;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Participation;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Unmarks the specified participation record from the student identified using its displayed index.
 */
public class UnparticipateCommand extends EditStudentCommand<UnparticipateCommand.UnparticipateCommandStudentEditor> {

    public static final String COMMAND_WORD = "unparticipate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the participation component for student identified by the index number used in the displayed "
            + "student list.\n"
            + "Removes participation record for the participation component specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_PARTICIPATION + "COMPONENT\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_PARTICIPATION + "P01\n"
            + "Example: " + COMMAND_WORD + " all " + PREFIX_PARTICIPATION + "P02";

    public static final String MESSAGE_UNMARK_SINGLE_PARTICIPATION_SUCCESS = "Removed Participation component %1$s "
            + "from Student: %2$s";

    public static final String MESSAGE_UNMARK_MULTI_PARTICIPATION_SUCCESS = "Removed Participation component %1$s from "
            + "%2$s students";

    public static final String MESSAGE_UNMARK_PARTICIPATION_NOTFOUND = "Participation component %1$s not found in "
            + "Student: %2$s";

    public static final String MESSAGE_NO_EDIT = "Participation component must be provided.";

    public UnparticipateCommand(IndexListGenerator indexListGenerator,
                                UnparticipateCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_UNMARK_SINGLE_PARTICIPATION_SUCCESS,
                studentEditor.getParticipation().participationComponent,
                editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_UNMARK_MULTI_PARTICIPATION_SUCCESS,
                studentEditor.getParticipation().participationComponent,
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Participation for a given Student.
     */
    public static class UnparticipateCommandStudentEditor implements StudentEditor {

        private final Participation participation;

        /**
         * Constructor using Participation.
         *
         * @param participation Participation to edit the student with.
         */
        public UnparticipateCommandStudentEditor(Participation participation) {
            this.participation = participation;
        }

        public Participation getParticipation() {
            return participation;
        }

        @Override
        public Student editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();

            Set<Participation> newParticipation = new HashSet<>(studentToEdit.getParticipations());
            newParticipation.remove(participation);
            studentData.setParticipations(newParticipation);

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return participation != null;
        }

        @Override
        public boolean equals(Object other) {

            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UnparticipateCommand.UnparticipateCommandStudentEditor)) {
                return false;
            }

            // state check
            UnparticipateCommand.UnparticipateCommandStudentEditor e =
                    (UnparticipateCommand.UnparticipateCommandStudentEditor) other;

            if (getParticipation() == null && e.getParticipation() != null) {
                return false;
            } else if (getParticipation() == null && e.getParticipation() == null) {
                return true;
            }

            return getParticipation().equals(e.getParticipation());
        }
    }
}
