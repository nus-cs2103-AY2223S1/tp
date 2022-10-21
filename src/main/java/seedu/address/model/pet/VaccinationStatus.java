package seedu.address.model.pet;

/**
 * A class that represents the vaccination status of a pet. Could be either true of false.
 */
public class VaccinationStatus implements Comparable<VaccinationStatus> {

    public static final String MESSAGE_USAGE = "The vaccination status should be either 'true' or 'false'";
    private final boolean isVaccinated;

    /**
     * Constructs the VaccinationStatus object.
     * @param isVaccinated A boolean value that indicates whether the pet is vaccinated.
     */
    public VaccinationStatus(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public static VaccinationStatus defaultStatus() {
        return new VaccinationStatus(false);
    }

    public boolean getVaccinationStatus() {
        return isVaccinated;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof VaccinationStatus) {
            VaccinationStatus otherStatus = (VaccinationStatus) other;
            return isVaccinated == otherStatus.getVaccinationStatus();
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "Vaccination status: " + (isVaccinated ? "Vaccinated" : "Not Vaccinated");
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isVaccinated);
    }

    @Override
    public int compareTo(VaccinationStatus o) {
        if (this.isVaccinated && o.isVaccinated || (!this.isVaccinated && !o.isVaccinated)) {
            return 0;
        } else if (this.isVaccinated) {
            return 1;
        } else {
            return -1;
        }
    }
}
