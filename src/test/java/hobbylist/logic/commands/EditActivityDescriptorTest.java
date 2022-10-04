package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.DESC_ANIME;
import static hobbylist.logic.commands.CommandTestUtil.DESC_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_ENTERTAINMENT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.EditCommand.EditActivityDescriptor;
import hobbylist.testutil.EditActivityDescriptorBuilder;

public class EditActivityDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditActivityDescriptor descriptorWithSameValues = new EditActivityDescriptor(DESC_ANIME);
        assertTrue(DESC_ANIME.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ANIME.equals(DESC_ANIME));

        // null -> returns false
        assertFalse(DESC_ANIME.equals(null));

        // different types -> returns false
        assertFalse(DESC_ANIME.equals(5));

        // different values -> returns false
        assertFalse(DESC_ANIME.equals(DESC_BOXING));

        // different name -> returns false
        EditActivityDescriptor editedAmy = new EditActivityDescriptorBuilder(DESC_ANIME).withName(VALID_NAME_BOXING)
                .build();
        assertFalse(DESC_ANIME.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditActivityDescriptorBuilder(DESC_ANIME).withDescription(VALID_DESCRIPTION_BOXING).build();
        assertFalse(DESC_ANIME.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditActivityDescriptorBuilder(DESC_ANIME).withTags(VALID_TAG_ENTERTAINMENT).build();
        assertFalse(DESC_ANIME.equals(editedAmy));
    }
}
