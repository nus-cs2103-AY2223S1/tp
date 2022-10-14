package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.ui.GuiTab;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCustomer(Customer target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        throw new AssertionError("This method should not be called.");
    }

    public void selectCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public FilteredList<Commission> getFilteredCommissionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableValue<FilteredList<Commission>> getObservableFilteredCommissionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCommissionList(Predicate<Commission> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSelectedCustomer() {
        throw new AssertionError("This method should not be called.");
    }

    public ObservableObject<Customer> getSelectedCustomer() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSelectedCommission() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void selectTab(GuiTab tab) {
    }

    @Override
    public GuiTab getSelectedTab() {
        return null;
    }

    @Override
    public ObservableObject<Commission> getSelectedCommission() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void selectCommission(Commission commission) {
        throw new AssertionError("This method should not be called.");
    }
}
