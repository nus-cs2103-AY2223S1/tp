package gim.logic.generators;

import gim.model.exercise.Name;

/**
 * Generator for easy workout session.
 */
public class MediumGenerator implements Generator {
    private Name name;

    public MediumGenerator(Name name) {
        this.name = name;
    }

    @Override
    public String suggest() {
        return "medium workout for " + name;
    }
}
