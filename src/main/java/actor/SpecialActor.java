package actor;

import akka.actor.UntypedActor;
import akka.dispatch.RequiresMessageQueue;
import mailbox.MyUnboundedMessageQueueSemantics;

public class SpecialActor extends UntypedActor
        //implements RequiresMessageQueue<MyUnboundedMessageQueueSemantics>
{

    @Override
    public void onReceive(Object o) throws Exception {

        if(o instanceof String){
            System.out.println(o);
        }else {
            System.err.println("Object type received is invalid.");
            unhandled(o);
        }
    }
}
