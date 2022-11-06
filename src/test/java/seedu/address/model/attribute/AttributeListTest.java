package seedu.address.model.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttributes.AGE;
import static seedu.address.testutil.TypicalAttributes.POSITION;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.attribute.exceptions.AttributeException;

class AttributeListTest {

    private Attribute<ArrayList<?>> objectAttribute =
            new AbstractAttribute<>("Object", new ArrayList<Object>()) { };


    @Test
    void createAttributeInstance() {
        AttributeList attributeList = new AttributeList();

        // Valid arguments
        assertEquals(attributeList.createAttributeInstance("Position", "CEO"), POSITION);
        assertEquals(attributeList.createAttributeInstance("Age", 20), AGE);

        List<Object> sampleList = new ArrayList<>();
        assertEquals(attributeList.createAttributeInstance("objectTest", sampleList),
                new AbstractAttribute<>("Objecttest", sampleList) { });
    }

    @Test
    void addAttribute_stringAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        assertNotNull(attributeList.findAttribute("Position"));
    }

    @Test
    void addAttribute_integerAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(AGE);
        assertNotNull(attributeList.findAttribute("Age"));
    }

    @Test
    void addAttribute_objectAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(objectAttribute);
        assertNotNull(attributeList.findAttribute("Object"));
    }

    @Test
    void addAttribute_stringValue_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Department", "Marketing");
        assertNotNull(attributeList.findAttribute("Department"));
    }

    @Test
    void addAttribute_integerValue_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Age", 23);
        assertNotNull(attributeList.findAttribute("Age"));
    }

    @Test
    void addAttribute_existingAttribute_throwsAttributeException() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Age", 23);
        assertThrows(AttributeException.class, () -> attributeList.addAttribute("Age", 24));
    }

    @Test
    void addAttribute_attributeNameOnly_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Birthday");
        assertNotNull(attributeList.findAttribute("Birthday"));
    }

    @Test
    void findAttribute_nonExistingAttribute_returnsNull() {
        AttributeList attributeList = new AttributeList();
        assertNull(attributeList.findAttribute("Specialisation"));
    }

    @Test
    void findAttribute_existingAttribute_returnsCorrectAttribute() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        assertEquals(attributeList.findAttribute("Position"), POSITION);
    }

    @Test
    void findAttribute_existingAttributeNonCaseSensitive_returnsCorrectAttribute() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        assertEquals(attributeList.findAttribute("poSItIOn"), POSITION);
    }

    @Test
    void editAttribute_validInputs_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Position", "Marketing");
        attributeList.editAttribute("Position", "CEO");
        assertEquals(attributeList.findAttribute("Position"), POSITION);
    }

    @Test
    void removeAttribute_existingAttribute_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("Age", 45);
        attributeList.removeAttribute("Age");
        assertNull(attributeList.findAttribute("Age"));
    }

    @Test
    void removeAttribute_nonExistingAttribute_throwsAttributeException() {
        AttributeList attributeList = new AttributeList();
        assertThrows(AttributeException.class, () -> attributeList.removeAttribute("anything"));
    }

    @Test
    void removeField_existingAttribute_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("test", "string");
        attributeList.removeField("test");
        assertNull(attributeList.findAttribute("test"));
    }

    @Test
    void removeField_nonExistingAttribute_doesNothing() {
        AttributeList attributeList = new AttributeList();
        attributeList.removeField("anything");
        assertEquals(attributeList, new AttributeList());
    }

    @Test
    void updateAttribute_existingAttribute_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        Attribute<String> updatedAttribute = new AbstractAttribute<>("newAttribute", "newField") { };
        attributeList.updateAttribute(POSITION, updatedAttribute);
        assertEquals(attributeList.findAttribute("newAttribute"), updatedAttribute);
    }

    @Test
    void updateAttribute_nonExistingAttribute_throwsAttributeException() {
        AttributeList attributeList = new AttributeList();
        assertThrows(AttributeException.class, () -> attributeList.updateAttribute(POSITION, AGE));
    }

    @Test
    void retrieveFieldValue_existingAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        assertEquals(attributeList.retrieveFieldValue("Position"), "CEO");
    }

    @Test
    void retrieveFieldValue_existingNonStringAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(objectAttribute);
        assertEquals(attributeList.retrieveFieldValue("Object"), new ArrayList<Object>());
    }

    @Test
    void retrieveFieldValue_nonExistingAttribute_returnsNull() {
        AttributeList attributelist = new AttributeList();
        assertNull(attributelist.retrieveFieldValue("AGE"));
    }

    @Test
    void addAll_validListOfAttributes_success() {
        AttributeList attributeList = new AttributeList();
        AttributeList toMatch = new AttributeList();
        List<Attribute<?>> attributes = new ArrayList<>();
        attributes.add(POSITION);
        attributes.add(AGE);
        attributes.add(objectAttribute);
        toMatch.addAttribute(POSITION);
        toMatch.addAttribute(AGE);
        toMatch.addAttribute(objectAttribute);
        attributeList.addAll(attributes);
        assertEquals(attributeList, toMatch);
    }

    @Test
    void addAll_validAttributeList_success() {
        AttributeList attributeList = new AttributeList();
        AttributeList toMatch = new AttributeList();
        toMatch.addAttribute(POSITION);
        toMatch.addAttribute(AGE);
        toMatch.addAttribute(objectAttribute);
        attributeList.addAll(toMatch);
        assertEquals(attributeList, toMatch);
    }

    @Test
    void addAll_emptyListOfAttributes_success() {
        AttributeList attributeList = new AttributeList();
        List<Attribute<?>> attributes = new ArrayList<>();
        attributeList.addAll(attributes);
        assertEquals(attributeList, new AttributeList());
    }

    @Test
    void addAll_emptyAttributeList_success() {
        AttributeList attributeList = new AttributeList();
        AttributeList toMatch = new AttributeList();
        attributeList.addAll(toMatch);
        assertEquals(attributeList, toMatch);
    }

    @Test
    void toList() {
        AttributeList attributeList = new AttributeList();
        List<Attribute<?>> attributes = new ArrayList<>();
        attributes.add(POSITION);
        attributes.add(AGE);
        attributes.add(objectAttribute);
        attributeList.addAttribute(POSITION);
        attributeList.addAttribute(AGE);
        attributeList.addAttribute(objectAttribute);
        assertEquals(attributeList.toList(), attributes);
    }

    @Test
    void isEmpty_emptyAttributeList_returnsTrue() {
        AttributeList attributeList = new AttributeList();
        assertTrue(attributeList.isEmpty());
    }

    @Test
    void isEmpty_nonEmptyAttributeList_returnsFalse() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(POSITION);
        assertFalse(attributeList.isEmpty());
    }
}
