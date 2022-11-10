package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.value.ChangeListener;

public class ObservableObjectTest {
    private ObservableObject<String> observableObject;
    private ArrayList<String> callHistory;
    private ChangeListener<? super String> listener;

    @BeforeEach
    public void setUp() {
        observableObject = new ObservableObject<>();
        callHistory = new ArrayList<>();
        listener = (observable, oldValue, newValue) -> callHistory.add(newValue);
    }

    @Test
    void testNoListener() {
        assertNull(observableObject.getValue());
        observableObject.setValue("test");
        assertEquals("test", observableObject.getValue());
        assertEquals(0, callHistory.size());
    }

    @Test
    void testAddListener() {
        assertNull(observableObject.getValue());
        observableObject.addListener(listener);
        observableObject.setValue("test");
        assertEquals(1, callHistory.size());
        assertEquals("test", callHistory.get(0));
    }

    @Test
    void testRemoveListener() {
        assertNull(observableObject.getValue());
        observableObject.addListener(listener);
        observableObject.setValue("test");
        assertEquals(1, callHistory.size());
        assertEquals("test", callHistory.get(0));
        observableObject.removeListener(listener);
        observableObject.setValue("test1");
        assertEquals(1, callHistory.size());
    }
}
