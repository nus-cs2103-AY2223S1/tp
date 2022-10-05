package seedu.address.model.module.schedule;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.exceptions.ConflictSlotException;
import seedu.address.model.module.schedule.exceptions.DuplicateSlotException;
/**
 * Represents a schedule of a module
 */
public class Schedule {
    private final List<Slot> slots;
    private final Module module;

    /**
     * Requires module not to e null
     * @param module the module that has this schedule
     */
    public Schedule(Module module) {
        requireNonNull(module);
        this.module = module;
        this.slots = new ArrayList<>();
    }

    /**
     * Checks if the new slot is conflict with any existing slot
     * @param newSlot new slot to add
     * @return conflict or not
     */
    private boolean isConflict(Slot newSlot) {
        requireNonNull(newSlot);
        return slots.stream().anyMatch(newSlot::isOverlap);
    }

    /**
     * Adds a new slot to the slot list
     * @param newSlot new slot to add
     * @throws DuplicateSlotException
     * @throws ConflictSlotException
     */
    public void addSlot(Slot newSlot) throws DuplicateSlotException, ConflictSlotException {
        if (slots.contains(newSlot)) {
            throw new DuplicateSlotException();
        }
        if (isConflict(newSlot)) {
            throw new ConflictSlotException();
        }
        slots.add(newSlot);
    }

    /**
     * Obtains all the slots
     * @return slot list
     */
    public List<Slot> getSlots() {
        return slots;
    }
}
