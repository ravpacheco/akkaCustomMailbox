package mailbox.messageQueue;

import akka.actor.ActorRef;
import akka.dispatch.Envelope;
import akka.dispatch.MessageQueue;
import mailbox.MyUnboundedMessageQueueSemantics;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// This is the MessageQueue implementation
public class MyMessageQueue implements MessageQueue,
        MyUnboundedMessageQueueSemantics {
    private final Queue<Envelope> queue =
            new ConcurrentLinkedQueue<Envelope>();

    // these must be implemented; queue used as example
    public void enqueue(ActorRef receiver, Envelope handle) {
        queue.offer(handle);
    }
    public Envelope dequeue() { return queue.poll(); }
    public int numberOfMessages() { return queue.size(); }
    public boolean hasMessages() { return !queue.isEmpty(); }
    public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
        for (Envelope handle: queue) {
            deadLetters.enqueue(owner, handle);
        }
    }
}
