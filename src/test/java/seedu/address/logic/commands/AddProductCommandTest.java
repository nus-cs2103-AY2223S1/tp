package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;
import seedu.address.testutil.ProductBuilder;


public class AddProductCommandTest {

    // EP: null, product not already in model, product already in model.
    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProductCommand(null));
    }

    @Test
    public void execute_productAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProductAdded modelStub = new ModelStubAcceptingProductAdded();
        Product validProduct = new ProductBuilder().build();

        CommandResult commandResult = new AddProductCommand(validProduct).execute(modelStub);

        assertEquals(String.format(AddProductCommand.MESSAGE_SUCCESS, validProduct), commandResult.getFeedbackToUser());
        assertEquals(CommandSpecific.PRODUCT, commandResult.getCommandSpecific());
        assertEquals(Arrays.asList(validProduct), modelStub.productsAdded);
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        Product validProduct = new ProductBuilder().build();
        AddProductCommand addProductCommand = new AddProductCommand(validProduct);
        ModelStub modelStub = new ModelStubWithProduct(validProduct);

        assertThrows(CommandException.class,
                AddProductCommand.MESSAGE_DUPLICATE_PRODUCT, () -> addProductCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Product product1 = new ProductBuilder().withProductName("Product1").build();
        Product product2 = new ProductBuilder().withProductName("Product2").build();
        AddProductCommand addProduct1Command = new AddProductCommand(product1);
        AddProductCommand addProduct2Command = new AddProductCommand(product2);

        // same object -> returns true
        assertTrue(product1.equals(product1));

        // same values -> returns true
        AddProductCommand addProduct1CommandCopy = new AddProductCommand(product1);
        assertTrue(addProduct1Command.equals(addProduct1CommandCopy));

        // different types -> returns false
        assertFalse(addProduct1Command.equals(1));

        // null -> returns false
        assertFalse(addProduct1Command.equals(null));

        // different Product -> returns false
        assertFalse(addProduct1Command.equals(addProduct2Command));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public Path getMyInsuRecFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyInsuRecFilePath(Path myInsuRecFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyInsuRec(ReadOnlyMyInsuRec myInsuRec) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMyInsuRec getMyInsuRec() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {

        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSpecificMeeting(Meeting meeting) {
            return false;
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getDetailedClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDetailedClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getDetailedMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDetailedMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Product.
     */
    private class ModelStubWithProduct extends ModelStub {
        private final Product product;

        ModelStubWithProduct(Product product) {
            requireNonNull(product);
            this.product = product;
        }

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return this.product.equals(product);
        }
    }

    /**
     * A Model stub that always accept the Product being added.
     */
    private class ModelStubAcceptingProductAdded extends ModelStub {
        final ArrayList<Product> productsAdded = new ArrayList<>();

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return productsAdded.stream().anyMatch(product::equals);
        }

        @Override
        public void addProduct(Product product) {
            requireNonNull(product);
            productsAdded.add(product);
        }

        @Override
        public ReadOnlyMyInsuRec getMyInsuRec() {
            return new MyInsuRec();
        }
    }

}
