package seedu.address.model.student;

import seedu.address.logic.commands.exceptions.CommandException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Picture {
    public static final String PICTURE_CONSTRAINTS = "Picture should be in .jpg format.";
    public static final String DEFAULT_PICTURE_PATH = "profile/default-profile-pic.jpg";
    public String filePath;


    public static boolean isJpgFile(File file) throws CommandException {
        int[] JpgByteArray = new int[] {255, 216, 255, 224};
        try {
            FileInputStream inputFile = new FileInputStream(file);
            int checkByte;
            for (int counter = 0; counter < 4; counter++) {
                checkByte = inputFile.read();
                if (JpgByteArray[counter] != checkByte) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new CommandException("File not found from Picture's check");
        } catch (IOException e) {
            throw new CommandException("Unable to read file");
        }
        return true;

    }


    public Picture(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return this.filePath;
    }
}
