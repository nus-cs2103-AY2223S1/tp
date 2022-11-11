package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_DESCRIPTION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TEAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TEAM_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Description;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Edits the currently set team.
 */
@CommandLine.Command(name = EditTeamCommand.COMMAND_WORD,
        aliases = {EditTeamCommand.ALIAS}, mixinStandardHelpOptions = true)
public class EditTeamCommand extends Command {
    public static final String COMMAND_WORD = "team";
    public static final String ALIAS = "te";
    public static final String FULL_COMMAND = EditCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to edit a team's details.\n";

    public static final String MESSAGE_EDIT_TEAM_SUCCESS = "Edited team: %1$s";
    public static final String MESSAGE_DUPLICATE_TEAM = "This team name already exists in the TruthTable.";

    private final EditTeamDescriptor editTeamDescriptor;

    @CommandLine.ArgGroup(exclusive = false, multiplicity = "1")
    private Arguments arguments;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public EditTeamCommand() {
        this.editTeamDescriptor = new EditTeamDescriptor();
    }

    /**
     * Creates and returns a {@code Team} with the details of {@code teamToEdit}
     * edited with {@code editTeamDescriptor}.
     */
    private static Team createEditedTeam(Team teamToEdit, EditTeamDescriptor editTeamDescriptor) {
        assert editTeamDescriptor != null;

        TeamName updatedName = editTeamDescriptor.getName().orElse(teamToEdit.getTeamName());
        Description updatedDescription = editTeamDescriptor.getDescription().orElse(teamToEdit.getDescription());
        List<Person> members = teamToEdit.getTeamMembers();
        List<Task> tasks = teamToEdit.getTaskList();
        List<Link> links = teamToEdit.getLinkList();

        return new Team(updatedName, updatedDescription, members, tasks, links);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);

        if (arguments.name != null) {
            editTeamDescriptor.name = arguments.name;
        }
        if (arguments.description != null) {
            editTeamDescriptor.description = arguments.description;
        }

        Team currentTeam = model.getTeam();
        Team editedTeam = createEditedTeam(currentTeam, editTeamDescriptor);
        List<Team> teamList = model.getTeamList();
        List<Team> teamListCopy = new ArrayList<>(teamList);

        List<Team> filteredListWithTargetTeam = teamList.stream()
                .filter(editedTeam::isSameTeam).collect(Collectors.toList());

        if ((!editedTeam.isSameTeam(currentTeam)) && (filteredListWithTargetTeam.size() != 0)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }

        int currentIndex = teamListCopy.indexOf(currentTeam);
        teamListCopy.set(currentIndex, editedTeam);
        model.setTeam(editedTeam);
        model.setTeams(teamListCopy);

        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_SUCCESS, editedTeam));
    }


    private static class Arguments {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG},
                paramLabel = LABEL_TEAM_NAME,
                description = FLAG_TEAM_NAME_DESCRIPTION)
        private TeamName name;

        @CommandLine.Option(names = {FLAG_DESCRIPTION_STR, FLAG_DESCRIPTION_LONG},
                paramLabel = LABEL_TEAM_DESCRIPTION,
                description = FLAG_TEAM_DESCRIPTION_DESCRIPTION)
        private Description description;

    }

    /**
     * Stores the details to edit the team with. Each non-empty field value will replace the
     * corresponding field value of the team.
     */
    public static class EditTeamDescriptor {
        private TeamName name;

        private Description description;

        public EditTeamDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditTeamDescriptor(EditTeamDescriptor toCopy) {
            setName(toCopy.name);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, description);
        }

        public Optional<TeamName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(TeamName name) {
            this.name = name;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

    }
}
