package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICTURE;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Picture;
import seedu.address.model.student.Student;

import java.io.*;
import java.util.List;

/**
 * Adds a profile picture for a student.
 */
public class UploadPictureCommand extends Command {

    public static final String COMMAND_WORD = "upload-pic";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads a profile picture to the student indetified."
            + "Parameters: "
            + PREFIX_PICTURE + "File path of picture you want to upload.";

    public static final String MESSAGE_SUCCESS = "Student's profile picture has been updated!";

    private final Index index;
    private final Picture picture;

    public UploadPictureCommand(Picture picture, Index index) {
        requireNonNull(picture);
        requireNonNull(index);
        this.picture = picture;
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
        String pictureFileName = studentToEdit.getStudentId().toString();
        String resultFileName =  "data/profile/" + pictureFileName + ".jpg";
        String inputFileName = this.picture.getFilePath();

        File outputFile = new File(resultFileName);
        File inputFile = new File(inputFileName);

        if (!Picture.isJpgFile(inputFile)) {
            throw new CommandException(Picture.PICTURE_CONSTRAINTS);
        }
        Picture.copyImage(inputFile, outputFile);
        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UploadPictureCommand)){
            return false;
        }

        UploadPictureCommand u = (UploadPictureCommand) other;
        return this.index.equals(u.index) && this.picture.equals(u.picture);
    }
}
