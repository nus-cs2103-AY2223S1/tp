package seedu.address.model.pet;

public class Species {
    private final String species;

    public Species(String species) {
        if (species == null) {
            this.species = "";
        } else {
            this.species = species;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Species // instanceof handles nulls
                && species.equals(((Species) other).species)); // state check
    }

    @Override
    public int hashCode() {
        return species.hashCode();
    }

    @Override
    public String toString() {
        return species;
    }

    public String getSpecies() {
        return species;
    }
}
