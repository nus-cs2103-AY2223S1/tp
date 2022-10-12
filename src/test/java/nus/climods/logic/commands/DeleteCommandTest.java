package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    // TODO: add more tests. harder to test since we need a way to test expected model state vs actual
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(null));
    }
}

