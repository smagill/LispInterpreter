package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.GreaterFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GreaterFunctionTest {

    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private NumericValueRetriever numericValueRetriever;
    private ListValueRetriever listValueRetriever;
    private NodeGenerator nodeGenerator;

    private GreaterFunction greaterFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        numericValueRetriever = Mockito.mock(NumericValueRetriever.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        greaterFunction = GreaterFunction.newInstance(
            functionLengthAsserter,
            nodeEvaluator,
            numericValueRetriever,
            listValueRetriever,
            nodeGenerator
        );
    }

    @Test
    void greaterFunctionTest() throws Exception {
        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                true
            )
        ).thenReturn(evaluatedAddress);

        int leftValue = 3;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(leftValue);

        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(expressionNodeParams);

        LispNode data = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getData()).thenReturn(data);

        LispNode evaluatedData = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                data,
                true
            )
        ).thenReturn(evaluatedData);

        int rightValue = 5;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(rightValue);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeGenerator.generateAtomNode(
                leftValue > rightValue
            )
        ).thenReturn(expected);

        LispNode actual = greaterFunction.evaluateLispFunction(params);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.THREE,
            params
        );

    }

    @Test
    void greaterFunctionTrueTest() throws Exception {
        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                true
            )
        ).thenReturn(evaluatedAddress);

        int leftValue = 3;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(leftValue);

        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(expressionNodeParams);

        LispNode data = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getData()).thenReturn(data);

        LispNode evaluatedData = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                data,
                true
            )
        ).thenReturn(evaluatedData);

        int rightValue = 1;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(rightValue);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeGenerator.generateAtomNode(
                leftValue > rightValue
            )
        ).thenReturn(expected);

        LispNode actual = greaterFunction.evaluateLispFunction(params);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.THREE,
            params
        );

    }
}
