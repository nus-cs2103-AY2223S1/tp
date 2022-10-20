package gim.logic.generators;

import gim.model.exercise.Name;

/**
 * Generator for easy workout session.
 */
public class EasyGenerator implements Generator {
    private Name name;

    public EasyGenerator(Name name) {
        this.name = name;
    }

    @Override
    public String suggest() {
        return "easy workout for " + name;
    }
}
