package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeGenerator {

    public AtomNode generateAtomNode(
        final boolean value
    ) {
        String t = value ? ReservedValuesConstants.T : ReservedValuesConstants.NIL;
        return AtomNode.newInstance(
            t
        );
    }

    public AtomNode generateAtomNode(
        final int value
    ) {
        String t = Integer.toString(value);
        return AtomNode.newInstance(
            t
        );
    }

    public AtomNode generateAtomNode(
        final String value
    ) {
        return AtomNode.newInstance(value);
    }

    public ExpressionNode generateExpressionNode(
        final Node address,
        final Node data
    ) {
        return ExpressionNode.newInstance(
            address,
            data
        );
    }
}
