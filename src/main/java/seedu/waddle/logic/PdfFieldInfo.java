package seedu.waddle.logic;

/**
 * A Class to store information about a Pdf field.
 */
public class PdfFieldInfo {
    private String name;
    private String value;

    /**
     * Constructor for a PdfFieldInfo.
     * @param name Name of the field.
     * @param value Value to insert into the field.
     */
    public PdfFieldInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Field name: " + this.name + " Value: " + this.value;
    }
}
