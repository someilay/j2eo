package tree.Expression.Primary;

import tree.*;
import tree.Type.Type;

// Primary
//    : ...
//    | Type DimsOpt DOT CLASS // ClassLiteral
//    | VOID DimsOpt DOT CLASS // ClassLiteral
//    | ...
public class ClassLiteral extends Literal
{
    // Structure
    public Type type;  // VOID, is type==null
    public Dims dims;

    // Creation
    public ClassLiteral(Type t, Dims d)
    {
        super(null);
        this.type = t;
        this.dims = d;
    }

    // Reporting
    public void report(int sh)
    {
        // Stub
        Entity.doShift(sh);
        System.out.println("CLASS LITERAL");
    }

    // Generation
    public void generateEO()
    {

    }
}