package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.model.Model.HIDE_ARCHIVE_IN_LIST;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.FACEBOOK;
import static seedu.application.testutil.TypicalApplications.SHOPEE;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalApplications.getTypicalApplications;
import static seedu.application.testutil.TypicalApplicationsWithInterview.getTypicalApplicationBookWithInterview;
import static seedu.application.testutil.TypicalApplicationsWithInterview.getTypicalApplicationsWithInterview;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.GuiSettings;
import seedu.application.model.application.Application;
import seedu.application.model.application.CompanyContainsKeywordsPredicate;
import seedu.application.model.application.PositionContainsKeywordsPredicate;
import seedu.application.model.application.interview.InterviewComparator;
import seedu.application.testutil.ApplicationBookBuilder;
import seedu.application.testutil.ApplicationBuilder;
import seedu.application.testutil.TypicalApplicationsWithInterview;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ApplicationBook(), new ApplicationBook(modelManager.getApplicationBook()));
    }

    @Test
    public void constructor_userPrefsProvided_applicationsSortedBasedOnSetting() {
        ModelManager modelManager;
        ModelManager expectedModel;
        ApplicationBook applicationBook = getTypicalApplicationBookWithInterview();
        applicationBook.addApplication(TypicalApplicationsWithInterview.GOOGLE);
        applicationBook.addApplication(TypicalApplicationsWithInterview.FACEBOOK);
        UserPrefs userPrefs = new UserPrefs();

        userPrefs.setSortSetting(SortSetting.BY_COMPANY);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByCompany(false);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_COMPANY_REVERSE);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByCompany(true);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_POSITION);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByPosition(false);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_POSITION_REVERSE);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByPosition(true);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_DATE);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByDate(false);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_DATE_REVERSE);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByDate(true);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_INTERVIEW);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByInterview(false);
        assertEquals(expectedModel, modelManager);

        userPrefs.setSortSetting(SortSetting.BY_INTERVIEW_REVERSE);
        modelManager = new ModelManager(applicationBook, userPrefs);
        expectedModel = new ModelManager(applicationBook, new UserPrefs());
        expectedModel.sortApplicationListByInterview(true);
        assertEquals(expectedModel, modelManager);
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicationBookFilePath(Paths.get("application/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicationBookFilePath(Paths.get("new/application/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setApplicationBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setApplicationBookFilePath(null));
    }

    @Test
    public void setApplicationBookFilePath_validPath_setsApplicationBookFilePath() {
        Path path = Paths.get("application/book/file/path");
        modelManager.setApplicationBookFilePath(path);
        assertEquals(path, modelManager.getApplicationBookFilePath());
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInApplicationBook_returnsFalse() {
        assertFalse(modelManager.hasApplication(FACEBOOK));
    }

    @Test
    public void hasApplication_applicationInApplicationBook_returnsTrue() {
        modelManager.addApplication(FACEBOOK);
        assertTrue(modelManager.hasApplication(FACEBOOK));
    }

    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList()
                .remove(0));
    }

    @Test
    public void sortApplicationListByCompany_emptyList_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.sortApplicationListByCompany(false);
        assertEquals(SortSetting.BY_COMPANY, modelManager.getSortSetting());
        modelManager.sortApplicationListByCompany(true);
        assertEquals(SortSetting.BY_COMPANY_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByCompany_nonemptyList_applicationsCorrectOrder() {
        ModelManager modelManager = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        List<Application> applications = getTypicalApplications();

        modelManager.sortApplicationListByCompany(false);
        applications.sort(Comparator.comparing(Application::getCompany));
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_COMPANY, modelManager.getSortSetting());

        modelManager.sortApplicationListByCompany(true);
        applications.sort(Comparator.comparing(Application::getCompany).reversed());
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_COMPANY_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByPosition_emptyList_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.sortApplicationListByPosition(false);
        assertEquals(SortSetting.BY_POSITION, modelManager.getSortSetting());
        modelManager.sortApplicationListByPosition(true);
        assertEquals(SortSetting.BY_POSITION_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByPosition_nonemptyList_applicationsCorrectOrder() {
        ModelManager modelManager = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        List<Application> applications = getTypicalApplications();

        modelManager.sortApplicationListByPosition(false);
        applications.sort(Comparator.comparing(Application::getPosition));
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_POSITION, modelManager.getSortSetting());

        modelManager.sortApplicationListByPosition(true);
        applications.sort(Comparator.comparing(Application::getPosition).reversed());
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_POSITION_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByDate_emptyList_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.sortApplicationListByDate(false);
        assertEquals(SortSetting.BY_DATE, modelManager.getSortSetting());
        modelManager.sortApplicationListByDate(true);
        assertEquals(SortSetting.BY_DATE_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByDate_nonemptyList_applicationsCorrectOrder() {
        ModelManager modelManager = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        List<Application> applications = getTypicalApplications();

        modelManager.sortApplicationListByDate(false);
        applications.sort(Comparator.comparing(Application::getDate));
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_DATE, modelManager.getSortSetting());

        modelManager.sortApplicationListByDate(true);
        applications.sort(Comparator.comparing(Application::getDate).reversed());
        assertEquals(applications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_DATE_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByInterview_emptyList_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.sortApplicationListByInterview(false);
        assertEquals(SortSetting.BY_INTERVIEW, modelManager.getSortSetting());
        modelManager.sortApplicationListByInterview(true);
        assertEquals(SortSetting.BY_INTERVIEW_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void sortApplicationListByInterview_nonemptyList_applicationsCorrectOrder() {
        ApplicationBook applicationBook = getTypicalApplicationBookWithInterview();
        List<Application> typicalApplications = getTypicalApplicationsWithInterview();
        List<Application> allApplications;

        // Applications with no interview should always sort after those with interviews
        Application applicationWithNoInterview = new ApplicationBuilder().build();
        assert !applicationWithNoInterview.hasInterview();
        applicationBook.addApplication(applicationWithNoInterview);

        ModelManager modelManager = new ModelManager(applicationBook, new UserPrefs());

        modelManager.sortApplicationListByInterview(false);
        typicalApplications.sort(new InterviewComparator());
        allApplications = new ArrayList<>(typicalApplications);
        allApplications.add(applicationWithNoInterview);
        assertEquals(allApplications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_INTERVIEW, modelManager.getSortSetting());

        modelManager.sortApplicationListByInterview(true);
        typicalApplications.sort(new InterviewComparator().reversed());
        allApplications = new ArrayList<>(typicalApplications);
        allApplications.add(applicationWithNoInterview);
        assertEquals(allApplications, modelManager.getFilteredApplicationList());
        assertEquals(SortSetting.BY_INTERVIEW_REVERSE, modelManager.getSortSetting());
    }

    @Test
    public void equals() {
        ApplicationBook applicationBook = new ApplicationBookBuilder().withApplication(FACEBOOK)
                .withApplication(SHOPEE).build();
        ApplicationBook differentApplicationBook = new ApplicationBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(applicationBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(applicationBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different ApplicationBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentApplicationBook, userPrefs)));

        // different VersionedApplicationBook -> return false
        modelManager.commitApplicationBook();
        assertFalse(modelManager.equals(modelManagerCopy));

        // same VersionedApplicationBook -> return true
        modelManagerCopy.commitApplicationBook();
        assertTrue(modelManager.equals(modelManagerCopy));

        // different filteredList (filtered by company) -> returns false
        String[] keywords = FACEBOOK.getCompany().company.split("\\s+");
        modelManager.updateFilteredApplicationList(new CompanyContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(HIDE_ARCHIVE_IN_LIST);

        // different filteredList (filtered by position) -> returns false
        String keyword = SHOPEE.getPosition().value.split("\\s+")[0]; //"Frontend"
        List<String> positionKeyword = new ArrayList<String>();
        positionKeyword.add(keyword);
        modelManager.updateFilteredApplicationList(new PositionContainsKeywordsPredicate(positionKeyword));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(HIDE_ARCHIVE_IN_LIST);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicationBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, differentUserPrefs)));
    }
}
