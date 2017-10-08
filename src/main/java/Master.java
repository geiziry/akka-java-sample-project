import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinGroup;
import akka.routing.RoundRobinPool;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Router;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Master extends UntypedActor {

    private final int nrOfMessages;
    private final int nrOfElements;
    private final ActorRef workerRouter;

    private double pi;
    private int nrOfResults;
    private final Instant start = Instant.now();

    private final ActorRef listener;
//    private final ActorRef workerRouter;


    public Master(final int nrofWorkers,int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;
        this.workerRouter = this.getContext().actorOf(new RoundRobinPool(nrofWorkers).props(Props.create(Worker.class)), "workerRouter");



    }


    @Override
    public void onReceive(Object message){
        if (message instanceof Pi.Calculate) {
            for (int start=0;start<nrOfMessages;start++) {
                workerRouter.tell(new Pi.Work(start,nrOfElements),getSelf());
            }
        } else if (message instanceof Pi.Result) {
            Pi.Result result = (Pi.Result) message;
            pi += result.getValue();
            nrOfResults += 1;
            if (nrOfResults == nrOfMessages) {
                Duration duration = Duration.between(start,Instant.now());
                listener.tell(new Pi.PiApproximation(pi, duration), getSelf());
            }

        } else {
            unhandled(message);
        }



    }
}
