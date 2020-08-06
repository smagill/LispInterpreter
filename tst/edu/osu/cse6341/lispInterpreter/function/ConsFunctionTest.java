package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.ConsFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ConsFunctionTest {

    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;
    private NodeGenerator nodeGenerator;

    private ConsFunction consFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        consFunction = ConsFunction.newInstance(
            functionLengthAsserter,
            listValueRetriever,
            nodeEvaluator,
            nodeGenerator
        );
    }

    @Test
    void consFunctionTest() throws Exception {
        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CONS
            )
        ).thenReturn(expressionNodeParams);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getAddress()).thenReturn(address);

        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                address,
                true
            )
        ).thenReturn(evaluatedAddress);

        LispNode data = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getData()).thenReturn(data);

        LispNode evaluatedData = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                data,
                true
            )
        ).thenReturn(evaluatedData);

        ExpressionNode expected = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeGenerator.generateExpressionNode(
                evaluatedAddress,
                evaluatedData
            )
        ).thenReturn(expected);

        LispNode actual = consFunction.evaluateLispFunction(
            params
        );

        Assertions.assertEquals(expected, actual);

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        );

    }
}