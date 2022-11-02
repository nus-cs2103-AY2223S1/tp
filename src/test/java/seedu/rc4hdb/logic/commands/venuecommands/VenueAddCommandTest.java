package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.ModelStub;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class VenueAddCommandTest {

    @Test
        public void constructor_nullVenueName_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new VenueAddCommand(null));
        }

        @Test
        public void execute_venueNameAcceptedByModel_addSuccessful() throws Exception {
            ModelStubAcceptingVenueAdded modelStub = new ModelStubAcceptingVenueAdded();
            Venue validVenue = new ResidentBuilder().build();

            CommandResult commandResult = new AddCommand(validResident).execute(modelStub);

            assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validResident), commandResult.getFeedbackToUser());
            assertEquals(Arrays.asList(validResident), modelStub.residentsAdded);
        }

        @Test
        public void execute_duplicatePerson_throwsCommandException() {
            Resident validResident = new ResidentBuilder().build();
            AddCommand addCommand = new AddCommand(validResident);
            ModelStub modelStub = new ModelStubWithResident(validResident);

            assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_RESIDENT, ()
                    -> addCommand.execute(modelStub));
        }

        @Test
        public void equals() {
            Resident alice = new ResidentBuilder().withName("Alice").build();
            Resident bob = new ResidentBuilder().withName("Bob").build();
            AddCommand addAliceCommand = new AddCommand(alice);
            AddCommand addBobCommand = new AddCommand(bob);

            // same object -> returns true
            assertTrue(addAliceCommand.equals(addAliceCommand));

            // same values -> returns true
            AddCommand addAliceCommandCopy = new AddCommand(alice);
            assertTrue(addAliceCommand.equals(addAliceCommandCopy));

            // different types -> returns false
            assertFalse(addAliceCommand.equals(1));

            // null -> returns false
            assertFalse(addAliceCommand.equals(null));

            // different person -> returns false
            assertFalse(addAliceCommand.equals(addBobCommand));
        }

        //======================== Start of model stubs ===============================================

        /**
         * A Model stub that contains a single resident.
         */
        public static class ModelStubWithResident extends ModelStub {
            private final Resident resident;

            /**
             * Constructs a model stub with a single resident.
             */
            public ModelStubWithResident(Resident resident) {
                requireNonNull(resident);
                this.resident = resident;
            }

            @Override
            public boolean hasResident(Resident resident) {
                requireNonNull(resident);
                return this.resident.isSameResident(resident);
            }
        }

        /**
         * A Model stub that always accept the resident being added.
         */
        public static class ModelStubAcceptingResidentAdded extends ModelStub {
            public final ArrayList<Resident> residentsAdded = new ArrayList<>();

            @Override
            public boolean hasResident(Resident resident) {
                requireNonNull(resident);
                return residentsAdded.stream().anyMatch(resident::isSameResident);
            }

            @Override
            public void addResident(Resident resident) {
                requireNonNull(resident);
                residentsAdded.add(resident);
            }

            @Override
            public ReadOnlyResidentBook getResidentBook() {
                return new ResidentBook();
            }
        }
}
