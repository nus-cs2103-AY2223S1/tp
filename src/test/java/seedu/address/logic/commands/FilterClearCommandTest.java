package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
public class FilterClearCommandTest {
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

        FilterClearCommand filterFirstCommand = new FilterClearCommand(firstPredicate);
        FilterClearCommand filterSecondCommand = new FilterClearCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterClearCommand filterFirstCommandCopy = new FilterClearCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));

        // both null -> returns true
        assertTrue(new FilterClearCommand().equals(new FilterClearCommand()));
    }

    @Test
    public void execute_nullPredicate_throwsNullExceptionError() {
        assertThrows(NullPointerException.class, () -> new FilterClearCommand(null));
    }

    @Test
    public void execute_filterApplied_clearFilter() {

        // clear name predicate
        Set<NameContainsKeywordsPredicate> namePredicates = prepareNamePredicate("Kurz,Elle,Kunz");
        FilterCommandPredicate predicate1 = new FilterCommandPredicate(namePredicates, null);
        FilterClearCommand command1 = new FilterClearCommand(predicate1);
        String expectedMessage1 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        model.removeFilterFromFilteredPersonList(predicate1);
        assertCommandSuccess(command1, model, expectedMessage1, expectedModel);
        assertEquals(getTypicalAddressBook().getPersonList(), model.getFilteredPersonList());

        // clear tag predicate
        Set<TagMatchesQueryPredicate> tagPredicates = prepareTagPredicate("owesMoney, friends");
        FilterCommandPredicate predicate2 = new FilterCommandPredicate(null, tagPredicates);
        FilterClearCommand command2 = new FilterClearCommand(predicate2);
        String expectedMessage2 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        model.removeFilterFromFilteredPersonList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(getTypicalAddressBook().getPersonList(), model.getFilteredPersonList());

        // two filters applied
        // clear name and tag predicate
        FilterCommandPredicate predicate3 = new FilterCommandPredicate(namePredicates, tagPredicates);
        FilterClearCommand command3 = new FilterClearCommand(predicate3);
        String expectedMessage3 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        model.removeFilterFromFilteredPersonList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(getTypicalAddressBook().getPersonList(), model.getFilteredPersonList());
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
