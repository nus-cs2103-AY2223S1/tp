package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.team.Link;

/**
 * A Model stub that contains a single link.
 */
public class ModelStubWithLink extends ModelStub {
    private final Link link;

    /**
     * Creates a new ModelStub with single link.
     */
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
