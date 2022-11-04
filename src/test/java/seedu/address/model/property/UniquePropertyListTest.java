package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_ADDRESS_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_DESCRIPTION_HOME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.HOME;
import static seedu.address.testutil.TypicalProperties.PEAKRESIDENCE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.PropertyBuilder;

public class UniquePropertyListTest {

    private final UniquePropertyList uniquePropertyList = new UniquePropertyList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyList.contains(PEAKRESIDENCE));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyList.add(PEAKRESIDENCE);
        assertTrue(uniquePropertyList.contains(PEAKRESIDENCE));
    }

    @Test
    public void contains_propertyWithSameIdentityFieldsInList_returnsTrue() {
        uniquePropertyList.add(PEAKRESIDENCE);
        Property editedPeakResidence = new PropertyBuilder(PEAKRESIDENCE)
                .withDescription(VALID_DESCRIPTION_HOME)
                .build();
        assertTrue(uniquePropertyList.contains(editedPeakResidence));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PEAKRESIDENCE);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.add(PEAKRESIDENCE));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(null, PEAKRESIDENCE));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(PEAKRESIDENCE, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(
                PropertyNotFoundException.class, (
                ) -> uniquePropertyList.setProperty(PEAKRESIDENCE, PEAKRESIDENCE));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyList.add(PEAKRESIDENCE);
        uniquePropertyList.setProperty(PEAKRESIDENCE, PEAKRESIDENCE);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(PEAKRESIDENCE);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyList.add(PEAKRESIDENCE);
        Property editedPeakResidence = new PropertyBuilder(PEAKRESIDENCE).withPrice("3000000")
                .withAddress(VALID_ADDRESS_HOME)
                .build();
        uniquePropertyList.setProperty(PEAKRESIDENCE, editedPeakResidence);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(editedPeakResidence);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyList.add(PEAKRESIDENCE);
        uniquePropertyList.setProperty(PEAKRESIDENCE, HOME);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(HOME);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PEAKRESIDENCE);
        uniquePropertyList.add(HOME);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.setProperty(PEAKRESIDENCE, HOME));
    }

    @Test
    public void remove_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.remove(PEAKRESIDENCE));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyList.add(PEAKRESIDENCE);
        uniquePropertyList.remove(PEAKRESIDENCE);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_nullUniquePropertyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((UniquePropertyList) null));
    }

    @Test
    public void setProperties_uniqueBuyerList_replacesOwnListWithProvidedUniqueBuyerList() {
        uniquePropertyList.add(PEAKRESIDENCE);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(HOME);
        uniquePropertyList.setProperties(expectedUniquePropertyList);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((List<Property>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyList.add(PEAKRESIDENCE);
        List<Property> propertyList = Collections.singletonList(HOME);
        uniquePropertyList.setProperties(propertyList);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(HOME);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<Property> listWithDuplicateProperties = Arrays.asList(PEAKRESIDENCE, PEAKRESIDENCE);
        assertThrows(DuplicatePropertyException.class, (
        ) -> uniquePropertyList.setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePropertyList.asUnmodifiableObservableList().remove(0));
    }
}
