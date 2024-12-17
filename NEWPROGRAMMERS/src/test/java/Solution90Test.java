import org.javatuples.Pair;

import java.util.function.BiFunction;
import java.util.stream.Stream;

class Solution90Test extends AbstractBiSolutionTest<Integer, Integer, Long> {

    @Override
    BiFunction<Integer, Integer, Long> solution() {
        return new Solution90()::solution;
    }

    @Override
    Stream<TestCase<Pair<Integer, Integer>, Long>> testCases() {
        return Stream.of(
                TestCase.of(2, 3, 20L)
        );
    }
}