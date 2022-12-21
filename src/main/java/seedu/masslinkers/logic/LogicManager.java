package seedu.masslinkers.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.logic.commands.Command;
import seedu.masslinkers.logic.commands.CommandResult;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.logic.parser.MassLinkersParser;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MassLinkersParser massLinkersParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        massLinkersParser = new MassLinkersParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = massLinkersParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMassLinkers(model.getMassLinkers());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMassLinkers getMassLinkers() {
        return model.getMassLinkers();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }
    @Override
    public Path getMassLinkersFilePath() {
        return model.getMassLinkersFilePath();
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
