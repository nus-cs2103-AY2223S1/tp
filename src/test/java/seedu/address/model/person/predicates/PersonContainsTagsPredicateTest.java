package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PredicateGeneratorUtil;

class PersonContainsTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstTagList = Collections.singletonList("Tag1");
        List<String> secondTagList = Arrays.asList("Tag1", "Tag2");

        PersonContainsTagsPredicate firstPredicate = new PersonContainsTagsPredicate(firstTagList);
        PersonContainsTagsPredicate firstPredicateCopy = new PersonContainsTagsPredicate(firstTagList);
        PersonContainsTagsPredicate secondPredicate = new PersonContainsTagsPredicate(secondTagList);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_personTagsMatchesSearchTags_returnsTrue() {
        Person personToTest = generatePersonWithTags("Tag3", "Tag4", "Tag5");

        // Same tags
        PersonContainsTagsPredicate predicate =
                PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag3", "Tag4", "Tag5");
        assertTrue(predicate.test(personToTest));

        // Matches all search tags
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag3", "Tag4");
        assertTrue(predicate.test(personToTest));

        // Mixed-case works
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("TAG3", "tAg4");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personTagsNotMatchesSearchTags_returnsFalse() {
        Person personToTest = generatePersonWithTags("Tag6", "Tag7", "Tag8");

        // Non-matching tag
        PersonContainsTagsPredicate predicate =
                PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Hi");
        assertFalse(predicate.test(personToTest));

        // Different tag
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag");
        assertFalse(predicate.test(personToTest));

        // Different tag
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag60");
        assertFalse(predicate.test(personToTest));

        // Some tags not match
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag6", "Wrong", "Tag8");
        assertFalse(predicate.test(personToTest));

        // Keywords match name and address, but does not match tag
        predicate = PredicateGeneratorUtil.generatePersonsContainsTagsPredicate("Tag");
        assertFalse(predicate.test(new PersonBuilder().withName("Tag").withEmail("Tag@gmail.com")
                .withAddress("Tag").withTags("SomethingElse").build()));
    }

    private Person generatePersonWithTags(String ... tagString) {
        return new PersonBuilder().withTags(tagString).build();
    }
}