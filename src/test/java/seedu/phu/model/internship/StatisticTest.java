package seedu.phu.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.APPLE;
import static seedu.phu.testutil.TypicalInternships.BLACKROCK;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.keyword.Keyword;
import seedu.phu.commons.core.keyword.KeywordList;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;

public class StatisticTest {
    private Model model;
    private Statistic statistic;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        statistic = new Statistic(model.getFilteredInternshipList());
    }

    @Test
    public void getWidth_noMutation() {
        Map<ApplicationProcess.ApplicationProcessState, Double> map = statistic.getWidth();

        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.APPLIED)), (double) 5 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ASSESSMENT)), (double) 1 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.INTERVIEW)), (double) 1 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ACCEPTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.REJECTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.OFFER)), 0);
    }

    @Test
    public void getWidth_withMutation() {
        model.addInternship(APPLE);
        model.addInternship(BLACKROCK);
        model.deleteInternship(AMAZON);

        KeywordList keywords = new KeywordList();
        keywords.addKeyword(new Keyword("A"));
        model.updateFilteredInternshipList(new ContainsKeywordsPredicate(
                keywords, FindableCategory.COMPANY_NAME));

        Map<ApplicationProcess.ApplicationProcessState, Double> map = statistic.getWidth();

        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.APPLIED)), (double) 3 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ASSESSMENT)), (double) 2 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.INTERVIEW)), (double) 2 / 7 * 100);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ACCEPTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.REJECTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.OFFER)), 0);
    }

    @Test
    public void getGroupedData_withoutMutation() {
        Map<ApplicationProcess.ApplicationProcessState, Integer> map = statistic.getGroupedData();

        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.APPLIED)), 5);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ASSESSMENT)), 1);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.INTERVIEW)), 1);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ACCEPTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.REJECTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.OFFER)), 0);
    }

    @Test
    public void getGroupedData_withMutation() {
        model.addInternship(APPLE);
        model.addInternship(BLACKROCK);
        model.deleteInternship(AMAZON);

        KeywordList keywords = new KeywordList();
        keywords.addKeyword(new Keyword("A"));
        model.updateFilteredInternshipList(new ContainsKeywordsPredicate(
                keywords, FindableCategory.COMPANY_NAME));

        Map<ApplicationProcess.ApplicationProcessState, Integer> map = statistic.getGroupedData();

        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.APPLIED)), 3);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ASSESSMENT)), 2);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.INTERVIEW)), 2);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.ACCEPTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.REJECTED)), 0);
        assertEquals(map.get((ApplicationProcess.ApplicationProcessState.OFFER)), 0);
    }
}
