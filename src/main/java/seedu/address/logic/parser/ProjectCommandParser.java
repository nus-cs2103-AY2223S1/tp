package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.logic.commands.project.DeleteProjectCommand;
import seedu.address.logic.commands.project.EditProjectCommand;
import seedu.address.logic.commands.project.ListProjectCommand;
import seedu.address.logic.commands.project.ProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.model.project.Repository;


/**
 * A parser to parse any commands related to project
 */
public class ProjectCommandParser implements Parser<ProjectCommand> {
    /**
     * Parse any commands that have to do with Projects
     *
     * @param flag      flag used in command
     * @param arguments arguments used in command
     * @return a ProjectCommand
     * @throws ParseException
     */
    @Override
    public ProjectCommand parse(String flag, String arguments) throws ParseException {
        // Strip is just there for good measure.
        switch (flag.strip()) {
        case AddProjectCommand.COMMAND_FLAG:
            return parseAddProjectCommand(arguments);
        case EditProjectCommand.COMMAND_FLAG:
            return parseEditProjectCommand(arguments);
        case DeleteProjectCommand.COMMAND_FLAG:
            return parseDeleteProjectCommand(arguments);
        case ListProjectCommand.COMMAND_FLAG:
            return parseListProjectCommand(arguments);
        default:
            throw new ParseException(FLAG_UNKNOWN_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private AddProjectCommand parseAddProjectCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_CLIENT, PREFIX_REPOSITORY, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProjectCommand.MESSAGE_ADD_PROJECT_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Name clientName = ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT).get());
        Repository repository = ParserUtil.parseRepository(argMultimap.getValue(PREFIX_REPOSITORY).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        List<Issue> issueList = new ArrayList<>();

        Project project = new Project(name, repository, deadline, null, issueList, null);

        return new AddProjectCommand(project);
    }

    //TODO: implement
    private EditProjectCommand parseEditProjectCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private DeleteProjectCommand parseDeleteProjectCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private ListProjectCommand parseListProjectCommand(String arguments) {
        return null;
    }
}
