package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
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
 * Records class participation for a student identified using its displayed index from the student map.
 */
public class ParticipateCommand extends EditStudentCommand<ParticipateCommand.ParticipateCommandStudentEditor> {

    public static final String COMMAND_WORD = "participate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Record the participation for a student identified by the index number used in the displayed"
            + " student list."
            + "\nRecord participation for the participation component specified in the parameter."
            + "\nParameters: INDEX (must be positive integer or use \"all\" to record everyone currently displayed)"
            + " OPTION (must be yes/no) "
            + PREFIX_PARTICIPATION + "PARTICIPATION_COMPONENT"
            + "\nExample: " + COMMAND_WORD + " 1 yes " + PREFIX_PARTICIPATION + "P01"
            + "\nExample: " + COMMAND_WORD + " all yes " + PREFIX_PARTICIPATION + "P02";

    public static final String MESSAGE_MARK_SINGLE_SUCCESS_PARTICIPATION = "Recorded Student as %1$s: %2$s";
    public static final String MESSAGE_MARK_MULTI_SUCCESS_PARTICIPATION = "Recorded %1$s students as %2$s";

    public static final String MESSAGE_NO_EDIT = "Participation component must be provided.";

    public ParticipateCommand(IndexListGenerator indexListGenerator, ParticipateCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        requireNonNull(studentEditor.getParticipation());
        return String.format(MESSAGE_MARK_SINGLE_SUCCESS_PARTICIPATION,
                studentEditor.getParticipation().getParticipationString(),
                editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        requireNonNull(studentEditor.getParticipation());
        return String.format(MESSAGE_MARK_MULTI_SUCCESS_PARTICIPATION,
                editedStudents.size(), studentEditor.getParticipation());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Participation for a given Student.
     */
    public static class ParticipateCommandStudentEditor implements StudentEditor {

        private final Participation participation;

        /**
         * Constructor using Participation.
         *
         * @param participation Participation to edit the student with.
         */
        public ParticipateCommandStudentEditor(Participation participation) {
            this.participation = participation;
        }

        public Participation getParticipation() {
            return participation;
        }



        @Override
        public Student editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();

            if (participation != null) {
                Set<Participation> newParticipation = new HashSet<>(studentToEdit.getParticipations());
                newParticipation.remove(participation);
                newParticipation.add(participation);
                studentData.setParticipations(newParticipation);
            }

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
            if (!(other instanceof ParticipateCommand.ParticipateCommandStudentEditor)) {
                return false;
            }

            // state check
            ParticipateCommand.ParticipateCommandStudentEditor e =
                    (ParticipateCommand.ParticipateCommandStudentEditor) other;

            if (getParticipation() == null && e.getParticipation() != null) {
                return false;
            } else if (getParticipation() == null && e.getParticipation() == null) {
                return true;
            }

            return getParticipation().equals(e.getParticipation());
        }
    }
}
