package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalMassLinkers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ModAddCommandTest {

    private static final Mod VALID_MOD_CS2100 = new Mod("CS2100", false);
    private static final Mod VALID_MOD_CS2101 = new Mod("CS2101", false);
    private static Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    }

    /**
     * Test constructor to throw an exception when null index is provided.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Test constructor to throw an exception when null mods is provided.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(INDEX_FIRST_PERSON, null));
    }

    /**
     * Test the {@code execute} method to add mods to a person.
     */
    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test person to model
        Person toAdd = new PersonBuilder(BOB).withMods(VALID_MOD_CS2100.getModName()).build();
        model.addPerson(toAdd);

        // execute ModAddCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModAddCommand commandToExecute = new ModAddCommand(indexLastPerson,
                FXCollections.singletonObservableList(VALID_MOD_CS2101));
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from ModAddCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName(), VALID_MOD_CS2101.getModName())
                .build();

        assertEquals(String.format(ModAddCommand.MESSAGE_SUCCESS, editedPerson), commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Test the {@code execute} method to add a pre-existing mod.
     */
    @Test
    public void execute_saveDuplicateMods_success() throws CommandException {

        // adds a test person to model
        Person toAdd = new PersonBuilder(BOB).withMods(VALID_MOD_CS2100.getModName()).build();
        model.addPerson(toAdd);

        // execute ModAddCommand on the test person with existing mod
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModAddCommand commandToExecute = new ModAddCommand(indexLastPerson,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from ModAddCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName())
                .build();

        assertEquals(String.format(ModAddCommand.MESSAGE_SUCCESS, editedPerson), commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Test the {@code execute} method to throw an exception when index provided
     * is out of bounds.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ModAddCommand invalidCommand = new ModAddCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
