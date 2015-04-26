package mailbox.messageQueue;

import akka.actor.ActorRef;
import akka.dispatch.Envelope;
import akka.dispatch.MessageQueue;
import mailbox.MyUnboundedMessageQueueSemantics;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GroupedMessageQueue implements MessageQueue,
        MyUnboundedMessageQueueSemantics {

    private String hello = "";
    private String world = "";

    private String I = "";
    private String am = "";
    private String groot = "";

    private final Queue<Envelope> queue =
            new ConcurrentLinkedQueue<Envelope>();

    public void enqueue(ActorRef receiver, Envelope handle) {

        if (handle.message() instanceof String) {
            String message = (String) handle.message();
            if (message.equals("I")) {
                I = message;
            } else if (message.equals("am")) {
                am = message;
            } else if (message.equals("groot")) {
                groot = message;
            } else if (message.equals("hello")) {
                hello = message;
            } else if (message.equals("world")) {
                world = message;
            } else {
                queue.offer(handle);
            }
        } else {
            queue.offer(handle);
        }
    }

    public Envelope dequeue() {

        if (!I.isEmpty() && !am.isEmpty() && !groot.isEmpty()) {
            I = am = groot = "";
            return new Envelope("You are groot", null);
        } else if (!hello.isEmpty() && !world.isEmpty()){
            hello = world = "";
            return new Envelope("We have a mailbox different", null);
        }
        return queue.poll();
    }

    public int numberOfMessages() {
        return queue.size();
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }

    public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
        for (Envelope handle : queue) {
            deadLetters.enqueue(owner, handle);
        }
    }
}