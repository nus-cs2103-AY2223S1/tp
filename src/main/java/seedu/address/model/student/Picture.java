package seedu.address.model.student;

import seedu.address.logic.commands.exceptions.CommandException;

import java.io.*;


public class Picture {
    public static final String PICTURE_CONSTRAINTS = "Picture should be in .jpg format.";
    public static final String MESSAGE_INVALID_FILE_PATH = "File path should not contain empty spaces, and should not be blank";
    public static final String VALIDATION_REGEX = "^\\S*$";
    private final String filePath;

    public static boolean isValidFilePath(String filepath) {
        return filepath.matches(VALIDATION_REGEX) && !filepath.isBlank();
    }

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

    public static void copyImage(File inFile, File outFile) throws CommandException{
        try {
            FileInputStream inputFile = new FileInputStream(inFile);
            FileOutputStream outputFile = new FileOutputStream(outFile);
            int dataByte;
            while ((dataByte = inputFile.read()) != -1) {
                outputFile.write(dataByte);
            }
            outputFile.close();
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new CommandException("File read has an error please use another file.");
        }
    }
    public Picture(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }






}
