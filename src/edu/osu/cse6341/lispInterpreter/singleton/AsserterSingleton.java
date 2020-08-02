package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.asserter.CondFunctionParameterAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;
import lombok.Getter;

import java.util.Set;

@Getter
public enum AsserterSingleton {
    INSTANCE;

    private final FunctionLengthAsserter functionLengthAsserter;
    private final TokenKindAsserter tokenKindAsserter;
    private final CondFunctionParameterAsserter condFunctionParameterAsserter;

    AsserterSingleton() {
        functionLengthAsserter = FunctionLengthAsserter.newInstance(
            DeterminerSingleton.INSTANCE.getFunctionLengthDeterminer()
        );

        final Set<TokenKind> startingTokenKindSet = Set.of(
            TokenKind.OPEN_TOKEN,
            TokenKind.NUMERIC_TOKEN,
            TokenKind.LITERAL_TOKEN
        );
        tokenKindAsserter = TokenKindAsserter.newInstance(
            startingTokenKindSet
        );
        condFunctionParameterAsserter = CondFunctionParameterAsserter.newInstance(
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            functionLengthAsserter
        );
    }
}
