import org.javatuples.Pair;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractBiSolutionTest<A1, A2, OUT> extends AbstractSolutionTest<Pair<A1, A2>, OUT> {

    abstract BiFunction<A1, A2, OUT> sol2();

    @Override
    Function<Pair<A1, A2>, OUT> solution() {
        return input -> sol2().apply(input.getValue0(), input.getValue1());
    }
}
