package seedu.rc4hdb.logic.commands.venuecommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.venues.VenueName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code VenueDeleteCommand}.
 */
public class VenueDeleteCommandTest {

    private Model model = new ModelManager(new ResidentBook(), getTypicalVenueBook(), new UserPrefs());

    @Test
    public void execute_validVenueName_success() {

        VenueName validVenueName = new VenueName("Hall");
        VenueDeleteCommand deleteCommand = new VenueDeleteCommand(validVenueName);

        String expectedMessage = String.format(VenueDeleteCommand.MESSAGE_SUCCESS, validVenueName);

        ModelManager expectedModel = new ModelManager(model.getResidentBook(), model.getVenueBook(), new UserPrefs());
        expectedModel.deleteVenue(validVenueName);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidVenueName_throwsCommandException() {
        VenueName invalidVenueName = new VenueName("Hell");
        VenueDeleteCommand deleteCommand = new VenueDeleteCommand(invalidVenueName);

        String expectedMessage = String.format(VenueDeleteCommand.MESSAGE_VENUE_NOT_FOUND, invalidVenueName);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

}

