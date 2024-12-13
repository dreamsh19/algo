import java.util.function.Function;

public abstract class AbstractTriSolutionTest<A1, A2, A3, OUT> extends AbstractSolutionTest<TriInput<A1, A2, A3>, OUT> {

    abstract TriFunction<A1, A2, A3, OUT> sol3();

    @Override
    Function<TriInput<A1, A2, A3>, OUT> solution() {
        return input -> sol3().apply(input.a1, input.a2, input.a3);
    }

}
