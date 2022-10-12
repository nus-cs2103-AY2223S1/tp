package seedu.travelr.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newTrip_success() {
        Trip validTrip = new TripBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTrip(validTrip);

        assertCommandSuccess(new AddCommand(validTrip), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTrip), expectedModel);
    }

    @Test
    public void execute_duplicateTrip_throwsCommandException() {
        Trip tripInList = model.getAddressBook().getTripList().get(0);
        assertCommandFailure(new AddCommand(tripInList), model, AddCommand.MESSAGE_DUPLICATE_TRIP);
    }

}
