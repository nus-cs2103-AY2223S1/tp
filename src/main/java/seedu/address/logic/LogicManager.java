package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ProfNusParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ProfNusParser profNusParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        profNusParser = new ProfNusParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = profNusParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveProfNus(model.getProfNus());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyProfNus getProfNus() {
        return model.getProfNus();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getAllPersonList() {
        return model.getAllPersonList();
    }

    @Override
    public ObservableList<Student> getFilteredTutorList() {
        return model.getFilteredTutorList();
    }

    @Override
    public ObservableList<Student> getAllTutorList() {
        return model.getAllTutorList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<Module> getAllModuleList() {
        return model.getAllModuleList();
    }

    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return model.getFilteredScheduleList();
    }

    @Override
    public ObservableList<Schedule> getAllScheduleList() {
        return model.getAllScheduleList();
    }

    @Override
    public Path getProfNusFilePath() {
        return model.getProfNusFilePath();
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
