package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Uid;

public class UidManagerTest {

    @Test
    public void execute_produceUid_success() {
        UidManager manager = new UidManager();
        ArrayList<Uid> arr = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arr.add(manager.produceUid());
        }
        List<Uid> arr2 = arr.stream().distinct().collect(Collectors.toList());
        assertEquals(arr.size(), arr2.size());
    }
}
