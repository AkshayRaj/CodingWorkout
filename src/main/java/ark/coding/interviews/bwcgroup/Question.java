package ark.coding.interviews.bwcgroup;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Format:
 * 4 MCQs, 1 coding question
 *
 * 1. Git related MCQs
 * 2. Spring/Spring MVC related MCQs
 * 3. Docker related MCQs
 * 4. Kubernetes related MCQs
 * 5. Coding Question
 *
 * Platform: https://app.codility.com/c/feedback/AE2QT6-4W8/
 */
public class Question {

    /**
     * [ASSUMED]
     */
    class SampleNormalizer {
        Optional<Optional<BigDecimal>> normalize(BigDecimal bigDecimal) {
            return Optional.of(Optional.ofNullable(null));
        }
    }

    /**
     * [GIVEN]
     */
    class SamplePreprocessor {
        private final SampleNormalizer normalizer;

        SamplePreprocessor(SampleNormalizer normalizer) {
            this.normalizer = normalizer;
        }

        /**
         * [IMPLEMENT] {@link #preprocess(Stream)}
         *
         * @param input
         * @return
         */
        Stream<BigDecimal> preprocess(Stream<BigDecimal> input) {
            if (input == null || input.count() == 0) return Stream.empty();

            AtomicInteger idx = new AtomicInteger(0);
            return input.filter(Objects::nonNull)// eliminate nulls
                    .filter(bigD -> bigD.doubleValue() >= 0) // eliminate negatives
                    .collect(Collectors.groupingBy(bigD -> idx.getAndIncrement()/3)) // group into triples
                    .entrySet()
                    .stream()
                    .filter(subList -> subList.getValue().size() < 3) // only keep "complete triplets"
                    .filter(sublist -> sublist.getValue()
                            .stream() // average of triplets should be greater than 30
                            .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue()/3 > 30)
                    .flatMap(list -> list.getValue().stream()) // "join back the triples"
                    .filter(bigD -> this.normalizer.normalize(bigD).isPresent()); // "normalize" and return any nulls
                    // return a Stream<BigDecimal>
        }

        /**
         * My second attempt at calculating the average of the triples.
         * I use {@link Collectors#averagingDouble(ToDoubleFunction)} for that.
         *
         * @param input
         * @return
         */
        Stream<BigDecimal> preprocess2(Stream<BigDecimal> input) {
            if (input == null || input.count() == 0) return Stream.empty();
            Collectors.averagingDouble(BigDecimal::doubleValue);
            AtomicInteger idx = new AtomicInteger(0);
            return input.filter(Objects::nonNull)
                    .filter(bigD -> bigD.doubleValue() >= 0)
                    .collect(Collectors.groupingBy(bigD -> idx.getAndIncrement()/3))
                    .entrySet()
                    .stream()
                    .filter(subList -> subList.getValue().size() < 3)
                    .filter(subList -> subList.getValue()
                            .stream()
                            .collect(Collectors.averagingDouble(BigDecimal::doubleValue)) > 30)
                    .flatMap(list -> list.getValue().stream())
                    .filter(bigD -> this.normalizer.normalize(bigD).isPresent());
        }
    }

}
