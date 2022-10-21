package seedu.address.testutil;

import seedu.address.logic.commands.InsuranceCommand.EditInsuranceDescriptor;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.LifeInsurance;
import seedu.address.model.person.Person;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditInsuranceDescriptorBuilder {

    private EditInsuranceDescriptor descriptor;

    public EditInsuranceDescriptorBuilder() {
        descriptor = new EditInsuranceDescriptor();
    }

    public EditInsuranceDescriptorBuilder(EditInsuranceDescriptor descriptor) {
        this.descriptor = new EditInsuranceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInsuranceDescriptor} with fields containing {@code person}'s Insurance details
     */
    public EditInsuranceDescriptorBuilder(Person person) {
        descriptor = new EditInsuranceDescriptor();
        descriptor.setHealthInsurance(person.getHealthInsurance());
        descriptor.setDisabilityInsurance(person.getDisabilityInsurance());
        descriptor.setCriticalIllnessInsurance(person.getCriticalIllnessInsurance());
        descriptor.setLifeInsurance(person.getLifeInsurance());
    }

    /**
     * Sets the {@code HealthInsurance} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withHealthInsurance(boolean hasInsurance) {
        descriptor.setHealthInsurance(new HealthInsurance(hasInsurance));
        return this;
    }

    /**
     * Sets the {@code DisabilityInsurance} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withDisabilityInsurance(boolean hasInsurance) {
        descriptor.setDisabilityInsurance(new DisabilityInsurance(hasInsurance));
        return this;
    }

    /**
     * Sets the {@code CriticalIllnessInsurance} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withCriticalIllnessInsurance(boolean hasInsurance) {
        descriptor.setCriticalIllnessInsurance(new CriticalIllnessInsurance(hasInsurance));
        return this;
    }

    /**
     * Sets the {@code LifeInsurance} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withLifeInsurance(boolean hasInsurance) {
        descriptor.setLifeInsurance(new LifeInsurance(hasInsurance));
        return this;
    }

    public EditInsuranceDescriptor build() {
        return descriptor;
    }
}
