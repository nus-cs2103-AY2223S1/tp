package seedu.uninurse.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.logic.commands.Command;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.parser.UninurseBookParser;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UninurseBookParser uninurseBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        uninurseBookParser = new UninurseBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = uninurseBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            model.updateRecurringTasks();
            storage.saveUninurseBook(model.getUninurseBook());
            if (command.isUndoable()) {
                model.makeSnapshot(commandResult);
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyUninurseBook getUninurseBook() {
        return model.getUninurseBook();
    }

    @Override
    public ObservableList<Patient> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getUninurseBookFilePath() {
        return model.getUninurseBookFilePath();
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
    public Patient getPatientOfInterest() {
        return model.getPatientOfInterest();
    }

    @Override
    public Schedule getSchedule() {
        return model.getSchedule();
    }

    @Override
    public PatientListTracker getSavedPatientListTracker() {
        return model.getSavedPatientListTracker();
    }
}
