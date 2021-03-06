package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.asserter.LineFormatAsserter;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.InvalidTokenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineFormatAsserterTest {

    private LineFormatAsserter lineFormatAsserter;

    @BeforeEach
    void setup() {
        lineFormatAsserter = LineFormatAsserter.newInstance(
            TokenValueConstants.ERROR_STATE_PATTERN
        );
    }

    @Test
    void lineAsserterDoesNotThrowTest(){

        Assertions.assertDoesNotThrow(
            () -> lineFormatAsserter.assertLineFormat("")
        );

        Assertions.assertDoesNotThrow(
            () -> lineFormatAsserter.assertLineFormat("2132132")
        );

        Assertions.assertDoesNotThrow(
            () -> lineFormatAsserter.assertLineFormat("\tASW 34324 AS() ")
        );
    }

    @Test
    void lineAsserterDoesThrowTest() {
        Assertions.assertThrows(
            InvalidTokenException.class,
            () -> lineFormatAsserter.assertLineFormat("23432DFD")
        );

        Assertions.assertThrows(
            InvalidTokenException.class,
            () -> lineFormatAsserter.assertLineFormat("2132132 343SDF")
        );

        Assertions.assertThrows(
            InvalidTokenException.class,
            () -> lineFormatAsserter.assertLineFormat("\tASW 34324P AS() ")
        );
    }
}
