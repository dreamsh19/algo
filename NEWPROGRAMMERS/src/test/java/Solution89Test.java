import java.util.function.Function;
import java.util.stream.Stream;

class Solution89Test extends AbstractUniSolutionTest<int[][], Integer> {


    @Override
    Function<int[][], Integer> solution() {
        return new Solution89()::solution;
    }

    @Override
    Stream<TestCase<int[][], Integer>> testCases() {
        return Stream.of(
                TestCase.of(new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}}, 3)
        );
    }
}