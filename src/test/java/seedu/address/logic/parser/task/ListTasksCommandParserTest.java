package seedu.address.logic.parser.task;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.ListTasksCommand;
import seedu.address.model.task.Deadline;

public class ListTasksCommandParserTest {

    private ListTasksCommandParser parser = new ListTasksCommandParser();

    @Test
    public void parse_validArgs_returnsListTasksCommand() {
        assertParseSuccess(
                parser,
                "  ",
                new ListTasksCommand(
                        "",
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                        )
        );
        assertParseSuccess(
                parser,
                " hi",
                new ListTasksCommand(
                        "hi",
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                )
        );

        assertParseSuccess(
                parser,
                " c/1",
                new ListTasksCommand(
                        "",
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON))
                )
        );


        assertParseSuccess(
                parser,
                " hi c/1 c/2",
                new ListTasksCommand(
                        "hi",
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON))
                )
        );
    }

    @Test
    public void parse_validArgsWithFlags() {
        assertParseSuccess(
                parser,
                " hi c/1 c/2 -c",
                new ListTasksCommand(
                        "hi",
                        List.of(ListTasksCommand.COMPLETED_FLAG),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON))
                )
        );

        assertParseSuccess(
                parser,
                " hi c/1 c/2 -a",
                new ListTasksCommand(
                        "hi",
                        List.of(ListTasksCommand.ALL_FLAG),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON))
                )
        );
    }

    @Test
    public void parse_nlp_deadlines() {
        assertParseSuccess(
                parser,
                " before tomorrow",
                new ListTasksCommand(
                        "",
                        List.of(),
                        Optional.of(Deadline.of(LocalDate.now().plusDays(1))),
                        Optional.empty(),
                        new HashSet<>()
                )
        );

        assertParseSuccess(
                parser,
                " after today",
                new ListTasksCommand(
                        "",
                        List.of(),
                        Optional.empty(),
                        Optional.of(Deadline.of(LocalDate.now())),
                        new HashSet<>()
                )
        );
    }

}
