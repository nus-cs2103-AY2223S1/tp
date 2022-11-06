package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.team.Link;

public class ModelStubWithLink extends ModelStub {
    private final Link link;

    public ModelStubWithLink(Link link) {
        requireNonNull(link);
        this.link = link;
    }

    @Override
    public boolean hasLink(Link link) {
        requireNonNull(link);
        return this.link.isSameLink(link);
    }


}
