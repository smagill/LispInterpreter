package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class GreaterFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public GreaterFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    String getFunctionName() {
        return "GREATER";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode) params).getData();
        int leftValue = getNumericValue(params.evaluate(true), 1);
        int rightValue = getNumericValue(right.evaluate(true), 2);
        boolean result = leftValue > rightValue;
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new GreaterFunction();
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 3;
    }
}
