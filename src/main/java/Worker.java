import akka.actor.UntypedActor;

public class Worker extends UntypedActor {

    @Override
    public void onReceive(Object message) {
        if (message instanceof Pi.Work) {
            Pi.Work work = (Pi.Work) message;
            double result = calculatePiFor(work.getStart(), work.getNeOfElements());
            getSender().tell(new Pi.Result(result), getSelf());
        } else {
            unhandled(message);
        }
    }

    private double calculatePiFor(int start, int neOfElements) {

        double acc=0.0;
        for (int i = start * neOfElements; i <= ((start + 1) * neOfElements - 1); i++) {
            acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
        }
        return acc;
    }
}
