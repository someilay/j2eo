package tree.Type;

import tree.Annotations;
import tree.Entity;

// The root class of the entire hierarchy for Java types.
//
// Type
//    :               UnannotatedType
//    | AnnotationSeq UnannotatedType
//    ;
public class Type extends Entity
{
    // Structure
    public Annotations annotations;

    // Creation
    public Type(Annotations annSeq)
    {
        this.annotations = annSeq;
    }
    public Type addAnnotations(Annotations anns)
    {
        this.annotations = anns;
        return this;
    }

    // Reporting
    public void report(int sh)
    {
        if ( annotations == null ) return;
        annotations.report(sh);
    }

}