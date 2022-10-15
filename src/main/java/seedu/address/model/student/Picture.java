package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;


/**
 * To process a student's picture.
 */
public class Picture {
    public static final String PICTURE_CONSTRAINTS = "Picture should be in .jpg format.";

    /**
     * Returns whether the given file is a .jpg file.
     * @param file
     * @return Whether the {@code file} is a .jpg file.
     * @throws CommandException If file cannot be found or unable to read the file.
     */
    public static boolean isJpgFile(File file) throws CommandException {
        int[] jpgByteArray = new int[] {255, 216, 255, 224};
        requireNonNull(file);
        try {
            FileInputStream inputFile = new FileInputStream(file);
            int checkByte;
            for (int counter = 0; counter < 4; counter++) {
                checkByte = inputFile.read();
                if (jpgByteArray[counter] != checkByte) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new CommandException("File not found!");
        } catch (IOException e) {
            throw new CommandException("Unable to read file");
        }
        return true;

    }
}
