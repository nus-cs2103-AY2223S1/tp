package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;

public class EditItineraryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryDescriptor descriptorWithSameValues = new EditItineraryDescriptor(DESC_SUMMER);
        assertTrue(DESC_SUMMER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SUMMER.equals(DESC_SUMMER));

        // null -> returns false
        assertFalse(DESC_SUMMER.equals(null));

        // different types -> returns false
        assertFalse(DESC_SUMMER.equals(5));

        // different values -> returns false
        assertFalse(DESC_SUMMER.equals(DESC_WINTER));

        // different name -> returns false
        EditItineraryDescriptor editedSummer =
                new EditItineraryDescriptorBuilder(DESC_SUMMER).withName(VALID_ITINERARY_DESC_WINTER).build();
        assertFalse(DESC_SUMMER.equals(editedSummer));

        // different country -> returns false
        editedSummer = new EditItineraryDescriptorBuilder(DESC_SUMMER).withCountry(VALID_COUNTRY_WINTER).build();
        assertFalse(DESC_SUMMER.equals(editedSummer));

        // different start date -> returns false
        editedSummer = new EditItineraryDescriptorBuilder(DESC_SUMMER).withStartDate(VALID_START_DATE_WINTER).build();
        assertFalse(DESC_SUMMER.equals(editedSummer));

        // different end date -> returns false
        editedSummer = new EditItineraryDescriptorBuilder(DESC_SUMMER).withDuration(VALID_DURATION_WINTER).build();
        assertFalse(DESC_SUMMER.equals(editedSummer));

        // different people -> returns false
        editedSummer = new EditItineraryDescriptorBuilder(DESC_SUMMER).withPeople(VALID_PEOPLE_WINTER).build();
        assertFalse(DESC_SUMMER.equals(editedSummer));
    }
}
