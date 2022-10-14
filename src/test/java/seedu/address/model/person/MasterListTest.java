package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBuyers;

public class MasterListTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());

    @Test
    public void clear() {
        MasterList masterList = new MasterList();
        masterList.addAll(new FilteredList<>(bModel.getFilteredBuyerList()));
        masterList.clear();
        assertEquals(masterList.getMasterList().size(), 0);
        assertEquals(masterList.getMasterList(), new ArrayList<Object>());
    }

    @Test
    public void addAll() {
        MasterList masterList = new MasterList();
        masterList.addAll(new FilteredList<>(bModel.getFilteredBuyerList()));
        assertEquals(masterList.getMasterList().size(), bModel.getFilteredBuyerList().size());
        assertEquals(masterList.getMasterList(), bModel.getFilteredBuyerList());
    }
}
