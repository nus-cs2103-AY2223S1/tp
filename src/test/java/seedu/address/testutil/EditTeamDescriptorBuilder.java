package seedu.address.testutil;

import seedu.address.logic.commands.EditTeamCommand.EditTeamDescriptor;
import seedu.address.model.team.Team;

public class EditTeamDescriptorBuilder {
    private EditTeamDescriptor descriptor;

    public EditTeamDescriptorBuilder() {
        descriptor = new EditTeamDescriptor();
    }

    public EditTeamDescriptorBuilder(EditTeamDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Returns an {@code EditTeamDescriptor} with fields containing {@code team}'s details
     * @param team
     */
    public EditTeamDescriptorBuilder(Team team) {
        descriptor = new EditTeamDescriptor();
        descriptor.setName(team.getTeamName());
        descriptor.setDescription(team.getDescription());
    }

    /**
     * Sets the {@code name} of the {@code EditTeamDescriptor} that we are building.
     */
    public EditTeamDescriptorBuilder withName(String name) {
        descriptor.setName(name);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code EditTeamDescriptor} that we are building.
     */
    public EditTeamDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    public EditTeamDescriptor build() {
        return descriptor;
    }


}
