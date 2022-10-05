package seedu.address.model.pet;

public class VaccinationStatus {

    private final boolean isVaccinated;

    public VaccinationStatus(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    @Override
    public String toString() {
        return ;
    }

    @Override
    public int hashCode(){
        Boolean.hashCode(isVaccinated)
    }
}
