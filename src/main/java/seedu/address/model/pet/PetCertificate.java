package seedu.address.model.pet;

public class PetCertificate {
    private final String certificate;

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
