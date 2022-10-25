package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;

/**
 * Edits the currently set team.
 */
public class EditTeamCommand extends Command {
    public static final String COMMAND_WORD = "edit_team";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the current team \n"
            + "Parameters: "
            + "[-" + FLAG_NAME_STR + " TEAM_NAME] "
            + "[-" + FLAG_DESCRIPTION_STR + " TEAM_DESCRIPTION] \n"
            + "Example:\n"
            + "1. " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " CS2103T "
            + "-" + FLAG_DESCRIPTION_STR + " \"A team to manage CS2103T\"\n"
            + "2. " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " CS2102 ";

    public static final String MESSAGE_EDIT_TEAM_SUCCESS = "Edited team: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_TEAM = "This team name already exists in the address book.";

    private final EditTeamDescriptor editTeamDescriptor;

    /**
     * Creates an EditTeamCommand to edit a {@code Team}.
     *
     * @param editTeamDescriptor details to edit the link with
     */
    public EditTeamCommand(EditTeamDescriptor editTeamDescriptor) {
        requireNonNull(editTeamDescriptor);
        this.editTeamDescriptor = editTeamDescriptor;
    }

    /**
     * Creates and returns a {@code Team} with the details of {@code teamToEdit}
     * edited with {@code editTeamDescriptor}.
     */
    private static Team createEditedTeam(Team teamToEdit, EditTeamDescriptor editTeamDescriptor) {
        assert editTeamDescriptor != null;

        String updatedName = editTeamDescriptor.getName().orElse(teamToEdit.getTeamName());
        String updatedDescription = editTeamDescriptor.getDescription().orElse(teamToEdit.getDescription());

        return new Team(updatedName, updatedDescription);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Team currentTeam = model.getTeam();
        Team editedTeam = createEditedTeam(currentTeam, editTeamDescriptor);
        List<Team> teamList = model.getTeamList();
        List<Team> teamListCopy = new ArrayList<>();
        teamListCopy.addAll(teamList);


        if ((!editedTeam.isSameTeam(currentTeam)) && (teamListCopy.contains(editedTeam))) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }

        int currentIndex = teamListCopy.indexOf(currentTeam);
        teamListCopy.set(currentIndex, editedTeam);
        model.setTeam(editedTeam);
        model.setTeams(teamListCopy);

        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_SUCCESS, editedTeam));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeamCommand)) {
            return false;
        }

        // state check
        EditTeamCommand e = (EditTeamCommand) other;
        return editTeamDescriptor.equals(e.editTeamDescriptor);
    }

    /**
     * Stores the details to edit the team with. Each non-empty field value will replace the
     * corresponding field value of the team.
     */
    public static class EditTeamDescriptor {
        private String name;

        private String description;

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

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTeamDescriptor)) {
                return false;
            }

            // state check
            EditTeamDescriptor e = (EditTeamDescriptor) other;

            return getName().equals(e.getName()) && getDescription().equals(e.getDescription());
        }

    }
}
