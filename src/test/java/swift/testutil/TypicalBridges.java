package swift.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import swift.model.AddressBook;
import swift.model.bridge.PersonTaskBridge;

/**
 * A utility class containing a list of {@code PersonTaskBridge} objects to be
 * used in tests.
 */
public class TypicalBridges {
    public static final String PERSON_ID_1 = "c4c645da-27b3-454d-9428-5295a6ee1f33";
    public static final String PERSON_ID_2 = "42d68acf-f3db-4c55-bbe6-4a3d1db64ac0";
    public static final String PERSON_ID_3 = "6a5f8ed7-2607-47d1-9faf-bf2437642985";
    public static final String TASK_ID_1 = "bfbf250c-fd58-49b4-be15-ca12095ca2ee";
    public static final String TASK_ID_2 = "41478d11-dbba-441f-ab0f-99ddf097eac1";
    public static final String TASK_ID_3 = "384308a6-6c01-4e7a-a9eb-8147f6502764";
    public static final String TASK_ID_4 = "8aa2ee94-728a-47b5-8864-474ea71612fb";

    public static final PersonTaskBridge DEFAULT_BRIDGE_1 = new PersonTaskBridge(
            UUID.fromString(PERSON_ID_1),
            UUID.fromString(TASK_ID_1));
    public static final PersonTaskBridge DEFAULT_BRIDGE_2 = new PersonTaskBridge(
            UUID.fromString(PERSON_ID_1),
            UUID.fromString(TASK_ID_2));
    public static final PersonTaskBridge DEFAULT_BRIDGE_3 = new PersonTaskBridge(
            UUID.fromString(PERSON_ID_2),
            UUID.fromString(TASK_ID_3));
    public static final PersonTaskBridge DEFAULT_BRIDGE_4 = new PersonTaskBridge(
            UUID.fromString(PERSON_ID_3),
            UUID.fromString(TASK_ID_4));

    private TypicalBridges() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = TypicalTasks.getTypicalAddressBook();
        ab.setBridges(getTypicalBridges());
        return ab;
    }

    public static List<PersonTaskBridge> getTypicalBridges() {
        return new ArrayList<>(Arrays.asList(DEFAULT_BRIDGE_1, DEFAULT_BRIDGE_2, DEFAULT_BRIDGE_3, DEFAULT_BRIDGE_4));
    }
}
