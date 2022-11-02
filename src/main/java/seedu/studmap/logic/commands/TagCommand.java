package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.util.CollectionUtil;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.tag.Tag;

/**
 * Add tag for an existing student in the student map.
 */
public class TagCommand extends EditStudentCommand<TagCommand.TagCommandStudentEditor> {

    public static final Logger LOGGER = LogsCenter.getLogger(TagCommand.class);

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tags for a student in the student map.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer or use \"all\" to add tags for everyone in the list)"
            + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "lab "
            + PREFIX_TAG + "goodProgress\n"
            + "Example: " + COMMAND_WORD + " all "
            + PREFIX_TAG + "tutorial "
            + PREFIX_TAG + "needRemedial";

    public static final String MESSAGE_SINGLE_ADD_TAGS_SUCCESS = "Added tags %1$s Student: %2$s";

    public static final String MESSAGE_MULTI_ADD_TAGS_SUCCESS = "Added tags %1$s for %2$d students";

    public static final String MESSAGE_TAGS_NOT_ADDED = "At least one tag must be added";

    public TagCommand(IndexListGenerator indexListGenerator, TagCommandStudentEditor editor) {
        super(indexListGenerator, editor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_SINGLE_ADD_TAGS_SUCCESS, CollectionUtil.collectionToString(studentEditor.tags),
                editedStudent.getName());
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(studentEditor.tags),
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_TAGS_NOT_ADDED;
    }


    /**
     * A static StudentEditor that adds tags to a given Student.
     */
    public static class TagCommandStudentEditor implements StudentEditor {

        private Set<Tag> tags;

        /**
         * Parameterless constructor.
         */
        public TagCommandStudentEditor() {
        }

        /**
         * Constructor with tags.
         *
         * @param tags Tags that this editor will add to a given Student.
         */
        public TagCommandStudentEditor(Set<Tag> tags) {
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
            newTags.addAll(tags);
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
