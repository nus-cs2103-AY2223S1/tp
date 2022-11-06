package seedu.address.logic.parser.task;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.ListTasksCommand;
import seedu.address.logic.parser.CliSyntax;
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
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                )
        );

        assertParseSuccess(
                parser,
                String.format(" %s1", CliSyntax.PREFIX_CONTACT),
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE))
                )
        );


        assertParseSuccess(
                parser,
                String.format(" hi %s1 %s2", CliSyntax.PREFIX_CONTACT, CliSyntax.PREFIX_CONTACT),
                new ListTasksCommand(
                        "hi",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE))
                )
        );
    }

    @Test
    public void parse_validArgsWithFlags() {
        String command1 = String.format(" hi %s1 %s2 -%s",
                CliSyntax.PREFIX_CONTACT,
                CliSyntax.PREFIX_CONTACT,
                ListTasksCommand.COMPLETED_FLAG);
        assertParseSuccess(
                parser,
                command1,
                new ListTasksCommand(
                        "hi",
                        List.of(),
                        List.of(ListTasksCommand.COMPLETED_FLAG),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE))
                )
        );

        String command2 = String.format(" hi %s1 %s2 -%s",
                CliSyntax.PREFIX_CONTACT,
                CliSyntax.PREFIX_CONTACT,
                ListTasksCommand.ALL_FLAG);
        assertParseSuccess(
                parser,
                command2,
                new ListTasksCommand(
                        "hi",
                        List.of(),
                        List.of(ListTasksCommand.ALL_FLAG),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE))
                )
        );
    }

    @Test
    public void parse_nlp_deadlines() {
        assertParseSuccess(
                parser,
                String.format(" %s tomorrow", CliSyntax.PREFIX_BEFORE),
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.of(Deadline.of(LocalDate.now().plusDays(1))),
                        Optional.empty(),
                        new HashSet<>()
                )
        );

        assertParseSuccess(
                parser,
                String.format(" %s today", CliSyntax.PREFIX_AFTER),
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.of(Deadline.of(LocalDate.now())),
                        new HashSet<>()
                )
        );
    }

}
