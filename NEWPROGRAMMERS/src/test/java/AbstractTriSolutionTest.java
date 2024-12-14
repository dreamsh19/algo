import org.javatuples.Triplet;

import java.util.function.Function;

public abstract class AbstractTriSolutionTest<A1, A2, A3, OUT> extends AbstractSolutionTest<Triplet<A1, A2, A3>, OUT> {

    abstract TriFunction<A1, A2, A3, OUT> sol3();

    @Override
    Function<Triplet<A1, A2, A3>, OUT> solution() {
        return input -> sol3().apply(input.getValue0(), input.getValue1(), input.getValue2());
    }

}
