package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.UniqueId;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
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
    public CommandResult executeGivenCommand(Command command) throws CommandException {
        CommandResult commandResult = command.execute(model);
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
    public ObservableList<Object> getFilteredCurrList() {
        return model.getFilteredCurrList();
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

    @Override
    public ObservableList<Order> getOrderAsObservableListFromBuyer(Buyer buyer) {
        List<UniqueId> ids = buyer.getOrderIds();
        List<Order> filteredOrders = model.getFilteredOrderList();
        List<Order> ordersFromBuyer = filteredOrders.stream()
                .filter(order -> ids.stream()
                        .anyMatch(order::hasId))
                .collect(Collectors.toList());

        return FXCollections.observableList(ordersFromBuyer);
    }

    @Override
    public ObservableList<Order> getOrderAsObservableListFromDeliverer(Deliverer deliverer) {
        List<UniqueId> ids = deliverer.getOrders();
        List<Order> filteredOrders = model.getFilteredOrderList();
        List<Order> ordersFromDeliverer = filteredOrders.stream()
                .filter(order -> ids.stream()
                        .anyMatch(order::hasId))
                .collect(Collectors.toList());

        return FXCollections.observableList(ordersFromDeliverer);
    }

    @Override
    public ObservableList<Pet> getPetAsObservableListFromSupplier(Supplier supplier) {
        List<UniqueId> ids = supplier.getPetIds();
        List<Pet> filteredPets = model.getFilteredPetList();
        List<Pet> petsFromSupplier = filteredPets.stream()
                .filter(pet -> ids.stream()
                        .anyMatch(pet::hasId))
                .collect(Collectors.toList());
        return FXCollections.observableList(petsFromSupplier);
    }

    @Override
    public void switchToBuyer() {
        model.switchToBuyerList();
    }

    @Override
    public void switchToSupplier() {
        model.switchToSupplierList();
    }
}
