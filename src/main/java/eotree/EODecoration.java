package eotree;

/**
 * name > @
 */
public class EODecoration extends EONode {
    EOBndName name;

    public EODecoration(EOBndName name) {
        this.name = name;
    }

    @Override
    public String generateEO(int indent) {
        return indent(indent) + name + " > @";
    }
}
