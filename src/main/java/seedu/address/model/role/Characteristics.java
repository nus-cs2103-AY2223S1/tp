package seedu.address.model.role;

/**
 * Placeholder
 */
public class Characteristics {
    public static final String MESSAGE_CONSTRAINTS =
            "Placeholder";

    private String[] characteristicsArr;

    /**
     * Placeholder
     */
    public Characteristics(String[] characteristics) {
        this.characteristicsArr = characteristics;
    }

    public static boolean isValidCharacteristics(String test) {
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < characteristicsArr.length; i++) {
            result += characteristicsArr[i] + " ";
        }
        return result;
    }
}
