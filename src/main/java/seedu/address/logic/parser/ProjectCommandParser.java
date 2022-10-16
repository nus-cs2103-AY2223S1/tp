package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.logic.commands.project.DeleteProjectCommand;
import seedu.address.logic.commands.project.EditProjectCommand;
import seedu.address.logic.commands.project.ListProjectCommand;
import seedu.address.logic.commands.project.ProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
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
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_CLIENT_ID,
                        PREFIX_REPOSITORY, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProjectCommand.MESSAGE_ADD_PROJECT_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Client client;
        Repository repository;
        Deadline deadline;

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_ID)) {
            client = Client.EmptyClient.EMPTY_CLIENT;
        } else {
            client = ParserUtil.parseClient(argMultimap.getValue(PREFIX_CLIENT_ID).get());
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_REPOSITORY)) {
            repository = Repository.EmptyRepository.EMPTY_REPOSITORY;
        } else {
            repository = ParserUtil.parseRepository(argMultimap.getValue(PREFIX_REPOSITORY).get());
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            deadline = Deadline.EmptyDeadline.EMPTY_DEADLINE;
        } else {
            deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        List<Issue>  issueList = new ArrayList<>();
        ProjectId projectId = new ProjectId(HasIntegerIdentifier.generateNextID(AddressBook.get().getProjectList()));
        ObservableList<Project> projList = AddressBook.get().getProjectList();
        Project project = new Project(name, repository, deadline, client, issueList, projectId);

        return new AddProjectCommand(project);
    }

    //TODO: implement
    private EditProjectCommand parseEditProjectCommand(String arguments) {
        return null;
    }

    private DeleteProjectCommand parseDeleteProjectCommand(String arguments) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new DeleteProjectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE), pe);
        }
    }

    private ListProjectCommand parseListProjectCommand(String arguments) {
        return new ListProjectCommand();
    }
}
