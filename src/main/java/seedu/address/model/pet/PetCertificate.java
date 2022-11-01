package seedu.address.model.pet;

/**
 * Represents the certificates that a pet has. For example, noble blood certificate.
 */
public class PetCertificate {
    public static final String MESSAGE_CONSTRAINTS =
            "Pet certificates should only contain alphanumeric characters and spaces, and it should not be blank";
    private final String certificate;

    /**
     * Constructs a PetCertificate object.
     *
     * @param certificate The string representation of the certificate.
     */
    public PetCertificate(String certificate) {
        if (certificate == null) {
            this.certificate = "";
        } else {
            this.certificate = certificate;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PetCertificate // instanceof handles nulls
                && certificate.equals(((PetCertificate) other).certificate)); // state check
    }

    @Override
    public int hashCode() {
        return certificate.hashCode();
    }

    @Override
    public String toString() {
        return " [ " + certificate + " ] ";
    }

    public String getCertificate() {
        return certificate;
    }
}
