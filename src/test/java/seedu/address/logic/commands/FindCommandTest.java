package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.DANIEL;
import static seedu.address.testutil.TypicalCustomers.ELLE;
import static seedu.address.testutil.TypicalCustomers.FIONA;
import static seedu.address.testutil.TypicalCustomers.MONA;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompositeCustomerPredicate firstPredicate =
                new CompositeCustomerPredicate(Collections.singleton("first"),
                        Collections.singleton(new Tag("fate")),
                        Collections.singleton(new Tag("hammer")));
        CompositeCustomerPredicate secondPredicate =
                new CompositeCustomerPredicate(Collections.singleton("second"),
                        Collections.singleton(new Tag("hell")),
                        Collections.singleton(new Tag("scream")));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different  -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noCustomerFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        String[] foreignKeywords = new String[]{"joseph", "matthew"};
        Tag[] foreignMustTags = new Tag[]{new Tag("friends"), new Tag("loyal")};
        Tag[] foreignAnyTags = new Tag[]{new Tag("demanding"), new Tag("favorite")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // No customer contains any of the given keywords
        CompositeCustomerPredicate predicate = preparePredicate(foreignKeywords, emptyTags, emptyTags);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        // No customer contains all the mustTags
        predicate = preparePredicate(emptyKeywords, foreignMustTags, emptyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        // No customer contains any of the optionalTags
        predicate = preparePredicate(emptyKeywords, emptyTags, foreignAnyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        // No customer meets all three composite requirements
        predicate = preparePredicate(foreignKeywords, foreignMustTags, foreignAnyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredCustomerList());
    }

    @Test
    public void execute_customersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        String[] keywords = new String[]{"Daniel", "Elle", "Kunz"};
        Tag[] mustTags = new Tag[]{new Tag("friends"), new Tag("owesMoney")};
        Tag[] anyTags = new Tag[]{new Tag("rich"), new Tag("artist"), new Tag("vip")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // Multiple customers contain at least one of the given keyword
        CompositeCustomerPredicate predicate = preparePredicate(keywords, emptyTags, emptyTags);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        // Multiple customers contain all the must-have tags
        predicate = preparePredicate(emptyKeywords, mustTags, emptyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        // Multiple customers contain at least one of the optional tags
        predicate = preparePredicate(emptyKeywords, emptyTags, anyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, FIONA, MONA), model.getSortedFilteredCustomerList());

        expectedModel.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 1);
        // One customer fits all three composite criteria
        predicate = preparePredicate(keywords, mustTags, anyTags);
        command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getSortedFilteredCustomerList());
    }

    /**
     * Combines keywords, must-have tags and optional tags into a {@code CompositeCustomerPredicate}.
     */
    public static CompositeCustomerPredicate preparePredicate(String[] keywords, Tag[] mustTags, Tag[] optionalTags) {
        return new CompositeCustomerPredicate(new HashSet<>(Arrays.asList(keywords)),
                new HashSet<>(Arrays.asList(mustTags)), new HashSet<>(Arrays.asList(optionalTags)));
    }
}
