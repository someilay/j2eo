package eotree;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EOObject extends EOExpr {
    List<EOBndName>     freeAttrs;
    Optional<EOBndName> varargAttr;
    List<EOBndExpr>     bndAttrs;
    Optional<String>    name;

    public EOObject(List<EOBndName> freeAttrs,
                    Optional<EOBndName> varargAttr,
                    List<EOBndExpr> bndAttrs,
                    Optional<String> name) {
        this.freeAttrs = freeAttrs;
        this.varargAttr = varargAttr;
        this.bndAttrs = bndAttrs;
        this.name = name;
    }

    @Override
    public String generateEO(int indent) {
        return "[" +
               freeAttrs.stream()
                       .map(attr -> attr.generateEO(indent))
                       .collect(Collectors.joining(" ")) +
               (varargAttr
                       .map(attr -> " " + attr.generateEO(indent) + "...")
                       .orElse("")) +
               "]" +
               (name
                       .map(n -> " > " + name)
                       .orElse("")
               ) + "\n" +
               bndAttrs.stream()
                       .map(attr -> attr.generateEO(indent + 1))
                       .collect(Collectors.joining("\n"));
    }
}
