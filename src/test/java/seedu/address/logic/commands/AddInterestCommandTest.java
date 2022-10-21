package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interest.Interest;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddInterestCommandTest {
    private static final Interest VALID_INTEREST_TENNIS = new Interest("tennis");
    private static final Interest VALID_INTEREST_ANIME = new Interest("anime");
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
     * Test the {@code execute} method to add interests to a person.
     */
    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test person to model
        Person toAdd = new PersonBuilder(BOB).withInterests(VALID_INTEREST_TENNIS.interestName).build();
        model.addPerson(toAdd);

        // execute AddInterestCommand on the test person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_TENNIS);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastPerson,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from AddInterestCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB)
                .withInterests(VALID_INTEREST_TENNIS.interestName, VALID_INTEREST_TENNIS.interestName)
                .build();

        assertEquals(String.format(AddInterestCommand.MESSAGE_SUCCESS, editedPerson),
                commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Test the {@code execute} method to add a pre-existing interest.
     */
    @Test
    public void execute_saveDuplicateInterests_success() throws CommandException {

        // adds a test person to model
        Person toAdd = new PersonBuilder(BOB).withInterests(VALID_INTEREST_ANIME.interestName).build();
        model.addPerson(toAdd);

        // execute AddInterestCommand on the test person with existing mod
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_ANIME);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastPerson,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited person from AddInterestCommand
        Person editedPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // expected edited person
        Person editedPersonExpected = new PersonBuilder(BOB)
                .withInterests(VALID_INTEREST_ANIME.interestName)
                .build();

        assertEquals(String.format(AddInterestCommand.MESSAGE_SUCCESS, editedPerson),
                commandResult.getFeedbackToUser());
        assertEquals(editedPerson, editedPersonExpected);
    }

    /**
     * Test the {@code execute} method to throw an exception when index provided
     * is out of bounds.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_ANIME);
        AddInterestCommand invalidCommand = new AddInterestCommand(indexOutOfBounds,
                currentInterestSet);
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
