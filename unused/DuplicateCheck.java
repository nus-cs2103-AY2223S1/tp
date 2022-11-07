/**
 * Duplicate checking algorithm for v1.4. Removed due to feature freeze: cannot fix feature flaw.
 */
public class DuplicateCheck {

    /**
     * Checks if an AbstractDisplayItem has the same email attribute
     * as the current AbstractDisplayItem instance.
     *
     * @param displayItem An other AbstractDisplayItem instance.
     * @return true if both DisplayItem instances have the same Email instance, false otherwise.
     */
    public boolean isSameEmail(AbstractDisplayItem displayItem) {
        Attribute<?> currentEmail = attributes.findAttribute("Email");
        Attribute<?> otherEmail = displayItem.attributes.findAttribute("Email");
        if (currentEmail == otherEmail) {
            return true;
        } else if (currentEmail == null || otherEmail == null) {
            return false;
        }
        return currentEmail.equals(otherEmail);
    }

    /**
     * Checks if this AbstractDisplayItem instance and another AbstractDisplayItem instance
     * do not have an Email attribute.
     *
     * @param displayItem Another AbstractDisplayItem instance.
     * @return true if both AbstractDisplayItem instances have no Email attribute, false otherwise.
     */
    public boolean isBothEmailNull(AbstractDisplayItem displayItem) {
        Attribute<?> currentEmail = attributes.findAttribute("Email");
        Attribute<?> otherEmail = displayItem.attributes.findAttribute("Email");
        return currentEmail == null && otherEmail == null;
    }

    /**
     * Checks if an AbstractDisplayItem has the same phone attribute
     * as the current AbstractDisplayItem instance.
     *
     * @param displayItem An other AbstractDisplayItem instance.
     * @return true if both DisplayItem instances have the same Phone instance, false otherwise.
     */
    public boolean isSamePhone(AbstractDisplayItem displayItem) {
        Attribute<?> currentPhone = attributes.findAttribute("Phone");
        Attribute<?> otherPhone = displayItem.attributes.findAttribute("Phone");
        if (currentPhone == otherPhone) {
            return true;
        } else if (currentPhone == null || otherPhone == null) {
            return false;
        }
        return currentPhone.equals(otherPhone);
    }

    /**
     * Checks if this AbstractDisplayItem instance and another AbstractDisplayItem instance
     * do not have a Phone attribute.
     *
     * @param displayItem Another AbstractDisplayItem instance.
     * @return true if both AbstractDisplayItem instances have no Phone attribute, false otherwise.
     */
    public boolean isBothPhoneNull(AbstractDisplayItem displayItem) {
        Attribute<?> currentPhone = attributes.findAttribute("Phone");
        Attribute<?> otherPhone = displayItem.attributes.findAttribute("Phone");
        return currentPhone == null && otherPhone == null;
    }

}
