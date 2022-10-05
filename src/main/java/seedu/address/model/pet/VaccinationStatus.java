package seedu.address.model.pet;

public class VaccinationStatus {

    public static VaccinationStatus defaultStatus() {
        return new VaccinationStatus(false);
    }

    private final boolean isVaccinated;

    public VaccinationStatus(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public boolean isVaccinated() {
        return isVaccinated;
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
