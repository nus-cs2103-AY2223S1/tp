package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.studmap.MainApp;
import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.util.CollectionUtil;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.tag.Tag;

/**
 * Deletes tag for an existing student in the student map.
 */
public class UntagCommand extends EditStudentCommand<UntagCommand.UntagCommandStudentEditor> {

    public static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes tags from a student in the student map.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer or use \"all\" to delete tags from everyone in the list) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "lab "
            + PREFIX_TAG + "goodProgress\n"
            + "Example: " + COMMAND_WORD + " all "
            + PREFIX_TAG + "tutorial "
            + PREFIX_TAG + "needRemedial";

    public static final String MESSAGE_SINGLE_DEL_TAGS_SUCCESS = "Deleted tags %1$s Student: %2$s";

    public static final String MESSAGE_MULTI_DEL_TAGS_SUCCESS = "Deleted tags %1$s for %2$d students";

    public static final String MESSAGE_TAGS_NOT_DELETED = "At least one tag must be deleted";

    public UntagCommand(IndexListGenerator indexListGenerator, UntagCommandStudentEditor editor) {
        super(indexListGenerator, editor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_SINGLE_DEL_TAGS_SUCCESS, CollectionUtil.collectionToString(studentEditor.tags),
                editedStudent.getName());
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_MULTI_DEL_TAGS_SUCCESS,
                CollectionUtil.collectionToString(studentEditor.tags),
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_TAGS_NOT_DELETED;
    }


    /**
     * A static StudentEditor that deletes tags to a given Student.
     */
    public static class UntagCommandStudentEditor implements StudentEditor {

        private Set<Tag> tags;

        /**
         * Parameterless constructor.
         */
        public UntagCommandStudentEditor() {
        }

        /**
         * Constructor with tags.
         *
         * @param tags Tags that this editor will delete from a given Student.
         */
        public UntagCommandStudentEditor(Set<Tag> tags) {
            requireNonNull(tags);
            setTags(tags);
        }

        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(tags);
        }

        public void setTags(Set<Tag> tags) {
            requireNonNull(tags);
            this.tags = new HashSet<>(tags);
        }

        @Override
        public Student editStudent(Student studentToEdit) {
            assert studentToEdit != null;

            StudentData studentData = studentToEdit.getStudentData();
            Set<Tag> newTags = studentData.getTags();
            newTags.removeAll(tags);
            studentData.setTags(newTags);

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return tags != null && tags.size() > 0;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditCommandStudentEditor)) {
                return false;
            }

            // state check
            EditCommand.EditCommandStudentEditor e = (EditCommand.EditCommandStudentEditor) other;

            return getTags().equals(e.getTags());
        }
    }
}
