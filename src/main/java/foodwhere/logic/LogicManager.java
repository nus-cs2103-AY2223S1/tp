package foodwhere.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import foodwhere.commons.core.GuiSettings;
import foodwhere.commons.core.LogsCenter;
import foodwhere.logic.commands.Command;
import foodwhere.logic.commands.CommandResult;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.AddressBookParser;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.Model;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;
import foodwhere.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class
LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Stall> getFilteredStallList() {
        return model.getFilteredStallList();
    }

    @Override
    public ObservableList<Review> getFilteredReviewList() {
        return model.getFilteredReviewList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
