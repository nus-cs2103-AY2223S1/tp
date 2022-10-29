package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonMatchesPredicateBuilder;
import seedu.address.testutil.StudentBuilder;

public class PersonMatchesPredicateTest {

    @Test
    public void equals() {
        PersonMatchesPredicate firstPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name1")).withEmailsList(Collections.singletonList("email1"))
                .withGenderList(Collections.singletonList("gender1")).withLocationsList(Collections.singletonList("location1"))
                .withModulesList(Collections.singleton("module1"), true).withPhonesList(Collections.singletonList("111"))
                .withOfficeHoursList(Collections.singletonList("officeHour1")).withRatingsList(Collections.singletonList("1"))
                .withSpecList(Collections.singletonList("spec1")).withTypesList(Collections.singletonList("type1"))
                .withTagsList(Collections.singleton("tag1"), true).withYearsList(Collections.singletonList("year1"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        PersonMatchesPredicate secondPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name2")).withEmailsList(Collections.singletonList("email2"))
                .withGenderList(Collections.singletonList("gender2")).withLocationsList(Collections.singletonList("location2"))
                .withModulesList(Collections.singleton("module2"), true).withPhonesList(Collections.singletonList("222"))
                .withOfficeHoursList(Collections.singletonList("officeHour2")).withRatingsList(Collections.singletonList("2"))
                .withSpecList(Collections.singletonList("spec2")).withTypesList(Collections.singletonList("type2"))
                .withTagsList(Collections.singleton("tag2"), true).withYearsList(Collections.singletonList("year2"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        PersonMatchesPredicate firstPredicateCopy = new PersonMatchesPredicateBuilder(firstPredicate).build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One names
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple names
        predicate.setNamesList(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching names
        predicate.setNamesList(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case names
        predicate.setNamesList(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setNamesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate.setNamesList(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate.setNamesList(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
            .withEmail("alice@email.com").withGender("F").build()));
    }
}
