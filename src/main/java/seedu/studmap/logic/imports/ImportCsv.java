package seedu.studmap.logic.imports;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import seedu.studmap.logic.imports.exceptions.ImportException;
import seedu.studmap.logic.parser.ParserUtil;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Parses CSV file and loads it into the model.
 */
public class ImportCsv {

    public static final String FILE_IS_NULL = "File is null!";
    public static final String FILE_DOES_NOT_EXIST = "File does not exist!";

    public static final String FILE_CANNOT_BE_READ = "File cannot be read!";

    public static final String FILE_NOT_CSV = "File is not a CSV file!";

    public static final String CSV_WRONG_FORMAT = "CSV template is wrongly formatted! "
            + "Please use the one in the UG strictly.";

    public static final String MISSING_COMPULSORY_ATTRIBUTE = "Row %d has missing compulsory attribute %s!\n";
    public static final String DUPLICATE_STUDENT = "Row %d is a duplicate entry!\n";

    public final String delimiter = "\\,";

    public final String[] templateHeader = {
        "name",
        "studentid",
        "module",
        "phone",
        "email",
        "github",
        "telegram",
    };

    public final Attributes[] enumList = {
        Attributes.NAME,
        Attributes.STUDENTID,
        Attributes.MODULE,
        Attributes.PHONE,
        Attributes.EMAIL,
        Attributes.GITHUB,
        Attributes.TELEGRAM
    };

    private StringBuilder log;

    private int rowNumber;

    /**
     * Executes the ImportCSV process, taking a model and file and updating the model with the imported students.
     *
     * @param model Model to update
     * @param file  CSV file containing import data
     * @return Error message containing logs during import
     * @throws ImportException
     */
    public String execute(Model model, File file) throws ImportException {

        if (file == null) {
            throw new ImportException(FILE_IS_NULL);
        }

        log = new StringBuilder();
        if (!file.exists()) {
            throw new ImportException(FILE_DOES_NOT_EXIST);
        } else if (!file.canRead()) {
            throw new ImportException(FILE_CANNOT_BE_READ);
        } else if (!checkIfCsv(file.getName())) {
            throw new ImportException(FILE_NOT_CSV);
        }

        Scanner sc;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new ImportException(FILE_DOES_NOT_EXIST);
        }

        if (!verifyFirstLine(sc.nextLine().split(delimiter, -1))) {
            throw new ImportException(CSV_WRONG_FORMAT);
        }

        rowNumber = 2;
        while (sc.hasNextLine()) {
            processLine(sc.nextLine().split(delimiter, -1), model);
            rowNumber++;
        }
        sc.close();
        return log.toString();
    }

    /**
     * Checks if the file is a CSV file.
     *
     * @param filename Filename to check
     * @return Boolean if file is CSV
     */
    public boolean checkIfCsv(String filename) {
        String[] fileNameSplit = filename.split("\\.");

        return fileNameSplit.length != 1 && fileNameSplit[fileNameSplit.length - 1].equals("csv");
    }

    /**
     * Checks if the first line follows the proper header template
     *
     * @param firstLine First line of the CSV
     * @return Boolean if first line correctly follows the template
     */
    public boolean verifyFirstLine(String[] firstLine) {
        if (firstLine.length != 7) {
            return false;
        }

        for (int i = 0; i < 7; i++) {
            if (!firstLine[i].equals(templateHeader[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Processes the CSV line input, creating a Student and updating the Model with it.
     *
     * @param inputLine Input from CSV
     * @param model     Model to update
     */
    public void processLine(String[] inputLine, Model model) {
        StudentData studentData = new StudentData();

        for (int i = 0; i < inputLine.length; i++) {
            Attributes currentAttribute = enumList[i];
            String currentInput = inputLine[i];
            try {
                switch (currentAttribute) {
                case NAME:
                    studentData.setName(ParserUtil.parseName(currentInput));
                    break;
                case STUDENTID:
                    studentData.setId(ParserUtil.parseId(currentInput));
                    break;
                case MODULE:
                    studentData.setModule(ParserUtil.parseModule(currentInput));
                    break;
                case PHONE:
                    studentData.setPhone(ParserUtil.parsePhone(currentInput));
                    break;
                case EMAIL:
                    studentData.setEmail(ParserUtil.parseEmail(currentInput));
                    break;
                case GITHUB:
                    studentData.setGitUser(ParserUtil.parseGitName(currentInput));
                    break;
                case TELEGRAM:
                    studentData.setTeleHandle(ParserUtil.parseHandle(currentInput));
                    break;
                default:
                    break;
                }
            } catch (ParseException e) {
                if (currentAttribute == Attributes.NAME || currentAttribute == Attributes.STUDENTID
                        || currentAttribute == Attributes.MODULE) {
                    log.append(String.format(MISSING_COMPULSORY_ATTRIBUTE,
                            rowNumber, currentAttribute));
                    return;
                }
            }
        }

        Student newStudent = new Student(studentData);
        if (model.hasStudent(newStudent)) {
            log.append(String.format(DUPLICATE_STUDENT, rowNumber));
            return;
        }
        model.addStudent(newStudent);
    }

    /**
     * Attributes used by ImportCsv.
     */
    public enum Attributes {
        NAME,
        STUDENTID,
        MODULE,
        PHONE,
        EMAIL,
        GITHUB,
        TELEGRAM
    }

}
