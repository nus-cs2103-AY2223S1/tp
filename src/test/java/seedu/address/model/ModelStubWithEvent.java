package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.event.Event;

/**
 * A model stub containing a single event.
 */
public class ModelStubWithEvent extends ModelStub {
    private final Event event;

    /**
     * Every field must be present and not null.
     */
    public ModelStubWithEvent(Event event) {
        requireNonNull(event);
        this.event = event;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return this.event.isSameEvent(event);
    }
}
