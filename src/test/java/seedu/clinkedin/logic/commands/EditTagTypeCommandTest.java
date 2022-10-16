package seedu.clinkedin.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

import static seedu.clinkedin.testutil.Assert.assertThrows;

public class EditTagTypeCommandTest {


    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, new UniqueTagTypeMap()));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(Index.fromZeroBased(0), null));
    }
}
