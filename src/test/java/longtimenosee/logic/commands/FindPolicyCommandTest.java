package longtimenosee.logic.commands;

import static longtimenosee.commons.core.Messages.MESSAGE_POLICIES_LISTED_OVERVIEW;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalPolicies.FLEXI;
import static longtimenosee.testutil.TypicalPolicies.INVEST_READY;
import static longtimenosee.testutil.TypicalPolicies.PRUSHIELD;
import static longtimenosee.testutil.TypicalPolicies.TERMLIFE;
import static longtimenosee.testutil.TypicalPolicies.WEALTH;
import static longtimenosee.testutil.TypicalPolicies.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.predicate.CompanyMatchesInputPredicate;
import longtimenosee.model.policy.predicate.CoverageMatchesInputPredicate;
import longtimenosee.model.policy.predicate.TitleContainsKeywordsPredicate;

public class FindPolicyCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyMatchesInputPredicate firstPredicate = new CompanyMatchesInputPredicate("AIA");
        CoverageMatchesInputPredicate secondPredicate = new CoverageMatchesInputPredicate(List.of("LIFE", "HEALTH"));
        TitleContainsKeywordsPredicate thirdPredicate = new TitleContainsKeywordsPredicate(List.of("Health", "Plan"));
        CompanyMatchesInputPredicate fourthPredicate = new CompanyMatchesInputPredicate("PRU");
        CoverageMatchesInputPredicate fifthPredicate = new CoverageMatchesInputPredicate(List.of("MOTOR"));
        TitleContainsKeywordsPredicate sixthPredicate = new TitleContainsKeywordsPredicate(List.of("shield"));

        FindPolicyCommand firstFindPolicyCommand =
                new FindPolicyCommand(List.of(firstPredicate, secondPredicate, thirdPredicate));
        FindPolicyCommand secondFindPolicyCommand =
                new FindPolicyCommand(List.of(fourthPredicate, fifthPredicate, sixthPredicate));

        // EP: null value
        assertFalse(firstFindPolicyCommand.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstFindPolicyCommand.equals(firstFindPolicyCommand));

        // EP: same internal predicates
        FindPolicyCommand firstFindPolicyCommandCopy =
                new FindPolicyCommand(List.of(firstPredicate, secondPredicate, thirdPredicate));
        assertTrue(firstFindPolicyCommand.equals(firstFindPolicyCommandCopy));

        // EP: different internal predicates
        assertFalse(firstFindPolicyCommand.equals(secondFindPolicyCommand));
    }

    @Test
    public void execute_singlePredicate_noPoliciesFound() {
        String expectedMessage = String.format(MESSAGE_POLICIES_LISTED_OVERVIEW, 0);

        // EP: Company Predicate
        CompanyMatchesInputPredicate companyPredicate = new CompanyMatchesInputPredicate("FWD");
        FindPolicyCommand findPolicyCommandWithCompanyPredicate = new FindPolicyCommand(List.of(companyPredicate));
        expectedModel.updateFilteredPolicyList(companyPredicate);
        assertCommandSuccess(findPolicyCommandWithCompanyPredicate, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPolicyList());

        // EP: Coverage Predicate
        CoverageMatchesInputPredicate coveragePredicate = new CoverageMatchesInputPredicate(List.of("MOTOR"));
        FindPolicyCommand findPolicyCommandWithCoveragePredicate = new FindPolicyCommand(List.of(coveragePredicate));
        expectedModel.updateFilteredPolicyList(coveragePredicate);
        assertCommandSuccess(findPolicyCommandWithCoveragePredicate, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPolicyList());

        // EP: Title Predicate
        TitleContainsKeywordsPredicate titlePredicate = new TitleContainsKeywordsPredicate(List.of("Plan"));
        FindPolicyCommand findPolicyCommandWithTitlePredicate = new FindPolicyCommand(List.of(coveragePredicate));
        expectedModel.updateFilteredPolicyList(titlePredicate);
        assertCommandSuccess(findPolicyCommandWithTitlePredicate, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPolicyList());
    }

    @Test
    public void execute_singlePredicate_onePolicyFound() {
        String expectedMessage = String.format(MESSAGE_POLICIES_LISTED_OVERVIEW, 1);

        // EP: Company Predicate
        CompanyMatchesInputPredicate companyPredicate = new CompanyMatchesInputPredicate("PRU");
        FindPolicyCommand findPolicyCommandWithCompanyPredicate = new FindPolicyCommand(List.of(companyPredicate));
        expectedModel.updateFilteredPolicyList(companyPredicate);
        assertCommandSuccess(findPolicyCommandWithCompanyPredicate, model, expectedMessage, expectedModel);
        assertEquals(List.of(PRUSHIELD), model.getFilteredPolicyList());

        // EP: Title Predicate
        TitleContainsKeywordsPredicate titlePredicate = new TitleContainsKeywordsPredicate(List.of("secure"));
        FindPolicyCommand findPolicyCommandWithTitlePredicate = new FindPolicyCommand(List.of(titlePredicate));
        expectedModel.updateFilteredPolicyList(titlePredicate);
        assertCommandSuccess(findPolicyCommandWithTitlePredicate, model, expectedMessage, expectedModel);
        assertEquals(List.of(FLEXI), model.getFilteredPolicyList());
    }

    @Test
    public void execute_singlePredicate_multiplePoliciesFound() {
        // EP: Coverage Predicate
        String secondExpectedMessage = String.format(MESSAGE_POLICIES_LISTED_OVERVIEW, 2);
        CoverageMatchesInputPredicate coveragePredicate = new CoverageMatchesInputPredicate(List.of("HEALTH", "LIFE"));
        FindPolicyCommand findPolicyCommandWithCoveragePredicate = new FindPolicyCommand(List.of(coveragePredicate));
        expectedModel.updateFilteredPolicyList(coveragePredicate);
        assertCommandSuccess(findPolicyCommandWithCoveragePredicate, model, secondExpectedMessage, expectedModel);
        assertEquals(List.of(PRUSHIELD, TERMLIFE), model.getFilteredPolicyList());

        // EP: Title Predicate
        String thirdExpectedMessage = String.format(MESSAGE_POLICIES_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate titlePredicate =
                new TitleContainsKeywordsPredicate(List.of("prushield", "wealth"));
        FindPolicyCommand findPolicyCommandWithTitlePredicate = new FindPolicyCommand(List.of(titlePredicate));
        expectedModel.updateFilteredPolicyList(titlePredicate);
        assertCommandSuccess(findPolicyCommandWithTitlePredicate, model, thirdExpectedMessage, expectedModel);
        assertEquals(List.of(PRUSHIELD, INVEST_READY, WEALTH), model.getFilteredPolicyList());
    }

    @Test
    public void execute_multiplePredicates_onePolicyFound() {
        String expectedMessage = String.format(MESSAGE_POLICIES_LISTED_OVERVIEW, 1);

        // EP: Company and Coverage Predicates
        CompanyMatchesInputPredicate firstCompanyPredicate = new CompanyMatchesInputPredicate("PRU");
        CoverageMatchesInputPredicate firstCoveragePredicate = new CoverageMatchesInputPredicate(List.of("LIFE"));
        FindPolicyCommand firstFindPolicyCommand =
                new FindPolicyCommand(List.of(firstCompanyPredicate, firstCoveragePredicate));
        Predicate<Policy> firstFinalPredicate = firstCompanyPredicate.and(firstCoveragePredicate);
        expectedModel.updateFilteredPolicyList(firstFinalPredicate);
        assertCommandSuccess(firstFindPolicyCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(PRUSHIELD), model.getFilteredPolicyList());

        // EP: Coverage and Title Predicates
        CoverageMatchesInputPredicate secondCoveragePredicate = new CoverageMatchesInputPredicate(List.of("HEALTH"));
        TitleContainsKeywordsPredicate secondTitlePredicate = new TitleContainsKeywordsPredicate(List.of("Solitaire"));
        FindPolicyCommand secondFindPolicyCommand =
                new FindPolicyCommand(List.of(secondCoveragePredicate, secondTitlePredicate));
        Predicate<Policy> secondFinalPredicate = secondCoveragePredicate.and(secondTitlePredicate);
        expectedModel.updateFilteredPolicyList(secondFinalPredicate);
        assertCommandSuccess(secondFindPolicyCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(TERMLIFE), model.getFilteredPolicyList());

        // EP: Title and Company Predicates
        TitleContainsKeywordsPredicate thirdTitlePredicate = new TitleContainsKeywordsPredicate(List.of("Ready"));
        CompanyMatchesInputPredicate thirdCompanyPredicate = new CompanyMatchesInputPredicate("MNF");
        FindPolicyCommand thirdFindPolicyCommand =
                new FindPolicyCommand(List.of(thirdTitlePredicate, thirdCompanyPredicate));
        Predicate<Policy> thirdFinalPredicate = thirdTitlePredicate.and(thirdCompanyPredicate);
        expectedModel.updateFilteredPolicyList(thirdFinalPredicate);
        assertCommandSuccess(thirdFindPolicyCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(INVEST_READY), model.getFilteredPolicyList());
    }
}
