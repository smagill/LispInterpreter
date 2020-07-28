package edu.osu.cse6341.lispInterpreter.program.parser;

import edu.osu.cse6341.lispInterpreter.program.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final Map<TokenKind, Node> tokenToNodeMap;

    static{
        tokenToNodeMap = new HashMap<>();
        tokenToNodeMap.put(TokenKind.OPEN_TOKEN, new ExpressionNode());
        tokenToNodeMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenToNodeMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
    }

    private final TokenKindAsserter tokenKindAsserter;

    public Parser() {
        tokenKindAsserter = AsserterSingleton.INSTANCE.getTokenKindAsserter();
    }

    public Node parseIntoNode(Tokenizer tokenizer) throws Exception{
        IToken token = tokenizer.getCurrent();
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        Node expressionChild = tokenToNodeMap.get(token.getTokenKind());
        expressionChild = expressionChild.newInstance();
        boolean isList = token.getTokenKind() == TokenKind.OPEN_TOKEN;
        if(isList) tokenizer.getNextToken();
        expressionChild.parse(tokenizer);
        if(isList) tokenizer.getNextToken();
        return expressionChild;
    }

}