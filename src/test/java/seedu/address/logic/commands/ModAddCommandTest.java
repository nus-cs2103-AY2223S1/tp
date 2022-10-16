package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(null, FXCollections.observableArrayList()));
    }

    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test person to model
        Person toAdd = new PersonBuilder(BOB).withMods(VALID_MOD_CS2100.modName).build();
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
                .withMods(VALID_MOD_CS2100.modName, VALID_MOD_CS2101.modName)
                .build();

        assertEquals(String.format(ModAddCommand.MESSAGE_SUCCESS, editedPerson), commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ModAddCommand invalidCommand = new ModAddCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
