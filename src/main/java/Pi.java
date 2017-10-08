import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.time.Duration;

public class Pi {

    public static void main(String[] args) {
        Pi pi = new Pi();
        pi.calculate(4, 10000, 10000);
    }

    private void calculate(final int nrofWorkers,final int nroElements,final int nrofMessages) {
        ActorSystem system = ActorSystem.create("PiSystem");

        final ActorRef listener = system.actorOf( Props.create(Listener.class), "listener");

        ActorRef master = system.actorOf(Props.create(Master.class, nrofWorkers, nrofMessages, nroElements, listener), "master");

        master.tell(new Calculate(),null);

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

    static class Calculate {
    }
}
