package hobbylist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.model.activity.Activity;
import hobbylist.model.activity.exceptions.DuplicateActivityException;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DescriptionBookTest {

    private final HobbyList hobbyList = new HobbyList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hobbyList.getActivityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> hobbyList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHobbyList_replacesData() {
        HobbyList newData = TypicalActivities.getTypicalHobbyList();
        hobbyList.resetData(newData);
        assertEquals(newData, hobbyList);
    }

    @Test
    public void resetData_withDuplicateActivity_throwsDuplicateActivityException() {
        // Two activities with the same identity fields
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .build();
        List<Activity> newActivities = Arrays.asList(TypicalActivities.ACTIVITY_A, editedA);
        HobbyListStub newData = new HobbyListStub(newActivities);

        Assert.assertThrows(DuplicateActivityException.class, () -> hobbyList.resetData(newData));
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> hobbyList.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInHobbyList_returnsFalse() {
        assertFalse(hobbyList.hasActivity(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void hasActivity_activityInHobbyList_returnsTrue() {
        hobbyList.addActivity(TypicalActivities.ACTIVITY_A);
        assertTrue(hobbyList.hasActivity(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void hasActivity_activityWithSameIdentityFieldsInHobbyList_returnsTrue() {
        hobbyList.addActivity(TypicalActivities.ACTIVITY_A);
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .build();
        assertTrue(hobbyList.hasActivity(editedA));
    }

    @Test
    public void getActivityList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> hobbyList.getActivityList().remove(0));
    }

    /**
     * A stub ReadOnlyHobbyList whose activity list can violate interface constraints.
     */
    private static class HobbyListStub implements ReadOnlyHobbyList {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        HobbyListStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        @Override
        public ObservableList<Activity> getActivityList() {
            return activities;
        }
    }

}
