package seedu.masslinkers.model.student;
//@author chm252
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.util.SampleDataUtil;
import seedu.masslinkers.testutil.StudentBuilder;

public class ModTakingContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModTakingContainsKeywordsPredicate firstPredicate = new ModTakingContainsKeywordsPredicate(
                firstPredicateKeywordList);
        ModTakingContainsKeywordsPredicate secondPredicate = new ModTakingContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModTakingContainsKeywordsPredicate firstPredicateCopy = new ModTakingContainsKeywordsPredicate(
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
    public void test_modTakingContainsKeywords_returnsTrue() {
        // One keyword
        ModTakingContainsKeywordsPredicate predicate = new ModTakingContainsKeywordsPredicate(
                Collections.singletonList("cs2109s"));
        Student jonasBoyd = new StudentBuilder().withName("Jonas Boyd").withMods("CS2109S").build();
        jonasBoyd.markMods(jonasBoyd.getMods());
        jonasBoyd.unmarkMods(jonasBoyd.getMods());
        assertTrue(predicate.test(jonasBoyd));

        // Multiple keywords and mods unmarked by default
        predicate = new ModTakingContainsKeywordsPredicate(Arrays.asList("ma2001", "ma1521"));
        Student wangHengHuat = new StudentBuilder().withName("Wang Heng Huat").withMods("MA2001", "MA1521").build();
        assertTrue(predicate.test(wangHengHuat));

        // Satisfies all keywords and mods unmarked by default
        predicate = new ModTakingContainsKeywordsPredicate(Arrays.asList("ma1521", "cs2030s", "cs2100"));
        Student weeMingQing = new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build();
        assertTrue(predicate.test(weeMingQing));
    }

    @Test
    public void test_modTakingDoesNotContainKeywords_returnsFalse() {
        // Mod taken
        Student chanHuatHeng = new StudentBuilder().withName("Chan Huat Heng").withMods("MA1521").build();
        chanHuatHeng.markMods(SampleDataUtil.getModSet("MA1521"));
        ModTakingContainsKeywordsPredicate predicate = new ModTakingContainsKeywordsPredicate(
                Arrays.asList("ma1521"));
        assertFalse(predicate.test(chanHuatHeng));

        // Zero keywords
        predicate = new ModTakingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build()));

        // Some mods have already been taken.
        Student weeMingQing = new StudentBuilder().withName("Wee Ming Qing").withMods("CS1231S", "CS2100",
                "MA1521", "CS2030S", "GEN2001").build();
        weeMingQing.markMods(SampleDataUtil.getModSet("CS1231S", "GEN2001"));
        predicate = new ModTakingContainsKeywordsPredicate(Arrays.asList("cs1231s", "cs2100", "gen2001"));
        assertFalse(predicate.test(weeMingQing));

        // Array containing no matching keyword
        predicate = new ModTakingContainsKeywordsPredicate(Arrays.asList("cs2040s"));
        assertFalse(predicate.test(new StudentBuilder().withName("Jonas Ooi").withPhone("9996969")
                .withEmail("jonooi@hotmail.com").withTelegram("jonasg")
                .withGitHub("handsomelad").withMods("CS2030S").build()));

        // Student with no module
        predicate = new ModTakingContainsKeywordsPredicate(Arrays.asList("CP2106"));
        assertFalse(predicate.test(new StudentBuilder().withName("Chia Yu Xuan").withPhone("12345")
                .withEmail("cyx1909@ymail.com").withTelegram("cyx1909")
                .withGitHub("cyx1909").build()));
    }
}
