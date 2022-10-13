package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null, null));
    }

    @Test
    public void execute_validAddStaff_success() {

    }

    @Test
    public void execute_duplicateStaff_exception() {

    }

    @Test
    public void execute_invalidProject_exception() {

    }


}
