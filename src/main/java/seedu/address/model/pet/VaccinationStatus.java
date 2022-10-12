package seedu.address.model.pet;

public class VaccinationStatus {

    public static final String MESSAGE_USAGE = "The vaccination status should be either 'true' or 'false'";
    private final boolean isVaccinated;

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
        return isVaccinated ? "Vaccinated" : "Not Vaccinated";
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isVaccinated);
    }
}
