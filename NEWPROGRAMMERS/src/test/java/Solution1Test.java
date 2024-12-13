import java.util.function.Function;
import java.util.stream.Stream;

class Solution1Test extends AbstractSolutionTest<String[], String> {

    @Override
    Function<String[], String> solution() {
        return new Solution1()::solution;
    }

    @Override
    Stream<TestCase<String[], String>> testCases() {
        return Stream.of(
                TestCase.of(new String[]{"a", "b", "c"}, "abc"),
                TestCase.of(new String[]{"a", "b", "c", "d"}, "abcd")
        );

    }
}