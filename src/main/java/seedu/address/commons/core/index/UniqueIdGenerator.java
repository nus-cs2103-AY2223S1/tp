package seedu.address.commons.core.index;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates a unique ID and increments the current ID.
 */
public class UniqueIdGenerator {

    private static final char START = 'a';
    private static final char BASE = 26;
    private static final Set<UniqueId> storedIdOrder = new HashSet<>();
    private static final Set<UniqueId> storedIdPet = new HashSet<>();
    private final List<Character> currentId = new ArrayList<>();

    /**
     * Constructs a unique id generator starting from the base id.
     */
    public UniqueIdGenerator() {
        currentId.add(START);
    }

    /**
     * Returns the current id and increments.
     *
     * @return A unique ID.
     */
    public UniqueId next() {
        StringBuilder sb = new StringBuilder();
        currentId.forEach(x -> sb.insert(0, x));
        String id = sb.toString();
        increment();
        return new UniqueId(id);
    }

    /**
     * Adds a unique id to the storedIdPet
     */
    public static boolean addToStoredIdPet(UniqueId id) {
        return storedIdPet.add(id);
    }

    /**
     * Checks if the storedIdPet set contains a given id.
     */
    public static boolean storedIdPetContains(UniqueId id) {
        return storedIdPet.contains(id);
    }

    /**
     * Adds a unique id to the storedIdOrder.
     */
    public static boolean addToStoredIdOrder(UniqueId id) {
        return storedIdOrder.add(id);
    }

    /**
     * Checks if the storedIdOrder set contains a given id.
     */
    public static boolean storedIdOrderContains(UniqueId id) {
        return storedIdOrder.contains(id);
    }


    private void increment() {
        int idx = 0;
        boolean carry = false;
        do {
            char cur = currentId.get(idx);
            cur += 1;
            if (cur == START + BASE) {
                carry = true;
                cur = START;
            }
            currentId.set(idx, cur);
            if (carry) {
                idx++;
            }
        } while (carry && idx < currentId.size());
        if (carry && idx == currentId.size()) {
            currentId.add(START);
        }
    }
}
