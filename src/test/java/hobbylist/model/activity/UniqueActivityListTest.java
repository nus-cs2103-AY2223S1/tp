package hobbylist.model.activity;

import static hobbylist.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_ENTERTAINMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.model.activity.exceptions.ActivityNotFoundException;
import hobbylist.model.activity.exceptions.DuplicateActivityException;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;

public class UniqueActivityListTest {

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void contains_activityNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void contains_activityInList_returnsTrue() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        assertTrue(uniqueActivityList.contains(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void contains_activityWithSameIdentityFieldsInList_returnsTrue() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .build();
        assertTrue(uniqueActivityList.contains(editedA));
    }

    @Test
    public void add_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void add_duplicateActivity_throwsDuplicateActivityException() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        Assert.assertThrows(DuplicateActivityException.class, ()
                -> uniqueActivityList.add(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void setActivity_nullTargetActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniqueActivityList.setActivity(null, TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void setActivity_nullEditedActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, null));
    }

    @Test
    public void setActivity_targetActivityNotInList_throwsActivityNotFoundException() {
        Assert.assertThrows(ActivityNotFoundException.class, ()
                -> uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void setActivity_editedActivityIsSameActivity_success() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, TypicalActivities.ACTIVITY_A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasSameIdentity_success() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .withDescription(VALID_DESCRIPTION_BOXING).withTags(VALID_TAG_ENTERTAINMENT)
                .build();
        uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, editedA);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(editedA);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasDifferentIdentity_success() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, TypicalActivities.BOXING);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalActivities.BOXING);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasNonUniqueIdentity_throwsDuplicateActivityException() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        uniqueActivityList.add(TypicalActivities.BOXING);
        Assert.assertThrows(DuplicateActivityException.class, ()
                -> uniqueActivityList.setActivity(TypicalActivities.ACTIVITY_A, TypicalActivities.BOXING));
    }

    @Test
    public void remove_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void remove_activityDoesNotExist_throwsActivityNotFoundException() {
        Assert.assertThrows(ActivityNotFoundException.class, ()
                -> uniqueActivityList.remove(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void remove_existingActivity_removesActivity() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        uniqueActivityList.remove(TypicalActivities.ACTIVITY_A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullUniqueActivityList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniqueActivityList.setActivities((UniqueActivityList) null));
    }

    @Test
    public void setActivities_uniqueActivityList_replacesOwnListWithProvidedUniqueActivityList() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalActivities.BOXING);
        uniqueActivityList.setActivities(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivities((List<Activity>) null));
    }

    @Test
    public void setActivities_list_replacesOwnListWithProvidedList() {
        uniqueActivityList.add(TypicalActivities.ACTIVITY_A);
        List<Activity> activityList = Collections.singletonList(TypicalActivities.BOXING);
        uniqueActivityList.setActivities(activityList);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalActivities.BOXING);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_listWithDuplicateActivity_throwsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivities = Arrays.asList(TypicalActivities.ACTIVITY_A,
                TypicalActivities.ACTIVITY_A);
        Assert.assertThrows(DuplicateActivityException.class, ()
                -> uniqueActivityList.setActivities(listWithDuplicateActivities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }
}
