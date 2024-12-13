import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractBiSolutionTest<A1, A2, OUT> extends AbstractSolutionTest<BiInput<A1, A2>, OUT> {

    abstract BiFunction<A1, A2, OUT> sol2();

    @Override
    Function<BiInput<A1, A2>, OUT> solution() {
        return input -> sol2().apply(input.a1, input.a2);
    }
}
