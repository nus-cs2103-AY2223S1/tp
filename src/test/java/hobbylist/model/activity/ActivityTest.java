package hobbylist.model.activity;

import static hobbylist.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_ENTERTAINMENT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;

public class ActivityTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Activity activity = new ActivityBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> activity.getTags().remove(0));
    }

    @Test
    public void isSameActivity() {
        // same object -> returns true
        assertTrue(TypicalActivities.ACTIVITY_A.isSameActivity(TypicalActivities.ACTIVITY_A));

        // null -> returns false
        assertFalse(TypicalActivities.ACTIVITY_A.isSameActivity(null));

        // same name, all other attributes different -> returns false
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .withDescription(VALID_DESCRIPTION_BOXING).withTags(VALID_TAG_ENTERTAINMENT).build();
        assertFalse(TypicalActivities.ACTIVITY_A.isSameActivity(editedA));

        // same name, description different -> returns false
        editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .withDescription(VALID_DESCRIPTION_BOXING).build();
        assertFalse(TypicalActivities.ACTIVITY_A.isSameActivity(editedA));

        // same name, tags different -> returns false
        editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A)
                .withTags(VALID_TAG_ENTERTAINMENT).build();
        assertFalse(TypicalActivities.ACTIVITY_A.isSameActivity(editedA));

        // different name, all other attributes same -> returns false
        editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A).withName(VALID_NAME_BOXING).build();
        assertFalse(TypicalActivities.ACTIVITY_A.isSameActivity(editedA));

        // name differs in case, all other attributes same -> returns false
        Activity editedB = new ActivityBuilder(TypicalActivities.BOXING)
                .withName(VALID_NAME_BOXING.toLowerCase()).build();
        assertFalse(TypicalActivities.BOXING.isSameActivity(editedB));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOXING + " ";
        editedB = new ActivityBuilder(TypicalActivities.BOXING).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalActivities.BOXING.isSameActivity(editedB));

        // name, description and tags are all the same -> returns true
        editedA = new ActivityBuilder((TypicalActivities.ACTIVITY_A)).build();
        assertTrue(TypicalActivities.ACTIVITY_A.isSameActivity(editedA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Activity aliceCopy = new ActivityBuilder(TypicalActivities.ACTIVITY_A).build();
        assertTrue(TypicalActivities.ACTIVITY_A.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalActivities.ACTIVITY_A.equals(TypicalActivities.ACTIVITY_A));

        // null -> returns false
        assertFalse(TypicalActivities.ACTIVITY_A.equals(null));

        // different type -> returns false
        assertFalse(TypicalActivities.ACTIVITY_A.equals(5));

        // different activity -> returns false
        assertFalse(TypicalActivities.ACTIVITY_A.equals(TypicalActivities.BOXING));

        // different name -> returns false
        Activity editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A).withName(VALID_NAME_BOXING).build();
        assertFalse(TypicalActivities.ACTIVITY_A.equals(editedA));

        // different description -> returns false
        editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A).withDescription(VALID_DESCRIPTION_BOXING).build();
        assertFalse(TypicalActivities.ACTIVITY_A.equals(editedA));

        // different tags -> returns false
        editedA = new ActivityBuilder(TypicalActivities.ACTIVITY_A).withTags(VALID_TAG_ENTERTAINMENT).build();
        assertFalse(TypicalActivities.ACTIVITY_A.equals(editedA));
    }
}
