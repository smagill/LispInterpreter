package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListValueRetrieverTest {

    private LispNode node;
    private String functionName;

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private Environment environment;
    private DotNotationPrinter dotNotationPrinter;

    private ListValueRetriever listValueRetriever;

    @BeforeEach
    void setup() {
        functionName = "functionName";

        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        environment = Mockito.mock(Environment.class);
        dotNotationPrinter = Mockito.mock(DotNotationPrinter.class);

        listValueRetriever = ListValueRetriever.newInstance(
            expressionNodeDeterminer,
            environment,
            dotNotationPrinter
        );
    }

    @Test
    void inputIsAListTest() throws Exception {
        node = Mockito.mock(ExpressionNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(true);

        ExpressionNode actual = listValueRetriever.retrieveListValue(
            node,
            functionName
        );

        Assertions.assertEquals(node, actual);

        Mockito.verifyZeroInteractions(environment);
        Mockito.verifyZeroInteractions(dotNotationPrinter);
    }

    @Test
    void inputIsAVariableListTest() throws Exception {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Mockito.when(environment.isVariableName(nodeValue)).thenReturn(true);

        LispNode result = Mockito.mock(ExpressionNode.class);
        Mockito.when(environment.getVariableValue(nodeValue)).thenReturn(result);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(true);

        ExpressionNode actual = listValueRetriever.retrieveListValue(
            node,
            functionName
        );

        Assertions.assertEquals(result, actual);
        Mockito.verifyZeroInteractions(dotNotationPrinter);
    }

    @Test
    void inputIsVariableButNotListTest() {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Mockito.when(environment.isVariableName(nodeValue)).thenReturn(true);

        LispNode result = Mockito.mock(ExpressionNode.class);
        Mockito.when(environment.getVariableValue(nodeValue)).thenReturn(result);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(false);

        Assertions.assertThrows(
            NotAListException.class,
            () -> listValueRetriever.retrieveListValue(
                node,
                functionName
            )
        );
    }

    @Test
    void inputIsNotAVariableTest() {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Mockito.when(environment.isVariableName(nodeValue)).thenReturn(false);

        Assertions.assertThrows(
            NotAListException.class,
            () -> listValueRetriever.retrieveListValue(
                node,
                functionName
            )
        );
    }
}
