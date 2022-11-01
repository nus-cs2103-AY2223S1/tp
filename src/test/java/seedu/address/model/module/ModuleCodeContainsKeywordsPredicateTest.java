package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TaskBuilder;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModuleCodeContainsKeywordsPredicateTest {

    @Test
    public void test_modCodeContainsKeywords_returnsTrue() {
        ModuleCodeContainsKeywordsPredicate predicate = new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("cs2030s"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("cs2030s").build()));

        predicate = new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("cs2030s"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("cS2030S").build()));

        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("20"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("Cs2030S").build()));

        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("30s"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2030S").build()));
    }

    @Test
    public void test_modCodeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleCodeContainsKeywordsPredicate predicate = new ModuleCodeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("cs2030s").build()));

        // Non-matching keyword
        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("cs2030s"));
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("cs2040s").build()));

    }
}
