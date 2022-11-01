package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EXAM;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXAMS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.Module;

/**
 * Edits the exam with the specified index number in the displayed exam list.
 */
public class EditExamCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD + ": Edits the details of the exam identified "
            + "by the index number used in the displayed exam list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE + "MODULE]* "
            + "[" + PREFIX_EXAM_DESCRIPTION + "EXAM DESCRIPTION]* "
            + "[" + PREFIX_EXAM_DATE + "EXAM DATE]* " + "\n"
            + "Example: e " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "cs2030s "
            + PREFIX_EXAM_DESCRIPTION + "finals "
            + PREFIX_EXAM_DATE + "20-12-2022";

    public static final String MESSAGE_EDIT_EXAM_SUCCESS = "Successfully Edited Exam: %1$s";
    public static final String MESSAGE_EXAM_NOT_EDITED = "Please provide a module or exam description or exam date "
            + "different from the exam's current module and description and exam date";;
    public static final String MESSAGE_NO_FIELDS_PROVIDED =
            "Please provide at least one of the fields to edit: m/MODULE, ex/EXAMDESCRIPTION, ed/EXAMDATE";
    private final Index index;
    private final EditExamDescriptor editExamDescriptor;

    /**
     * @param index of the exam in the filtered exam list to edit
     * @param editExamDescriptor details to edit the exam with
     */
    public EditExamCommand(Index index, EditExamDescriptor editExamDescriptor) {
        requireAllNonNull(index, editExamDescriptor);
        this.index = index;
        this.editExamDescriptor = editExamDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> lastShownList = model.getFilteredExamList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_EXAM_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        if (editExamDescriptor.getModule().isPresent() && !model.hasModule(editExamDescriptor.module)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        Exam examToEdit = lastShownList.get(index.getZeroBased());
        Exam editedExam = examToEdit.edit(editExamDescriptor);

        if (examToEdit.isSameExam(editedExam)) {
            throw new CommandException(MESSAGE_EXAM_NOT_EDITED);
        }

        boolean isEditedExamOfSameModule = examToEdit.getModule().isSameModule(editedExam.getModule());

        try {
            model.replaceExam(examToEdit, editedExam, false);
            if (!isEditedExamOfSameModule && model.isExamLinkedToTask(examToEdit)) {
                model.unlinkTasksFromExam(examToEdit);
                return new CommandResult(String.format(MESSAGE_EDIT_EXAM_SUCCESS, editedExam) + "\n"
                        + "Warning! All the tasks previously linked to this exam are now unlinked.");
            } else {
                model.updateExamFieldForTask(examToEdit, editedExam);
            }
        } catch (DuplicateExamException e) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.updateFilteredExamList(PREDICATE_SHOW_ALL_EXAMS);
        return new CommandResult(String.format(MESSAGE_EDIT_EXAM_SUCCESS, editedExam));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditExamCommand)) {
            return false;
        }

        // state check
        EditExamCommand e = (EditExamCommand) other;
        return index.equals(e.index)
                && editExamDescriptor.equals(e.editExamDescriptor);
    }

    /**
     * Stores the details to edit the exam with. Each non-empty field value will replace the
     * corresponding field value of the exam.
     */
    public static class EditExamDescriptor {
        private Module module;
        private ExamDescription description;
        private ExamDate examDate;

        public EditExamDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditExamDescriptor(EditExamDescriptor toCopy) {
            setExamDate(toCopy.examDate);
            setDescription(toCopy.description);
            setModule(toCopy.module);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(module, description, examDate);
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setDescription(ExamDescription description) {
            this.description = description;
        }

        public Optional<ExamDescription> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setExamDate(ExamDate examDate) {
            this.examDate = examDate;
        }

        public Optional<ExamDate> getExamDate() {
            return Optional.ofNullable(examDate);
        }
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExamDescriptor)) {
                return false;
            }

            // state check
            EditExamDescriptor e = (EditExamDescriptor) other;

//            return module.equals(e.module)
//                    && description.equals(e.description) && examDate.equals(e.examDate);
            //not sure if this is correct...
            return getModule().equals(e.getModule()) && getDescription().equals(e.getDescription())
                    && getExamDate().equals(e.getExamDate());
        }
    }
}
