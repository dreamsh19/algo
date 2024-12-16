import java.util.function.Function;

public abstract class AbstractUniSolutionTest<A1, OUT> extends AbstractSolutionTest<A1, OUT> {

    abstract Function<A1, OUT> solution();

    @Override
    OUT solution(A1 a1) {
        return solution().apply(a1);
    }
}