package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.ValueRetrieverSingleton;

public class TimesFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

	public TimesFunction(){
        functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
        numericValueRetriever = ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.TIMES,
            FunctionLengthConstants.THREE,
            params.getLength()
        );
        Node evaluatedAddress = params.evaluate(true);
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.TIMES
        );
        Node right = ((ExpressionNode) params).getData();
        Node evaluatedData = right.evaluate(true);
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.TIMES
        );
        int result = leftValue * rightValue;
        return new AtomNode(result);
    }
}
