import entities.*;

public interface DataGenerator {

    // returns an entry of the given type.
    Entity getEntry(String type);

}
