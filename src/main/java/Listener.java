import akka.actor.UntypedActor;

public class Listener extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Pi.PiApproximation) {
            Pi.PiApproximation approximation = (Pi.PiApproximation) message;
            System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                    approximation.getPi(), approximation.getDuration().toMillis()));
            getContext().system().terminate();
        } else {
            unhandled(message);
        }
    }
}
