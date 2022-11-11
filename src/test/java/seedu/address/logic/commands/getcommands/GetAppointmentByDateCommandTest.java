package seedu.address.logic.commands.getcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AppointmentByDatePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetAppointmentByDateCommand}.
 */
public class GetAppointmentByDateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentByDatePredicate firstPredicate =
                new AppointmentByDatePredicate(Collections.singletonList(LocalDate.of(2022, Month.APRIL, 12)));
        AppointmentByDatePredicate secondPredicate =
                new AppointmentByDatePredicate(Collections.singletonList(LocalDate.of(2022, Month.MAY, 12)));

        GetAppointmentByDateCommand getFirstAppointmentByDateCommand = new GetAppointmentByDateCommand(firstPredicate);
        GetAppointmentByDateCommand getSecondAppointmentByDateCommand =
                new GetAppointmentByDateCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstAppointmentByDateCommand.equals(getFirstAppointmentByDateCommand));

        // same values -> returns true
        GetAppointmentByDateCommand getFirstAppointmentByDateCommandCopy =
                new GetAppointmentByDateCommand(firstPredicate);
        assertTrue(getFirstAppointmentByDateCommand.equals(getFirstAppointmentByDateCommandCopy));

        // different types -> returns false
        assertFalse(getFirstAppointmentByDateCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstAppointmentByDateCommand.equals(null));

        // different appointment by date command -> returns false
        assertFalse(getFirstAppointmentByDateCommand.equals(getSecondAppointmentByDateCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        AppointmentByDatePredicate predicate = preparePredicate(" ");
        GetAppointmentByDateCommand command = new GetAppointmentByDateCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        AppointmentByDatePredicate predicate = preparePredicate(LocalDate.of(2022, Month.JUNE, 14).toString());
        GetAppointmentByDateCommand command = new GetAppointmentByDateCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentByDatePredicate}.
     */
    private AppointmentByDatePredicate preparePredicate(String userInput) {
        String[] st = userInput.split("\\s+");
        List<LocalDate> dates = new ArrayList<>();
        for (String i : st) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(i.trim(), dateTimeFormatter);
            dates.add(date);
        }
        return new AppointmentByDatePredicate(dates);
    }
}
