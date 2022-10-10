package seedu.address.model.application;

import org.junit.jupiter.api.Test;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.model.application.exceptions.ApplicationNotFoundException;
import seedu.address.testutil.ApplicationBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.GOOGLE;

public class UniqueApplicationListTest {

    private final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

    @Test
    public void contains_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.contains(null));
    }

    @Test
    public void contains_ApplicationNotInList_returnsFalse() {
        assertFalse(uniqueApplicationList.contains(BYTEDANCE));
    }

    @Test
    public void contains_ApplicationInList_returnsTrue() {
        uniqueApplicationList.add(BYTEDANCE);
        assertTrue(uniqueApplicationList.contains(BYTEDANCE));
    }

    @Test
    public void contains_ApplicationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicationList.add(BYTEDANCE);
        Application editedBYTEDANCE = new ApplicationBuilder(BYTEDANCE).withEmail(VALID_EMAIL_GOOGLE).withContact(VALID_CONTACT_FACEBOOK)
                .build();
        assertTrue(uniqueApplicationList.contains(editedBYTEDANCE));
    }

    @Test
    public void add_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.add(null));
    }

    @Test
    public void add_duplicateApplication_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(BYTEDANCE);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.add(BYTEDANCE));
    }

    @Test
    public void setApplication_nullTargetApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(null, BYTEDANCE));
    }

    @Test
    public void setApplication_nullEditedApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(BYTEDANCE, null));
    }

    @Test
    public void setApplication_targetApplicationNotInList_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.setApplication(BYTEDANCE, BYTEDANCE));
    }

    @Test
    public void setApplication_editedApplicationIsSameApplication_success() {
        uniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.setApplication(BYTEDANCE, BYTEDANCE);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BYTEDANCE);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasSameIdentity_success() {
        uniqueApplicationList.add(BYTEDANCE);
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withEmail(VALID_EMAIL_GOOGLE).withContact(VALID_CONTACT_FACEBOOK)
                .build();
        uniqueApplicationList.setApplication(BYTEDANCE, editedBytedance);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(editedBytedance);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasDifferentIdentity_success() {
        uniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.setApplication(BYTEDANCE, GOOGLE);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GOOGLE);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasNonUniqueIdentity_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.add(GOOGLE);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.setApplication(BYTEDANCE, GOOGLE));
    }

    @Test
    public void remove_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.remove(null));
    }

    @Test
    public void remove_ApplicationDoesNotExist_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.remove(BYTEDANCE));
    }

    @Test
    public void remove_existingApplication_removesApplication() {
        uniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.remove(BYTEDANCE);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullUniqueApplicationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplications((UniqueApplicationList) null));
    }

    @Test
    public void setApplications_uniqueApplicationList_replacesOwnListWithProvidedUniqueApplicationList() {
        uniqueApplicationList.add(BYTEDANCE);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GOOGLE);
        uniqueApplicationList.setApplications(expectedUniqueApplicationList);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplications((List<Application>) null));
    }

    @Test
    public void setApplications_list_replacesOwnListWithProvidedList() {
        uniqueApplicationList.add(BYTEDANCE);
        List<Application> ApplicationList = Collections.singletonList(GOOGLE);
        uniqueApplicationList.setApplications(ApplicationList);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GOOGLE);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_listWithDuplicateApplications_throwsDuplicateApplicationException() {
        List<Application> listWithDuplicateApplications = Arrays.asList(BYTEDANCE, BYTEDANCE);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.setApplications(listWithDuplicateApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicationList.asUnmodifiableObservableList().remove(0));
    }
}
