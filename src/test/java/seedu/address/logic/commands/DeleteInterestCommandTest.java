package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOB_WITHOUT_INTERESTS;
import static seedu.address.testutil.TypicalPersons.BOB_WITHOUT_TENNIS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interest.Interest;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeleteInterestCommandTest {
    private static final Interest INTEREST_TENNIS = new Interest("tennis");
    private static final Interest INTEREST_NETFLIX = new Interest("netflix");
    private static final Interest INTEREST_ANIME = new Interest("anime");

    private static Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    /**
     * Test constructor to throw an exception when null index is provided.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterestCommand(null,
                new HashSet<>()));
    }

    /**
     * Test constructor to throw an exception when null interest is provided.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterestCommand(INDEX_FIRST_PERSON, null));
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete 1 existing interest
     * from a batchmate.
     */
    @Test
    public void execute_deleteOneInterest_success() throws CommandException {

        // adds a test person to model (Bob has interests: tennis, netflix)
        Person toAdd = new PersonBuilder(BOB).build();
        model.addPerson(toAdd);

        // execute DeleteInterestCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastPerson,
                interestsToBeDeleted);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from the model after executing DeleteInterestCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB_WITHOUT_TENNIS).build();

        assertEquals(String.format(DeleteInterestCommand.MESSAGE_SUCCESS, editedPerson),
                commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete multiple existing interests
     * from a batchmate.
     */
    @Test
    public void execute_deleteMultipleInterests_success() throws CommandException {

        // adds a test person to model (Bob has interests: tennis, netflix)
        Person toAdd = new PersonBuilder(BOB).build();
        model.addPerson(toAdd);

        // execute DeleteInterestCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        interestsToBeDeleted.add(INTEREST_NETFLIX);

        DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastPerson,
                interestsToBeDeleted);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from the model after executing DeleteInterestCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB_WITHOUT_INTERESTS).build();

        assertEquals(String.format(DeleteInterestCommand.MESSAGE_SUCCESS, editedPerson),
                commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete 1 non-existing interest
     * from a batchmate.
     */
    @Test
    public void execute_delete1NonExistingInterest_throwsCommandException() {

        // adds a test person to model (Bob has interests: tennis, netflix)
        Person toAdd = new PersonBuilder(BOB).build();
        model.addPerson(toAdd);

        // execute DeleteInterestCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_ANIME);

        try {
            DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastPerson,
                    interestsToBeDeleted);
            commandToExecute.execute(model); // Should jump to catch block
            fail(); // Test should not reach this line
        } catch (CommandException e) {
            assertEquals(DeleteInterestCommand.MESSAGE_INVALID_INTEREST, e.getMessage());
        }
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete multiple interests containing
     * a mix of existing and non-existing interests from a batchmate.
     */
    @Test
    public void execute_deleteMixedInterests_throwsCommandException() {

        // adds a test person to model (Bob has interests: tennis, netflix)
        Person toAdd = new PersonBuilder(BOB).build();
        model.addPerson(toAdd);

        // execute DeleteInterestCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        interestsToBeDeleted.add(INTEREST_ANIME);

        try {
            DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastPerson,
                    interestsToBeDeleted);
            commandToExecute.execute(model); // Should jump to catch block
            fail(); // Test should not reach this line
        } catch (CommandException e) {
            assertEquals(DeleteInterestCommand.MESSAGE_INVALID_INTEREST, e.getMessage());
        }
    }

}
