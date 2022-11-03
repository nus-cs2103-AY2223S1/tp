// package seedu.uninurse.logic.parser;

// import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
// import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
// import static seedu.uninurse.testutil.Assert.assertThrows;
// import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
// import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

// import org.junit.jupiter.api.Test;

// import seedu.uninurse.logic.commands.EditTaskCommand;

/**
 * Contains unit tests for {@code EditTaskCommandParser}.
 */
// class EditTaskCommandParserTest {

//     private final EditTaskCommandParser parser = new EditTaskCommandParser();
//     private final String nonEmptyTask = "Some task";

//     @Test
//     public void parse_nullArgs_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> parser.parse(null));
//     }

    // @Test
    // public void parse_patientIndexSpecifiedTaskIndexSpecified_success() {
    //     String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
    //             + PREFIX_TASK_DESCRIPTION + nonEmptyTask + " | " + DATE_TIME_STRING;

    //     EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
    //             new Task(nonEmptyTask, new DateTime(DATE_TIME_STRING)));

    //     assertParseSuccess(parser, userInput, expectedCommand);
    // }

//     @Test
//     public void parse_patientIndexMissingTaskIndexSpecified_failure() {
//         String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + nonEmptyTask;

//         String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

//         assertParseFailure(parser, userInput, expectedMessage);
//     }

//     @Test
//     public void parse_patientIndexSpecifiedTaskIndexMissing_failure() {
//         String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + nonEmptyTask;

//         String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

//         assertParseFailure(parser, userInput, expectedMessage);
//     }

    // @Test
    // public void parse_emptyTaskEdit_failure() {
    //     String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
    //             + PREFIX_TASK_DESCRIPTION;

    //     String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    //     assertParseFailure(parser, userInput, expectedMessage);
    // }

//     @Test
//     public void parse_taskPrefixMissing_failure() {
//         String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " ";

//         String expectedMessage = String.format(EditTaskCommand.MESSAGE_NOT_EDITED);

//         assertParseFailure(parser, userInput, expectedMessage);
//     }
// }
