package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunctionEvaluator {

    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;
    private final NodeValueComparator nodeValueComparator;

    public LispNode evaluateCondFunction(
        LispNode params
    ) throws Exception {
        if (params instanceof AtomNode) {
            throw new NotAListException("Error! None of the conditions in the COND function evaluated to true.\n");
        }
        ExpressionNode expressionNodeParams = (ExpressionNode)params;
        LispNode address = expressionNodeParams.getAddress();
        ExpressionNode expressionNodeAddress = listValueRetriever.retrieveListValue(
            address,
            FunctionNameConstants.COND
        );
        LispNode booleanResult = nodeEvaluator.evaluate(
            expressionNodeAddress.getAddress(),
            true
        );
        if((booleanResult instanceof AtomNode) && !nodeValueComparator.equalsNil(((AtomNode)booleanResult).getValue()))
            return nodeEvaluator.evaluate(expressionNodeAddress.getData(), true);
        return evaluateCondFunction(
            expressionNodeParams.getData()
        );
    }
}
