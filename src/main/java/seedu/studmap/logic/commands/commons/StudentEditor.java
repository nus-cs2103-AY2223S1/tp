package seedu.studmap.logic.commands.commons;

import seedu.studmap.model.student.Student;

/**
 * An abstract class that encapsulates the manner in which a student may be edited.
 */
public interface StudentEditor {

    /**
     * Creates a new student by editing a given student.
     * Editing behaviour is to be defined in the inherited class.
     *
     * @param studentToEdit Student to be edited
     * @return New edited student
     */
    EditResult editStudent(Student studentToEdit);

    /**
     * Returns true if this editor will make edits when used.
     */
    boolean hasEdits();

    /**
     * A class that encapsulates the edit result of an editStudent operation.
     */
    final class EditResult {
        public final Student editedStudent;
        public final boolean isEdited;

        /**
         * Constructor for EditResult.
         *
         * @param editedStudent Student that has been edited.
         * @param isEdited      Whether there is an edit.
         */
        public EditResult(Student editedStudent, boolean isEdited) {
            this.editedStudent = editedStudent;
            this.isEdited = isEdited;
        }
    }
}
