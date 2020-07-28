package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class LessFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

    @Override
    public Node evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.LESS,
            FunctionLengthConstants.THREE,
            params.parameterLength()
        );
        Node evaluatedAddress = params.evaluateLispNode(true);
        int leftValue = numericValueRetriever.retrieveNumericValue(
            (LispNode)evaluatedAddress,
            1,
            FunctionNameConstants.LESS
        );
        Node right = ((ExpressionNode) params).getData();
        Node evaluatedData = right.evaluate(true);
        int rightValue = numericValueRetriever.retrieveNumericValue(
            (LispNode)evaluatedData,
            2,
            FunctionNameConstants.LESS
        );
        boolean result = leftValue < rightValue;
        return new AtomNode(result);
    }
}