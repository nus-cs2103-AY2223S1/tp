package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_1;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_2;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_3;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;
import seedu.address.model.util.FindMeetingFunctionalInterface;


public class FindMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void equals() {
        MeetingContainsKeywordsPredicate descriptionPredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("first"), Meeting::getDescription);
        MeetingContainsKeywordsPredicate locationPredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("second"), Meeting::getLocation);
        MeetingContainsKeywordsPredicate peoplePredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("third"),
                        Meeting::getPeopleToMeetAsString);

        FindMeetingCommand findDescriptionMeetingCommand = new FindMeetingCommand(descriptionPredicate);
        FindMeetingCommand findLocationMeetingCommand = new FindMeetingCommand(locationPredicate);
        FindMeetingCommand findPeopleMeetingCommand = new FindMeetingCommand(peoplePredicate);

        // same object -> returns true
        assertEquals(findDescriptionMeetingCommand, findDescriptionMeetingCommand);

        // same text and same functional interface -> returns true
        FindMeetingCommand findDescriptionMeetingCommandCopy = new FindMeetingCommand(descriptionPredicate);
        assertEquals(findDescriptionMeetingCommand, findDescriptionMeetingCommandCopy);

        // different types -> returns false
        assertNotEquals(0, findDescriptionMeetingCommand);

        // null -> returns false
        assertNotEquals(null, findDescriptionMeetingCommandCopy);

        // different keywords & functional interface -> returns false
        assertNotEquals(findLocationMeetingCommand, findDescriptionMeetingCommandCopy);
        assertNotEquals(findLocationMeetingCommand, findPeopleMeetingCommand);
        assertNotEquals(findDescriptionMeetingCommandCopy, findPeopleMeetingCommand);

        // same keywords & different functional interface -> returns false
        MeetingContainsKeywordsPredicate findFirstLocationPredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("first"), Meeting::getLocation);
        FindMeetingCommand findFirstLocationMeetingCommand = new FindMeetingCommand(findFirstLocationPredicate);
        assertNotEquals(findDescriptionMeetingCommand, findFirstLocationMeetingCommand);

        // different keywords & same functional interface -> returns false
        MeetingContainsKeywordsPredicate findDescriptionPredicateCopy =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("different"), Meeting::getDescription);
        FindMeetingCommand findDescriptionMeetingCommandDiff = new FindMeetingCommand(findDescriptionPredicateCopy);
        assertNotEquals(findDescriptionMeetingCommand, findDescriptionMeetingCommandDiff);
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        MeetingContainsKeywordsPredicate descriptionPredicate = preparePredicate(" " , "Description");
        FindMeetingCommand findMeetingCommand = new FindMeetingCommand(descriptionPredicate);
        expectedModel.updateFilteredMeetingList(descriptionPredicate);
        assertCommandSuccess(findMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFoundDescription() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 3);
        MeetingContainsKeywordsPredicate descriptionPredicate =
                preparePredicate("CS1101S CS2040S CS2103", "Description");
        FindMeetingCommand findMeetingCommand = new FindMeetingCommand(descriptionPredicate);
        expectedModel.updateFilteredMeetingList(descriptionPredicate);
        assertCommandSuccess(findMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_TYPICAL_1, MEETING_TYPICAL_2, MEETING_TYPICAL_3),
                model.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFoundLocation() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 3);
        MeetingContainsKeywordsPredicate descriptionPredicate =
                preparePredicate("DECK UTOWN COM1", "Location");
        FindMeetingCommand findMeetingCommand = new FindMeetingCommand(descriptionPredicate);
        expectedModel.updateFilteredMeetingList(descriptionPredicate);
        assertCommandSuccess(findMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_TYPICAL_1, MEETING_TYPICAL_2, MEETING_TYPICAL_3),
                model.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFoundPeople() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 3);
        MeetingContainsKeywordsPredicate descriptionPredicate =
                preparePredicate("ALICE ELLE DANIEL", "People");
        FindMeetingCommand findMeetingCommand = new FindMeetingCommand(descriptionPredicate);
        expectedModel.updateFilteredMeetingList(descriptionPredicate);
        assertCommandSuccess(findMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_TYPICAL_1, MEETING_TYPICAL_2, MEETING_TYPICAL_3),
                model.getFilteredMeetingList());
    }

    /**
     * Parses {@code userInput and type} into a {@code MeetingContainsKeywordsPredicate}.
     */
    private MeetingContainsKeywordsPredicate preparePredicate(String userInput, String type) {
        FindMeetingFunctionalInterface functionalInterface;
        if (type.equals("Description")) {
            functionalInterface = FindMeetingCommand.GET_DESCRIPTION;
        } else if (type.equals("Location")) {
            functionalInterface = FindMeetingCommand.GET_LOCATION;
        } else {
            functionalInterface = FindMeetingCommand.GET_PEOPLE;
        }
        return new MeetingContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")), functionalInterface);
    }

}
