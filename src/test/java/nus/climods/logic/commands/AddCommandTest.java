package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    // TODO: add more tests once we have infrastructure to compare expected vs actual model state
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }
}

