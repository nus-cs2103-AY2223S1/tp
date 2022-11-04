package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.MainApp;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TeachersPetParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.StatisticsCalculator;
import seedu.address.model.student.Student;
import seedu.address.storage.ClassStorage;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TeachersPetParser teachersPetParser;
    private final StatisticsCalculator statisticsCalculator;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        teachersPetParser = new TeachersPetParser();
        statisticsCalculator = new StatisticsCalculator(model.getTeachersPet());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException,
            IOException, DataConversionException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = teachersPetParser.parseCommand(commandText);
        model.updateTeachersPetHistory();
        try {
            commandResult = command.execute(model);
        } catch (CommandException | DataConversionException e) {
            logger.info("Invalid command: " + commandText);
            model.deleteTeachersPetHistory();
            throw e;
        }

        ClassStorage.refresh(model);

        try {
            if (!MainApp.isInInvalidFormat()) {
                storage.saveTeachersPet(model.getTeachersPet());
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTeachersPet getTeachersPet() {
        return model.getTeachersPet();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Student> getFilteredScheduleList() {
        return model.getFilteredScheduleList();
    }

    @Override
    public Path getTeachersPetFilePath() {
        return model.getTeachersPetFilePath();
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
    public StatisticsCalculator getStatisticsCalculator() {
        return statisticsCalculator;
    }
}
