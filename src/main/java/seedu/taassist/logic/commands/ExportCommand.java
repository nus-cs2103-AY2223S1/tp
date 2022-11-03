package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_EMPTY_GRADE;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_LINE_BREAK;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_NAME_COLUMN_HEADER;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_SEPARATOR;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.taassist.logic.commands.actions.ExportCsvStorageAction;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.StudentModuleData;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.IsPartOfClassPredicate;
import seedu.taassist.model.student.Student;

/**
 * Retrieve module class information and creates a {@code StorageCommand} to
 * export the module class as a CSV file containing student's grade information.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_DATA_RETRIEVAL_FAILED = "Unable to retrieve class data.";
    public static final String EMPTY_FEEDBACK = "";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();
        IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(focusedClass);
        List<Student> students = model.getStudentList().stream().filter(predicate).collect(Collectors.toList());
        String fileName = focusedClass.getClassName();
        String fileData;
        try {
            fileData = moduleClassToCsvString(focusedClass, students);
        } catch (AssertionError e) {
            throw new CommandException(String.format(MESSAGE_DATA_RETRIEVAL_FAILED, fileName));
        }

        return new CommandResult(EMPTY_FEEDBACK, new ExportCsvStorageAction(fileName, fileData));
    }

    /**
     * Returns a string containing data to be written to the output CSV file based on
     * the {@code moduleClass} and the {@code students}.
     */
    private static String moduleClassToCsvString(ModuleClass moduleClass, List<Student> students)
            throws AssertionError {

        List<List<String>> fileData = new ArrayList<>();
        List<Session> sessions = moduleClass.getSessions();

        // Header row
        List<String> headerRow = new ArrayList<>();
        headerRow.add(CSV_NAME_COLUMN_HEADER);

        // Column header for each session
        for (Session s : sessions) {
            headerRow.add(s.getSessionName());
        }
        fileData.add(headerRow);

        // Row for each student
        for (Student student : students) {
            List<String> row = new ArrayList<>();
            row.add(student.getName().toString());

            Optional<StudentModuleData> moduleDataOptional = student.findStudentModuleData(moduleClass);
            assert(moduleDataOptional.isPresent());
            StudentModuleData moduleData = moduleDataOptional.get();

            for (Session s : sessions) {
                Optional<SessionData> sessionDataOptional = moduleData.findSessionData(s);
                row.add(sessionDataOptional
                        .map(SessionData::getGrade)
                        .map(String::valueOf)
                        .orElse(CSV_EMPTY_GRADE));
            }
            fileData.add(row);
        }

        String content = fileData.stream()
                .map(ls -> ls.stream().collect(Collectors.joining(CSV_SEPARATOR)))
                .collect(Collectors.joining(CSV_LINE_BREAK));

        return content;
    }
}
