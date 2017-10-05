import java.time.Duration;

public class Pi {

    static class Calculate{

    }

    static class Work{
        private final int start;
        private final int neOfElements;

        Work(int start, int neOfElements) {
            this.start = start;
            this.neOfElements = neOfElements;
        }

        public int getStart() {
            return start;
        }

        public int getNeOfElements() {
            return neOfElements;
        }
    }

    static class Result{
        private final double value;

        Result(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

    static class PiApproximation{
        private final double pi;
        private final Duration duration;

        PiApproximation(double pi, Duration duration) {
            this.pi = pi;
            this.duration = duration;
        }

        public double getPi() {
            return pi;
        }

        public Duration getDuration() {
            return duration;
        }
    }

}
