package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {
    private static final String COMMA = "\\s*,\\s*";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate("second");

        TagMatchesQueryPredicate firstTagPredicate =
                new TagMatchesQueryPredicate(new Tag("friend"));
        TagMatchesQueryPredicate secondTagPredicate =
                new TagMatchesQueryPredicate(new Tag("neighbor"));

        FilterCommandPredicate firstPredicate =
                new FilterCommandPredicate(firstNamePredicate, firstTagPredicate);
        FilterCommandPredicate secondPredicate =
                new FilterCommandPredicate(secondNamePredicate, secondTagPredicate);

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nullPredicate_throwsNullExceptionError() {
        assertThrows(NullPointerException.class, () -> new FilterCommand(null));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        Set<NameContainsKeywordsPredicate> namePredicate = prepareNamePredicate("bbbxyz");
        FilterCommandPredicate predicate = new FilterCommandPredicate(namePredicate, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.addNewFilterToFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Set<NameContainsKeywordsPredicate> namePredicates = prepareNamePredicate("Alice,Elle,Kunz");
        Set<TagMatchesQueryPredicate> tagPredicates = prepareTagPredicate("owesMoney, friends");

        FilterCommandPredicate predicate1 = new FilterCommandPredicate(namePredicates, null);
        FilterCommand command1 = new FilterCommand(predicate1);
        String expectedMessage1 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.addNewFilterToFilteredPersonList(predicate1);
        assertCommandSuccess(command1, model, expectedMessage1, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredPersonList());

        expectedModel.clearFiltersInFilteredPersonList();
        model.clearFiltersInFilteredPersonList();

        FilterCommandPredicate predicate2 = new FilterCommandPredicate(null, tagPredicates);
        FilterCommand command2 = new FilterCommand(predicate2);
        String expectedMessage2 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.addNewFilterToFilteredPersonList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());

        expectedModel.clearFiltersInFilteredPersonList();
        model.clearFiltersInFilteredPersonList();

        FilterCommandPredicate predicate3 = new FilterCommandPredicate(namePredicates, tagPredicates);
        FilterCommand command3 = new FilterCommand(predicate3);
        String expectedMessage3 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.addNewFilterToFilteredPersonList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a set of {@code NameContainsKeywordsPredicate}.
     */
    private Set<NameContainsKeywordsPredicate> prepareNamePredicate(String userInput) {
        return Stream.of(userInput.split(COMMA)).map(name -> new NameContainsKeywordsPredicate(name))
                .collect(Collectors.toSet());
    }

    /**
     * Parses {@code userInput} into a set of {@code TagMatchesQueryPredicate}.
     */
    private Set<TagMatchesQueryPredicate> prepareTagPredicate(String userInput) {
        return Stream.of(userInput.split(COMMA)).map(input -> new Tag(input))
                .map(tag -> new TagMatchesQueryPredicate(tag)).collect(Collectors.toSet());
    }
}
