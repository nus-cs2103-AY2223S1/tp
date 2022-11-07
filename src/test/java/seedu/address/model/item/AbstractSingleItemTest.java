package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.AccessDisplayFlags.GROUP;
import static seedu.address.model.AccessDisplayFlags.TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.exceptions.ItemCannotBeParentException;
import seedu.address.testutil.PersonBuilder;

class AbstractSingleItemTest {

    private static final AbstractSingleItem SAMPLE_GROUP = new AbstractSingleItem("CS2103", GROUP, GROUP) {
        @Override
        public UUID getUid() {
            return null;
        }
    };

    private static final AbstractSingleItem SAMPLE_TASK = new AbstractSingleItem("Testing", TASK, TASK) {
        @Override
        public UUID getUid() {
            return null;
        }
    };

    private static AbstractSingleItem otherGroup = new AbstractSingleItem("Group2", GROUP, GROUP) {
        @Override
        public UUID getUid() {
            return null;
        }
    };

    @Test
    void instanceOfAbstractDisplayItem_returnsTrue() {
        assertTrue(SAMPLE_GROUP instanceof AbstractDisplayItem);
    }

    @Test
    void constructor_validInputs_success() {
        AbstractSingleItem item = new AbstractSingleItem("ABC", TASK, TASK) {
            @Override
            public UUID getUid() {
                return null;
            }
        };
        assertNotNull(item);
    }

    @Test
    void getTitle_success() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("contactmation.json");
        assertEquals(otherGroup.getTitle(sb, null), "/Group2/contactmation.json");
    }

    @Test
    void getFullPath_success() {
        assertEquals(otherGroup.getFullPath(), "/Group2");
    }

    @Test
    void setParent_inputIsAbstractSingleItem_success() {
        SAMPLE_GROUP.setParent(otherGroup);
        assertEquals(SAMPLE_GROUP.getParent(), otherGroup);
        SAMPLE_GROUP.setParent(null);
    }

    @Test
    void setParent_inputIsNull_success() {
        SAMPLE_TASK.setParent(null);
        assertNull(SAMPLE_TASK.getParent());
    }

    @Test
    void setParent_inputNotAbstractSingleItem_throwsItemCannotBeParentException() {
        assertThrows(ItemCannotBeParentException.class, () -> SAMPLE_GROUP.setParent(new PersonBuilder(ALICE).build()));
    }

    @Test
    void getParent_success() {
        assertNull(SAMPLE_GROUP.getParent());
    }

    @Test
    void getParents_success() {
        assertEquals(otherGroup.getParents(), Set.of());
        SAMPLE_GROUP.setParent(otherGroup);
        assertEquals(SAMPLE_GROUP.getParents(), Set.of(otherGroup));
        SAMPLE_GROUP.setParent(null);
    }

    @Test
    void removeParent() {
        SAMPLE_GROUP.setParent(otherGroup);
        SAMPLE_GROUP.removeParent(otherGroup);
        assertNull(SAMPLE_GROUP.getParent());
    }

    @Test
    void testToString() {
        assertEquals(otherGroup.toString(), "/Group2");
    }
}
