package seedu.masslinkers.model.student;
//@author jonasgwt
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.testutil.StudentBuilder;

public class ModContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModContainsKeywordsPredicate firstPredicate = new ModContainsKeywordsPredicate(firstPredicateKeywordList);
        ModContainsKeywordsPredicate secondPredicate = new ModContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModContainsKeywordsPredicate firstPredicateCopy = new ModContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_modContainsKeywords_returnsTrue() {
        // One keyword
        ModContainsKeywordsPredicate predicate = new ModContainsKeywordsPredicate(Collections.singletonList("cs2109s"));
        assertTrue(predicate.test(new StudentBuilder().withName("Jonas Boyd").withMods("CS2109S").build()));

        // Multiple keywords
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("ma2001", "ma1521"));
        assertTrue(predicate.test(new StudentBuilder().withName("Wang Heng Huat Fei").withMods("MA1521", "MA2001",
                "GEA1000").build()));

        // Satisfies all keywords
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("ma1521", "cs2030s", "cs2100"));
        assertTrue(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));
    }

    @Test
    public void test_modDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        ModContainsKeywordsPredicate predicate = new ModContainsKeywordsPredicate(Arrays.asList("ma1521", "cs3230"));
        assertFalse(predicate.test(new StudentBuilder().withName("Chan Huat Heng").withMods("MA1521")
                .build()));

        // Zero keywords
        predicate = new ModContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));

        // Non-matching keyword
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("cs1101s"));
        assertFalse(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));

        // Array containing no matching keyword
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("cs2040s"));
        assertFalse(predicate.test(new StudentBuilder().withName("Jonas Ooi").withPhone("9996969")
                .withEmail("jonooi@hotmail.com").withTelegram("jonasg")
                .withGitHub("handsomelad").withMods("CS2030S").build()));

        // Student with no module
        predicate = new ModContainsKeywordsPredicate(Arrays.asList("is1103"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("lalaland")
                .withGitHub("AliceInTheWonderLand").build()));
    }
}
