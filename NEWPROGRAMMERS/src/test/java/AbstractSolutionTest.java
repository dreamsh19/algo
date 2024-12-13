import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractSolutionTest<IN, OUT> {

    @Test
    void testSolution() {
        testCases().forEach(tc -> tc.runWith(solution()));
    }

    abstract Function<IN, OUT> solution();

    abstract Stream<TestCase<IN, OUT>> testCases();

}
