package seedu.address.commons.core.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a unique ID and increments the current ID.
 */
public class UniqueIdGenerator {

    private static final char START = 'a';
    private static final char BASE = 26;
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
     * @return A unqiue ID.
     */
    public UniqueId next() {
        StringBuilder sb = new StringBuilder();
        currentId.forEach(x -> sb.insert(0, x));
        String id = sb.toString();
        increment();
        return new UniqueId(id);
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
