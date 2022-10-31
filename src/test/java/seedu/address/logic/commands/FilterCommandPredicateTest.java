package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommandPredicateTest {

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
        FilterCommandPredicate secondPredicate = new FilterCommandPredicate(
                Set.of(firstNamePredicate, secondNamePredicate),
                Set.of(firstTagPredicate, secondTagPredicate));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same value -> returns true
        assertTrue(firstPredicate
                .equals(new FilterCommandPredicate(firstNamePredicate, firstTagPredicate)));

        // same set -> returns true
        assertTrue(secondPredicate.equals(new FilterCommandPredicate(
                Set.of(firstNamePredicate, secondNamePredicate),
                Set.of(firstTagPredicate, secondTagPredicate))));

        // same name no tag -> returns true
        assertTrue(new FilterCommandPredicate(firstNamePredicate, null)
                .equals(new FilterCommandPredicate(firstNamePredicate, null)));

        // same tag no name -> returns true
        assertTrue(new FilterCommandPredicate(null, firstTagPredicate)
                .equals(new FilterCommandPredicate(null, firstTagPredicate)));

        // different type -> returns false
        assertFalse(firstPredicate.equals(5));

        // different name -> returns false
        assertFalse(firstPredicate
                .equals(new FilterCommandPredicate(secondNamePredicate, firstTagPredicate)));

        // different tag -> returns false
        assertFalse(firstPredicate
                .equals(new FilterCommandPredicate(firstNamePredicate, secondTagPredicate)));

        // different set -> return false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
