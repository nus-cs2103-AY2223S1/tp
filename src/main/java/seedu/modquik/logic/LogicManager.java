package seedu.modquik.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.modquik.commons.core.GuiSettings;
import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.logic.parser.ModQuikParser;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModQuikParser modQuikParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        modQuikParser = new ModQuikParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = modQuikParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveModQuik(model.getModQuik());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyModQuik getModQuik() {
        return model.getModQuik();
    }

    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        return model.getFilteredReminderList();
    }

    @Override
    public ObservableList<Tutorial> getFilteredTutorialList() {
        return model.getFilteredTutorialList();
    }

    @Override
    public ObservableList<Consultation> getFilteredConsultationList() {
        return model.getFilteredConsultationList();
    }

    @Override
    public ObservableList<PieChart.Data> getStudentGradeChartData() {
        return model.getStudentGradeChartData();
    }

    @Override
    public Path getModQuikFilePath() {
        return model.getModQuikFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
