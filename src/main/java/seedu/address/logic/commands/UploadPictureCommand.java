package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Picture;
import seedu.address.model.student.Student;
import seedu.address.storage.ImageStorage;

/**
 * Adds a profile picture for a student.
 */
public class UploadPictureCommand extends Command {

    public static final String COMMAND_WORD = "upload-pic";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads a profile picture to the student indentified."
            + "Parameters: index of student you would like to edit.";

    public static final String MESSAGE_SUCCESS = "Student's profile picture has been updated!";

    private final Index index;

    /**
     * Creates a new UploadPictureCommand Object.
     * @param index
     */
    public UploadPictureCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        File studentPicture = ImageStorage.chooseImage();
        if (studentPicture == null) {
            throw new CommandException("No image selected!");
        }
        if (!Picture.isJpgFile(studentPicture)) {
            throw new CommandException(Picture.PICTURE_CONSTRAINTS);
        }
        ImageStorage.uploadImage(studentToEdit, studentPicture);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UploadPictureCommand)) {
            return false;
        }

        UploadPictureCommand u = (UploadPictureCommand) other;
        return this.index.equals(u.index);
    }
}
