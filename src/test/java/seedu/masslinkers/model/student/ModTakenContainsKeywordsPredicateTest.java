package seedu.masslinkers.model.student;
//@@author chm252
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.util.SampleDataUtil;
import seedu.masslinkers.testutil.StudentBuilder;

public class ModTakenContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModTakenContainsKeywordsPredicate firstPredicate = new ModTakenContainsKeywordsPredicate(
                firstPredicateKeywordList);
        ModTakenContainsKeywordsPredicate secondPredicate = new ModTakenContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModTakenContainsKeywordsPredicate firstPredicateCopy = new ModTakenContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_modTakenContainsKeywords_returnsTrue() {
        // One keyword
        ModTakenContainsKeywordsPredicate predicate = new ModTakenContainsKeywordsPredicate(
                Collections.singletonList("cs2109s"));
        Student jonasBoyd = new StudentBuilder().withName("Jonas Boyd").withMods("CS2109S").build();
        jonasBoyd.markMods(jonasBoyd.getMods());
        assertTrue(predicate.test(jonasBoyd));

        // Multiple keywords
        predicate = new ModTakenContainsKeywordsPredicate(Arrays.asList("ma2001", "ma1521"));
        Student wangHengHuat = new StudentBuilder().withName("Wang Heng Huat").withMods("MA2001", "MA1521").build();
        wangHengHuat.markMods(SampleDataUtil.getModSet("MA1521", "MA2001"));
        assertTrue(predicate.test(wangHengHuat));

        // Satisfies all keywords
        predicate = new ModTakenContainsKeywordsPredicate(Arrays.asList("ma1521", "cs2030s", "cs2100"));
        Student weeMingQing = new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build();
        weeMingQing.markMods(SampleDataUtil.getModSet("MA1521", "CS2030S", "CS2100"));
        assertTrue(predicate.test(weeMingQing));
    }

    @Test
    public void test_modTakenDoesNotContainKeywords_returnsFalse() {
        // Mod not taken
        ModTakenContainsKeywordsPredicate predicate = new ModTakenContainsKeywordsPredicate(
                Arrays.asList("ma1521"));
        assertFalse(predicate.test(new StudentBuilder().withName("Chan Huat Heng").withMods("MA1521")
                .build()));

        // Zero keywords
        predicate = new ModTakenContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));

        // Has not taken all the specified mods by default
        predicate = new ModTakenContainsKeywordsPredicate(Arrays.asList("cs1231s", "cs2100"));
        assertFalse(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));

        // Array containing no matching keyword
        predicate = new ModTakenContainsKeywordsPredicate(Arrays.asList("cs2040s"));
        assertFalse(predicate.test(new StudentBuilder().withName("Jonas Ooi").withPhone("9996969")
                .withEmail("jonooi@hotmail.com").withTelegram("jonasg")
                .withGitHub("handsomelad").withMods("CS2030S").build()));

        // Student with no module
        predicate = new ModTakenContainsKeywordsPredicate(Arrays.asList("CP2106"));
        assertFalse(predicate.test(new StudentBuilder().withName("Chia Yu Xuan").withPhone("12345")
                .withEmail("cyx1909@ymail.com").withTelegram("cyx1909")
                .withGitHub("cyx1909").build()));
    }
}
