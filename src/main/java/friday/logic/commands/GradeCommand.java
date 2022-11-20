package friday.logic.commands;

import static friday.commons.util.CollectionUtil.requireAllNonNull;
import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static friday.logic.parser.CliSyntax.PREFIX_PRACTICAL;
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.logic.parser.CliSyntax.PREFIX_RA2;

import java.util.List;
import java.util.Optional;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.commons.util.CollectionUtil;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.grades.Grade;
import friday.model.grades.GradesList;
import friday.model.student.Student;

/**
 * Grades an assessment for a student identified using it's displayed index from FRIDAY.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the grades of the student identified "
            + "by the index number used in the displayed list of students. \n"
            + "Existing grades will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RA1 + "RA1_SCORE] "
            + "[" + PREFIX_RA2 + "RA2_SCORE] "
            + "[" + PREFIX_PRACTICAL + "PRACTICAL_SCORE] "
            + "[" + PREFIX_MIDTERM + "MID_TERM_SCORE] "
            + "[" + PREFIX_FINALS + "FINALS_SCORE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RA1 + "69.90 "
            + PREFIX_RA2 + "90 "
            + PREFIX_PRACTICAL + "100 "
            + PREFIX_MIDTERM + "99.99 "
            + PREFIX_FINALS + "75.74 ";

    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Updated grade for Student: %1$s";

    private final Index index;
    private final EditGradeDescriptor editGradeDescriptor;

    /**
     * @param index of the student in the filtered student list to edit the remark
     * @param editGradeDescriptor details to edit the student's grades with
     */
    public GradeCommand(Index index, EditGradeDescriptor editGradeDescriptor) {
        requireAllNonNull(index, editGradeDescriptor);

        this.index = index;
        this.editGradeDescriptor = editGradeDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        GradesList editedGradesList = createEditedGradesList(studentToEdit, editGradeDescriptor);
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getTelegramHandle(),
                studentToEdit.getConsultation(), studentToEdit.getMasteryCheck(), studentToEdit.getRemark(),
                studentToEdit.getTags(), editedGradesList);
        model.setStudent(studentToEdit, editedStudent);

        return new CommandResult(String.format(MESSAGE_EDIT_GRADE_SUCCESS, studentToEdit));
    }

    /**
     * Creates and returns a {@code GradesList} with the details of {@code studentToEdit}
     * edited with {@code editGradeDescriptor}
     *
     * @param studentToEdit the student to update the grades of
     * @param editGradeDescriptor the details of the grades to update
     * @return the updated GradesList
     */
    private static GradesList createEditedGradesList(Student studentToEdit, EditGradeDescriptor editGradeDescriptor) {
        assert studentToEdit != null;
        GradesList gradesList = studentToEdit.getGradesList();
        GradesList newGradesList = new GradesList();
        GradesList.editGrade(newGradesList, gradesList.getGrade("RA1"));
        GradesList.editGrade(newGradesList, gradesList.getGrade("RA2"));
        GradesList.editGrade(newGradesList, gradesList.getGrade("Midterm"));
        GradesList.editGrade(newGradesList, gradesList.getGrade("Practical"));
        GradesList.editGrade(newGradesList, gradesList.getGrade("Finals"));
        editGradeDescriptor.getRa1().ifPresent(t -> GradesList.editGrade(newGradesList, t));
        editGradeDescriptor.getRa2().ifPresent(t -> GradesList.editGrade(newGradesList, t));
        editGradeDescriptor.getPa().ifPresent(t -> GradesList.editGrade(newGradesList, t));
        editGradeDescriptor.getMt().ifPresent(t -> GradesList.editGrade(newGradesList, t));
        editGradeDescriptor.getFt().ifPresent(t -> GradesList.editGrade(newGradesList, t));
        return newGradesList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeCommand)) {
            return false;
        }

        // state check
        GradeCommand e = (GradeCommand) other;
        return index.equals(e.index)
                && editGradeDescriptor.equals(e.editGradeDescriptor);
    }

    /**
     * Stores the details to edit the grades of a student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditGradeDescriptor {
        private Grade ra1;
        private Grade ra2;
        private Grade mt;
        private Grade ft;
        private Grade pa;

        public EditGradeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGradeDescriptor(EditGradeDescriptor toCopy) {
            setRa1(toCopy.ra1);
            setRa2(toCopy.ra2);
            setPa(toCopy.pa);
            setMt(toCopy.mt);
            setFt(toCopy.ft);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(ra1, ra2, pa, mt, ft);
        }

        public void setRa1(Grade ra1) {
            this.ra1 = ra1;
        }

        public Optional<Grade> getRa1() {
            return Optional.ofNullable(ra1);
        }

        public void setRa2(Grade ra2) {
            this.ra2 = ra2;
        }

        public Optional<Grade> getRa2() {
            return Optional.ofNullable(ra2);
        }

        public void setPa(Grade pa) {
            this.pa = pa;
        }

        public Optional<Grade> getPa() {
            return Optional.ofNullable(pa);
        }

        public void setMt(Grade mt) {
            this.mt = mt;
        }

        public Optional<Grade> getMt() {
            return Optional.ofNullable(mt);
        }

        public void setFt(Grade ft) {
            this.ft = ft;
        }

        public Optional<Grade> getFt() {
            return Optional.ofNullable(ft);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGradeDescriptor)) {
                return false;
            }

            // state check
            EditGradeDescriptor e = (EditGradeDescriptor) other;

            return getRa1().equals(e.getRa1())
                    && getRa2().equals(e.getRa2())
                    && getPa().equals(e.getPa())
                    && getMt().equals(e.getMt())
                    && getFt().equals(e.getFt());
        }
    }
}
