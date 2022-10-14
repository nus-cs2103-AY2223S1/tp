package seedu.address.logic;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMMISSIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ObservableObject;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

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
        commandResult = command.execute(model, storage);
        selectValidCustomer();
        selectValidCommission();

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
    public ObservableList<Customer> getFilteredCustomerList() {
        return model.getFilteredCustomerList();
    }


    @Override
    public ObservableValue<FilteredList<Commission>> getObservableFilteredCommissionList() {
        return model.getObservableFilteredCommissionList();
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
    public ObservableObject<Customer> getSelectedCustomer() {
        return model.getSelectedCustomer();
    }

    @Override
    public void selectCustomer(Customer customer) {
        model.selectCustomer(customer);
    }

    @Override
    public ObservableObject<Commission> getSelectedCommission() {
        return model.getSelectedCommission();
    }

    @Override
    public void selectCommission(Commission commission) {
        model.selectCommission(commission);
    }

    @Override
    public void selectValidCustomer() {
        if (!model.getFilteredCustomerList().contains(model.getSelectedCustomer().getValue())) {
            model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
            List<Customer> customers = model.getFilteredCustomerList();
            model.selectCustomer(customers.size() > 0 ? customers.get(0) : null);
        }
    }

    @Override
    public void selectValidCommission() {
        if (!model.getFilteredCommissionList().contains(model.getSelectedCommission().getValue())) {
            model.updateFilteredCommissionList(PREDICATE_SHOW_ALL_COMMISSIONS);
            List<Commission> commissions = model.getFilteredCommissionList();
            model.selectCommission(commissions.size() > 0 ? commissions.get(0) : null);
        }
    }

    @Override
    public GuiTab getSelectedTab() {
        return model.getSelectedTab();
    }
}
