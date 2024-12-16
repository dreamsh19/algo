import org.javatuples.Pair;

import java.util.function.BiFunction;

public abstract class AbstractBiSolutionTest<A1, A2, OUT> extends AbstractSolutionTest<Pair<A1, A2>, OUT> {

    abstract BiFunction<A1, A2, OUT> solution();

    @Override
    OUT solution(Pair<A1, A2> pair) {
        return solution().apply(pair.getValue0(), pair.getValue1());
    }
}
