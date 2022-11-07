package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.AliasSettings;
import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.Messages;
import hobbylist.commons.core.ThemeSettings;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Review;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RateCommandTest {

    @Test
    public void constructor_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        RateCommand test = new RateCommand(Index.fromOneBased(10), 1, Optional.empty());
        Assert.assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX, (
                ) -> test.execute(new ModelStubWithActivity(TypicalActivities.ACTIVITY_G)));
    }

    @Test
    public void execute_success_returnsCorrectCommandResult() {
        RateCommand test = new RateCommand(Index.fromOneBased(1), 2, Optional.empty());
        ModelStubWithActivity model = new ModelStubWithActivity(TypicalActivities.ACTIVITY_G);
        String expectedString = String.format(RateCommand.MESSAGE_EDIT_ACTIVITY_SUCCESS, TypicalActivities.ACTIVITY_G);
        assertCommandSuccess(test, model, expectedString, model);
    }

    @Test
    public void setCommandWord_validWord_success() {
        RateCommand.setCommandWord("test");
        assertEquals(RateCommand.getCommandWord(), "test");
        RateCommand.setCommandWord("rate");
    }

    @Test
    public void equals() {
        RateCommand firstCommand = new RateCommand(Index.fromOneBased(1), 3, Optional.of(new Review("test")));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // wrong type -> returns false
        assertFalse(firstCommand.equals("test"));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> return true
        RateCommand copy = new RateCommand(Index.fromOneBased(1), 3, Optional.of(new Review("test")));
        assertTrue(firstCommand.equals(copy));

        // different index -> return false
        RateCommand different = new RateCommand(Index.fromOneBased(2), 3, Optional.of(new Review("test")));
        assertFalse(firstCommand.equals(different));

        // different rating -> return false
        different = new RateCommand(Index.fromOneBased(1), 2, Optional.of(new Review("test")));
        assertFalse(firstCommand.equals(different));

        // different review -> return false
        different = new RateCommand(Index.fromOneBased(1), 3, Optional.of(new Review("different")));
        assertFalse(firstCommand.equals(different));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ThemeSettings getThemeSettings() {
            throw new AssertionError("This method should not be called.");
        }

        public AliasSettings getAliasSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setThemeSettings(ThemeSettings themeSettings) {
            throw new AssertionError("This method should not be called.");
        }
        public void setAliasSettings(AliasSettings aliasSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHobbyListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHobbyListFilePath(Path hobbyListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHobbyList(ReadOnlyHobbyList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHobbyList getHobbyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteActivity(Activity target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setActivity(Activity target, Activity editedActivity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Activity> getFilteredActivityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredActivityList(Predicate<Activity> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Activity> getSelectedActivity() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void selectActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single activity.
     */
    private class ModelStubWithActivity extends ModelStub {
        private final ObservableList<Activity> activity;

        ModelStubWithActivity(Activity activity) {
            requireNonNull(activity);
            this.activity = FXCollections.observableArrayList(activity);
        }

        @Override
        public boolean hasActivity(Activity activity) {
            requireNonNull(activity);
            return this.activity.get(0).isSameActivity(activity);
        }

        @Override
        public ObservableList<Activity> getFilteredActivityList() {
            return this.activity;
        }

        @Override
        public void setActivity(Activity one, Activity two) {
            return;
        }

        @Override
        public void updateFilteredActivityList(Predicate<Activity> test) {
            return;
        }
    }
}
