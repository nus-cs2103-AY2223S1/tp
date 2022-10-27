package seedu.studmap.logic.imports;

import seedu.studmap.logic.commands.CommandResult;
import seedu.studmap.logic.imports.exceptions.ImportException;
import seedu.studmap.logic.parser.ParserUtil;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parses CSV file and loads it into the model.
 */
public class ImportCSV {

    public static final String FILE_DOES_NOT_EXIST = "File does not exist!";

    public static final String FILE_CANNOT_BE_READ = "File cannot be read!";

    public static final String FILE_NOT_CSV = "File is not a CSV file!";

    public static final String CSV_WRONG_FORMAT = "CSV template is wrongly formatted! "
            + "Please use the one in the UG strictly.";

    public final String delimiter = "\\,";

    private StringBuilder log;

    private int rowNumber;

    private enum Attributes {
        NAME,
        STUDENTID,
        MODULE,
        PHONE,
        EMAIL,
        GITHUB,
        TELEGRAM
    }

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


    public String execute(Model model, File file) throws ImportException {
        log = new StringBuilder();
        if (!file.exists()) {
            throw new ImportException(FILE_DOES_NOT_EXIST);
        } else if (!file.canRead()) {
            throw new ImportException(FILE_CANNOT_BE_READ);
        } else if (!checkIfCSV(file.getName())) {
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

    public boolean checkIfCSV(String filename) {
        String[] fileNameSplit = filename.split("\\.");

        return fileNameSplit.length != 1 && fileNameSplit[fileNameSplit.length - 1].equals("csv");
    }

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
                }
            } catch (ParseException e) {
                if (currentAttribute == Attributes.NAME || currentAttribute == Attributes.STUDENTID
                        || currentAttribute == Attributes.MODULE) {
                    log.append(String.format("Row %d has missing compulsory attribute %s!\n",
                            rowNumber, currentAttribute));
                    return;
                }
            }
        }

        Student newStudent = new Student(studentData);
        if (model.hasStudent(newStudent)) {
            log.append(String.format("Row %d is a duplicate entry!\n", rowNumber));
            return;
        }
        model.addStudent(newStudent);
    }

}
