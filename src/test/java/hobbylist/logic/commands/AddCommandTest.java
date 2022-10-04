package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static hobbylist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import hobbylist.model.HobbyList;
import hobbylist.model.activity.Activity;
import hobbylist.testutil.Assert;
import hobbylist.testutil.ActivityBuilder;
import javafx.collections.ObservableList;
import hobbylist.commons.core.GuiSettings;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;

public class AddCommandTest {

    @Test
    public void constructor_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_activityAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingActivityAdded modelStub = new ModelStubAcceptingActivityAdded();
        Activity validActivity = new ActivityBuilder().build();

        CommandResult commandResult = new AddCommand(validActivity).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validActivity), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validActivity), modelStub.activitiesAdded);
    }

    @Test
    public void execute_duplicateActivity_throwsCommandException() {
        Activity validActivity = new ActivityBuilder().build();
        AddCommand addCommand = new AddCommand(validActivity);
        ModelStub modelStub = new ModelStubWithActivity(validActivity);

        Assert.assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ACTIVITY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Activity anime = new ActivityBuilder().withName("Anime").build();
        Activity boxing = new ActivityBuilder().withName("Boxing").build();
        AddCommand addAnimeCommand = new AddCommand(anime);
        AddCommand addBoxingCommand = new AddCommand(boxing);

        // same object -> returns true
        assertTrue(addAnimeCommand.equals(addAnimeCommand));

        // same values -> returns true
        AddCommand addAnimeCommandCopy = new AddCommand(anime);
        assertTrue(addAnimeCommand.equals(addAnimeCommandCopy));

        // different types -> returns false
        assertFalse(addAnimeCommand.equals(1));

        // null -> returns false
        assertFalse(addAnimeCommand.equals(null));

        // different person -> returns false
        assertFalse(addAnimeCommand.equals(addBoxingCommand));
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
    }

    /**
     * A Model stub that contains a single activity.
     */
    private class ModelStubWithActivity extends ModelStub {
        private final Activity activity;

        ModelStubWithActivity(Activity activity) {
            requireNonNull(activity);
            this.activity = activity;
        }

        @Override
        public boolean hasActivity(Activity activity) {
            requireNonNull(activity);
            return this.activity.isSameActivity(activity);
        }
    }

    /**
     * A Model stub that always accept the activity being added.
     */
    private class ModelStubAcceptingActivityAdded extends ModelStub {
        final ArrayList<Activity> activitiesAdded = new ArrayList<>();

        @Override
        public boolean hasActivity(Activity activity) {
            requireNonNull(activity);
            return activitiesAdded.stream().anyMatch(activity::isSameActivity);
        }

        @Override
        public void addActivity(Activity activity) {
            requireNonNull(activity);
            activitiesAdded.add(activity);
        }

        @Override
        public ReadOnlyHobbyList getHobbyList() {
            return new HobbyList();
        }
    }

}
