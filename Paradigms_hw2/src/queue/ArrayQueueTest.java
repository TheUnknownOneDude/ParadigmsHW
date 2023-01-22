package queue;

import base.ModelessSelector;
import base.Named;
import base.TestCounter;
import base.VariantTester;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ArrayQueueTest {
    public static final ModelessSelector<?> SELECTOR = VariantTester.<Named<Consumer<TestCounter>>>selector(ArrayQueueTest.class, (c, t) -> c.getValue().accept(t))
            .variant("Base", variant(Queues.QueueModel.class, d -> () -> d))
            ;

    private ArrayQueueTest() {
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }

    /* package-private */ static <M extends Queues.QueueModel, T extends Queues.QueueChecker<M>> Consumer<VariantTester<Named<Consumer<TestCounter>>>> variant(
            final Class<M> type,
            final T tester,
            final Queues.Splitter<M> splitter
    ) {
        return VariantTester.variant(type.getSimpleName(), new ArrayQueueTester<>(type, tester, splitter)::test);
    }

    /* package-private */ static <M extends Queues.QueueModel, T extends Queues.QueueChecker<M>> Consumer<VariantTester<Named<Consumer<TestCounter>>>> variant(
            final Class<M> type,
            final T tester
    ) {
        return variant(type, tester, (t, q, r) -> List.of());
    }
}
