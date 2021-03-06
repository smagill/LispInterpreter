package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class TokenGenerator {

    public Token generateCloseToken() {
        return Token.newInstance(
            TokenKind.CLOSE_TOKEN,
            String.valueOf(TokenValueConstants.CLOSE_PARENTHESES)
        );
    }

    public Token generateOpenToken() {
        return Token.newInstance(
            TokenKind.OPEN_TOKEN,
            String.valueOf(TokenValueConstants.OPEN_PARENTHESES)
        );
    }

    public Token generateNumericToken(
        String value
    ) {
        return Token.newInstance(
            TokenKind.NUMERIC_TOKEN,
            value
        );
    }

    public Token generateLiteralToken(
        String value
    ) {
        return Token.newInstance(
            TokenKind.LITERAL_TOKEN,
            value
        );
    }
}
