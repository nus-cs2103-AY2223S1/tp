package seedu.condonery.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_TAG_HUSBAND;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalProperties.PINNACLE;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.exceptions.DuplicatePropertyException;
import seedu.condonery.testutil.PropertyBuilder;

public class PropertyDirectoryTest {

    private final PropertyDirectory propertyDirectory = new PropertyDirectory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), propertyDirectory.getPropertyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyDirectory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPropertyDirectory_replacesData() {
        PropertyDirectory newData = getTypicalPropertyDirectory();
        propertyDirectory.resetData(newData);
        assertEquals(newData, propertyDirectory);
    }

    @Test
    public void resetData_withDuplicateProperties_throwsDuplicatePropertyException() {
        // Two persons with the same identity fields
        Property editedPinnacle = new PropertyBuilder(PINNACLE)
                .withAddress(CLIENT_VALID_ADDRESS_BOB).withTags(CLIENT_VALID_TAG_HUSBAND)
            .build();
        List<Property> newProperties = Arrays.asList(PINNACLE, editedPinnacle);
        PropertyDirectoryStub newData = new PropertyDirectoryStub(newProperties);

        assertThrows(DuplicatePropertyException.class, () -> propertyDirectory.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyDirectory.hasProperty(null));
    }

    @Test
    public void hasPerson_personNotInPropertyDirectory_returnsFalse() {
        assertFalse(propertyDirectory.hasProperty(PINNACLE));
    }

    @Test
    public void hasPerson_personInPropertyDirectory_returnsTrue() {
        propertyDirectory.addProperty(PINNACLE);
        assertTrue(propertyDirectory.hasProperty(PINNACLE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPropertyDirectory_returnsTrue() {
        propertyDirectory.addProperty(PINNACLE);
        Property editedPinnacle = new PropertyBuilder(PINNACLE)
                .withAddress(CLIENT_VALID_ADDRESS_BOB).withTags(CLIENT_VALID_TAG_HUSBAND)
            .build();
        assertTrue(propertyDirectory.hasProperty(editedPinnacle));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> propertyDirectory.getPropertyList().remove(0));
    }

    /**
     * A stub ReadOnlyPropertyDirectory whose persons list can violate interface constraints.
     */
    private static class PropertyDirectoryStub implements ReadOnlyPropertyDirectory {
        private final ObservableList<Property> properties = FXCollections.observableArrayList();

        PropertyDirectoryStub(Collection<Property> properties) {
            this.properties.setAll(properties);
        }

        @Override
        public ObservableList<Property> getPropertyList() {
            return properties;
        }
    }

}
