package seedu.classify.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.classify.commons.core.GuiSettings;
import seedu.classify.commons.core.LogsCenter;
import seedu.classify.logic.commands.Command;
import seedu.classify.logic.commands.CommandResult;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.StudentRecordParser;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.FilteredStudents;
import seedu.classify.model.Model;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.student.Student;
import seedu.classify.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StudentRecordParser studentRecordParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        studentRecordParser = new StudentRecordParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = studentRecordParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStudentRecord(model.getStudentRecord());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudentRecord getStudentRecord() {
        return model.getStudentRecord();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getStudentRecordFilePath() {
        return model.getStudentRecordFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public FilteredStudents getFilteredStudents() {
        return this.model.getFilteredStudents();
    }

}
