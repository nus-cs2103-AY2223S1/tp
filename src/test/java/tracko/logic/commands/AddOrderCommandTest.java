package tracko.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
// import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.commands.order.AddOrderCommand;
import tracko.model.Model;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.TrackO;
import tracko.model.items.Item;
import tracko.model.order.Order;
// import tracko.model.person.Person;
import tracko.testutil.OrderBuilder;


public class AddOrderCommandTest {

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        AddOrderCommand command = new AddOrderCommand(validOrder);
        command.setAwaitingInput(false);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.ordersAdded);
    }

    // @Test
    // public void execute_duplicatePerson_throwsCommandException() {
    //     Person validPerson = new OrderBuilder().build();
    //     AddOrderCommand addOrderCommand = new AddOrderCommand(validPerson);
    //     ModelStub modelStub = new ModeulStubWithOrder(validPerson);
    //     assertThrows(CommandException.class, AddOrderCommand.MESSAGE_DUPLICATE_PERSON,
    //         () -> addOrderCommand.execute(modelStub));
    // }

    @Test
    public void equals() {
        Order alice = new OrderBuilder().withName("Alice").build();
        Order bob = new OrderBuilder().withName("Bob").build();
        AddOrderCommand addAliceCommand = new AddOrderCommand(alice);
        AddOrderCommand addBobCommand = new AddOrderCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddOrderCommand addAliceCommandCopy = new AddOrderCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public Path getTrackOFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackOFilePath(Path trackOFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredItemListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getInventoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackO(ReadOnlyTrackO newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    // /**
    //  * A Model stub that contains a single person.
    //  */
    // private class ModeulStubWithOrder extends ModelStub {
    //     private final Order order;
    //
    //     ModeulStubWithOrder(Order order) {
    //         requireNonNull(order);
    //         this.order = order;
    //     }
    //
    //     @Override
    //     public boolean hasPerson(Person person) {
    //         requireNonNull(person);
    //         return this.person.isSamePerson(person);
    //     }
    // }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<Order> ordersAdded = new ArrayList<>();

        // @Override
        // public boolean hasPerson(Person person) {
        //     requireNonNull(person);
        //     return personsAdded.stream().anyMatch(person::isSamePerson);
        // }

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            return new TrackO();
        }
    }

}
