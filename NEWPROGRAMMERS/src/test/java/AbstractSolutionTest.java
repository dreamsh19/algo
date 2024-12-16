import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractSolutionTest<IN, OUT> {

    @ParameterizedTest
    @MethodSource("testCases")
    void testSolution(TestCase<IN, OUT> testCase) {
        testCase.runWith(this::solution);
    }

    abstract OUT solution(IN in);

    abstract Stream<TestCase<IN, OUT>> testCases();

}
