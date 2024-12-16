import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Assertions;

import java.util.function.Function;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCase<IN, OUT> {

    private final IN in;
    private final OUT out;

    public static <I1, O> TestCase<I1, O> of(I1 in, O out) {
        return new TestCase<>(in, out);
    }

    public static <I1, I2, O> TestCase<Pair<I1, I2>, O> of(I1 i1, I2 i2, O out) {
        return new TestCase<>(new Pair<>(i1, i2), out);
    }

    public static <I1, I2, I3, O> TestCase<Triplet<I1, I2, I3>, O> of(I1 i1, I2 i2, I3 i3, O out) {
        return new TestCase<>(new Triplet<>(i1, i2, i3), out);
    }


    public void runWith(Function<IN, OUT> solution) {
        OUT actual = solution.apply(this.in);
        Assertions.assertEquals(actual, this.out);
    }

}

