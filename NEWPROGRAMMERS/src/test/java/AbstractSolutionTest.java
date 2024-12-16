import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public abstract class AbstractSolutionTest<IN, OUT> {
    @Test
    void testSolution() {
        testCases().forEach(tc -> tc.runWith(this::solution));
    }

    abstract OUT solution(IN in);

    abstract Stream<TestCase<IN, OUT>> testCases();

}
