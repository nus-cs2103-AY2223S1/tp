package friday.logic.commands;

import static friday.commons.util.CollectionUtil.requireAllNonNull;
import static friday.logic.parser.CliSyntax.PREFIX_REMARK;
import static friday.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

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

public class GradeCommand extends Command {
    public static final String COMMAND_WORD = "grade";
    // NEED TO EDIT THIS
    public static final String MESSAGE_USAGE = COMMAND_WORD + "";

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
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        GradesList editedGradesList = createEditedGradesList(studentToEdit, editGradeDescriptor);
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getTelegramHandle(),
                studentToEdit.getConsultation(), studentToEdit.getMasteryCheck(), studentToEdit.getRemark(),
                studentToEdit.getTags(), editedGradesList);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_EDIT_GRADE_SUCCESS, studentToEdit));
    }

    private static GradesList createEditedGradesList(Student studentToEdit, EditGradeDescriptor editGradeDescriptor) {
        assert studentToEdit != null;
        GradesList gradesList = studentToEdit.getGradesList();
        editGradeDescriptor.getRa1().ifPresent(t -> GradesList.editGrade(gradesList, t));
        editGradeDescriptor.getRa2().ifPresent(t -> GradesList.editGrade(gradesList, t));
        editGradeDescriptor.getPa().ifPresent(t -> GradesList.editGrade(gradesList, t));
        editGradeDescriptor.getMt().ifPresent(t -> GradesList.editGrade(gradesList, t));
        editGradeDescriptor.getFt().ifPresent(t -> GradesList.editGrade(gradesList, t));
        return gradesList;
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
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
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

        public void setRa2(Grade ra1) {
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
