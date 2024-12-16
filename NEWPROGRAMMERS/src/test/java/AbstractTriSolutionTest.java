import org.javatuples.Triplet;

public abstract class AbstractTriSolutionTest<A1, A2, A3, OUT> extends AbstractSolutionTest<Triplet<A1, A2, A3>, OUT> {

    abstract TriFunction<A1, A2, A3, OUT> solution();

    @Override
    OUT solution(Triplet<A1, A2, A3> triplet) {
        return solution().apply(triplet.getValue0(), triplet.getValue1(), triplet.getValue2());
    }

}
