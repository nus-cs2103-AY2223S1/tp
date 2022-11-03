package seedu.boba.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.ALICE;
import static seedu.boba.testutil.TypicalCustomers.CARL;

import org.junit.jupiter.api.Test;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;

public class VersionedBobaBotTest {
    private static BobaBot bbt = new BobaBot();
    @Test
    public void constructor() {
        assertEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 20));
        assertNotEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 25));
    }

    @Test
    public void are_equals() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt, 20);
        assertEquals(versionedBobaBot, versionedBobaBot);
        assertEquals(versionedBobaBot, new VersionedBobaBot(bbt));
    }

    @Test
    public void not_equals() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        assertNotEquals(versionedBobaBot, 1);
    }

    @Test
    public void commitWithValidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        //Since the editedBobaBot differs from the versionedBobaBot, there will be a valid commit
        //and both the currentStatePointer & size of stateList will be incremented by 1
        assertEquals(versionedBobaBot.getStateListSize(), 2);
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 1);
    }

    @Test
    public void commitWithValidArgsAndMaxSize() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        //To fill up the stateList of size 20 with the different versions of bobaBot
        for (int i = 0; i < 20; i++) {
            if(i % 2 == 0) {
                editedBobaBot.addPerson(ALICE);
            } else {
                editedBobaBot.removePerson(ALICE);
            }
            versionedBobaBot.commit(editedBobaBot);
        }

        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        //Since the editedBobaBot differs from the versionedBobaBot, there will be a valid commit
        //The current statePointer will remain at 19
        //The size of the stateList will remain at 20 after removing the oldest state and adding the newest state
        assertEquals(versionedBobaBot.getStateListSize(), 20);
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 19);
    }

    @Test
    public void commitWithInvalidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot notEditedBobaBot = new BobaBot();
        versionedBobaBot.commit(notEditedBobaBot);
        //Since both the notEditedBobaBot and versionedBobaBot are the same, there will be no valid commit
        //thus the size of stateList remains as 1 since no new state is saved and the currentStatePointer
        //remains at 0
        assertEquals(versionedBobaBot.getStateListSize(), 1);
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 0);
    }

    @Test
    public void undoWithValidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        versionedBobaBot.undo(editedBobaBot);
        assertEquals(editedBobaBot, new BobaBot());
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 0);
        assertEquals(versionedBobaBot.getStateListSize(), 2);
    }

    @Test
    public void undoWithInvalidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        assertThrows(PreviousStateNotFoundException.class, () -> versionedBobaBot.undo(editedBobaBot));
    }

    @Test
    public void redoWithValidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        versionedBobaBot.undo(editedBobaBot);
        assertEquals(editedBobaBot, new BobaBot());
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 0);
        assertEquals(versionedBobaBot.getStateListSize(), 2);

        //Testing for redo occurs after undo
        versionedBobaBot.redo(editedBobaBot);
        BobaBot expectedBobaBot = new BobaBot();
        expectedBobaBot.addPerson(ALICE);
        assertEquals(editedBobaBot, expectedBobaBot);
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 1);
        assertEquals(versionedBobaBot.getStateListSize(), 2);
    }

    @Test
    public void redoWithInvalidArgs() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        assertThrows(NextStateNotFoundException.class, () -> versionedBobaBot.redo(editedBobaBot));
    }

    @Test
    public void toStringWhenInitialised() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        String expectedMessage = "VersionedBobaBot: \n"
                + "    size limit: " + 20 + "\n"
                + "    current pointer: " + 0 + "\n"
                + "[0 persons]";
        assertEquals(versionedBobaBot.toString(), expectedMessage);
    }

    @Test
    public void toStringAfterOneCommit() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        String expectedMessage = "VersionedBobaBot: \n"
                + "    size limit: " + 20 + "\n"
                + "    current pointer: " + 1 + "\n"
                + "[0 persons, 1 persons]";
        assertEquals(versionedBobaBot.toString(), expectedMessage);
    }

    @Test
    public void commitAfterAnUndo() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt);
        BobaBot editedBobaBot = new BobaBot();
        editedBobaBot.addPerson(ALICE);
        versionedBobaBot.commit(editedBobaBot);
        versionedBobaBot.undo(editedBobaBot);
        editedBobaBot.addPerson(CARL);
        versionedBobaBot.commit(editedBobaBot);
        assertEquals(versionedBobaBot.getStateListSize(), 2);
        assertEquals(versionedBobaBot.getCurrentStatePointer(), 1);
    }
}
