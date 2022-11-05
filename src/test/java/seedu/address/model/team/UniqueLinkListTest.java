package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_FACEBOOK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.exceptions.DuplicateLinkException;
import seedu.address.model.team.exceptions.LinkNotFoundException;
import seedu.address.testutil.LinkBuilder;

class UniqueLinkListTest {
    private final UniqueLinkList uniqueLinkList = new UniqueLinkList();

    @Test
    public void contains_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.contains(null));
    }

    @Test
    public void contains_linkNotInList_returnsFalse() {
        assertFalse(uniqueLinkList.contains(LINK_GOOGLE));
    }

    @Test
    public void contains_linkInList_returnsTrue() {
        uniqueLinkList.add(LINK_GOOGLE);
        assertTrue(uniqueLinkList.contains(LINK_GOOGLE));
    }

    @Test
    public void contains_linkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLinkList.add(LINK_GOOGLE);
        Link editedGoogle = new LinkBuilder(LINK_GOOGLE).withLink(VALID_URL_FACEBOOK).build();
        assertTrue(uniqueLinkList.contains(editedGoogle));
    }

    @Test
    public void add_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.add(null));
    }

    @Test
    public void add_duplicateLink_throwsDuplicateLinkException() {
        uniqueLinkList.add(LINK_GOOGLE);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.add(LINK_GOOGLE));
    }

    @Test
    public void setLink_nullTargetLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLink(null, LINK_GOOGLE));
    }

    @Test
    public void setLink_nullEditedLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLink(LINK_GOOGLE, null));
    }

    @Test
    public void setLink_targetLinkNotInList_throwsLinkNotFoundException() {
        assertThrows(LinkNotFoundException.class, () -> uniqueLinkList.setLink(LINK_GOOGLE, LINK_GOOGLE));
    }

    @Test
    public void setLink_editedLinkIsSameLink_success() {
        uniqueLinkList.add(LINK_GOOGLE);
        uniqueLinkList.setLink(LINK_GOOGLE, LINK_GOOGLE);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_GOOGLE);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasSameIdentity_success() {
        uniqueLinkList.add(LINK_GOOGLE);
        Link editedGoogle = new LinkBuilder(LINK_GOOGLE).withLink(VALID_URL_FACEBOOK).build();
        uniqueLinkList.setLink(LINK_GOOGLE, editedGoogle);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(editedGoogle);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasDifferentIdentity_success() {
        uniqueLinkList.add(LINK_GOOGLE);
        uniqueLinkList.setLink(LINK_GOOGLE, LINK_FACEBOOK);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_FACEBOOK);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasNonUniqueIdentity_throwsDuplicateLinkException() {
        uniqueLinkList.add(LINK_GOOGLE);
        uniqueLinkList.add(LINK_FACEBOOK);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.setLink(LINK_GOOGLE, LINK_FACEBOOK));
    }

    @Test
    public void remove_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.remove(null));
    }

    @Test
    public void remove_linkDoesNotExist_throwsLinkNotFoundException() {
        assertThrows(LinkNotFoundException.class, () -> uniqueLinkList.remove(LINK_GOOGLE));
    }

    @Test
    public void remove_existingLink_removesLink() {
        uniqueLinkList.add(LINK_GOOGLE);
        uniqueLinkList.remove(LINK_GOOGLE);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_nullUniqueLinkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLinks(null));
    }

    @Test
    public void setLinks_uniqueLinkList_replacesOwnListWithProvidedUniqueLinkList() {
        uniqueLinkList.add(LINK_GOOGLE);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_FACEBOOK);
        uniqueLinkList.setLinks(expectedUniqueLinkList.asUnmodifiableObservableList());
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLinks((List<Link>) null));
    }

    @Test
    public void setLinks_list_replacesOwnListWithProvidedList() {
        uniqueLinkList.add(LINK_GOOGLE);
        List<Link> linkList = Collections.singletonList(LINK_FACEBOOK);
        uniqueLinkList.setLinks(linkList);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_FACEBOOK);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_listWithDuplicateLinks_throwsDuplicateLinkException() {
        List<Link> listWithDuplicateLinks = Arrays.asList(LINK_GOOGLE, LINK_GOOGLE);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.setLinks(listWithDuplicateLinks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueLinkList.asUnmodifiableObservableList().remove(0));
    }
}