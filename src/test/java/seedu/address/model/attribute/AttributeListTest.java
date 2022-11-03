package seedu.address.model.attribute;

import org.junit.jupiter.api.Test;
import seedu.address.model.attribute.exceptions.AttributeException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttributeListTest {

    private Attribute<String> stringAttribute = new AbstractAttribute<>("String", "test") { };
    private Attribute<Integer> integerAttribute = new AbstractAttribute<>("Integer", 12345) { };
    private Attribute<ArrayList<?>> objectAttribute =
            new AbstractAttribute<>("Object", new ArrayList<Object>()) { };


    @Test
    void createAttributeInstance() {
        AttributeList attributeList = new AttributeList();

        // Valid arguments
        assertEquals(attributeList.createAttributeInstance("stringTest", "test"),
                new AbstractAttribute<>("Stringtest", "test") { });
        assertEquals(attributeList.createAttributeInstance("integerTest", 1),
                new AbstractAttribute<>("Integertest", 1) { });

        List<Object> sampleList = new ArrayList<>();
        assertEquals(attributeList.createAttributeInstance("objectTest", sampleList),
                new AbstractAttribute<>("Objecttest", sampleList) { });
    }

    @Test
    void addAttribute_StringAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(stringAttribute);
        assertNotNull(attributeList.findAttribute("String"));
    }

    @Test
    void addAttribute_IntegerAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(integerAttribute);
        assertNotNull(attributeList.findAttribute("Integer"));
    }

    @Test
    void addAttribute_ObjectAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(objectAttribute);
        assertNotNull(attributeList.findAttribute("Object"));
    }

    @Test
    void addAttribute_StringValue_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("stringTest", "hello");
        assertNotNull(attributeList.findAttribute("Stringtest"));
    }

    @Test
    void addAttribute_IntegerValue_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("integerTest", 12345);
        assertNotNull(attributeList.findAttribute("Integertest"));
    }

    @Test
    void addAttribute_existingAttribute_throwsAttributeException() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("integerTest", 12345);
        assertThrows(AttributeException.class, () -> attributeList.addAttribute("integertest", 54321));
    }

    @Test
    void addAttribute_attributeNameOnly_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("test");
        assertNotNull(attributeList.findAttribute("test"));
    }

    @Test
    void findAttribute_nonExistingAttribute_returnsNull() {
        AttributeList attributeList = new AttributeList();
        assertNull(attributeList.findAttribute("hello"));
    }

    @Test
    void findAttribute_existingAttribute_returnsCorrectAttribute() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(stringAttribute);
        assertEquals(attributeList.findAttribute("String"), stringAttribute);
    }

    @Test
    void findAttribute_existingAttributeNonCaseSensitive_returnsCorrectAttribute() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(stringAttribute);
        assertEquals(attributeList.findAttribute("sTRinG"), stringAttribute);
    }

    @Test
    void editAttribute_validInputs_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("stringTest", "string");
        attributeList.editAttribute("stringTest", "test");
        assertEquals(attributeList.findAttribute("Stringtest"),
                new AbstractAttribute<>("Stringtest", "test") { });
    }

    @Test
    void removeAttribute_existingAttribute_success() throws AttributeException {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute("test", "string");
        attributeList.removeAttribute("test");
        assertNull(attributeList.findAttribute("test"));
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
        attributeList.addAttribute(stringAttribute);
        Attribute<String> updatedAttribute = new AbstractAttribute<>("newAttribute", "newField") { };
        attributeList.updateAttribute(stringAttribute, updatedAttribute);
        assertEquals(attributeList.findAttribute("newAttribute"), updatedAttribute);
    }

    @Test
    void updateAttribute_nonExistingAttribute_throwsAttributeException() {
        AttributeList attributeList = new AttributeList();
        assertThrows(AttributeException.class, () -> attributeList.updateAttribute(stringAttribute, integerAttribute));
    }

    @Test
    void retrieveFieldValue_existingAttribute_success() {
        AttributeList attributeList = new AttributeList();
        attributeList.addAttribute(stringAttribute);
        assertEquals(attributeList.retrieveFieldValue("String"), "test");
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
        assertNull(attributelist.retrieveFieldValue("anything"));
    }

    @Test
    void addAll_validListOfAttributes_success() {
        AttributeList attributeList = new AttributeList();
        AttributeList toMatch = new AttributeList();
        List<Attribute<?>> attributes = new ArrayList<>();
        attributes.add(stringAttribute);
        attributes.add(integerAttribute);
        attributes.add(objectAttribute);
        toMatch.addAttribute(stringAttribute);
        toMatch.addAttribute(integerAttribute);
        toMatch.addAttribute(objectAttribute);
        attributeList.addAll(attributes);
        assertEquals(attributeList, toMatch);
    }

    @Test
    void addAll_validAttributeList_success() {
        AttributeList attributeList = new AttributeList();
        AttributeList toMatch = new AttributeList();
        toMatch.addAttribute(stringAttribute);
        toMatch.addAttribute(integerAttribute);
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
        attributes.add(stringAttribute);
        attributes.add(integerAttribute);
        attributes.add(objectAttribute);
        attributeList.addAttribute(stringAttribute);
        attributeList.addAttribute(integerAttribute);
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
        attributeList.addAttribute(stringAttribute);
        assertFalse(attributeList.isEmpty());
    }
}