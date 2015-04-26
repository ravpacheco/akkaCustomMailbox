package mailbox;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.Envelope;
import akka.dispatch.MailboxType;
import akka.dispatch.MessageQueue;
import akka.dispatch.ProducesMessageQueue;
import com.typesafe.config.Config;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

import mailbox.messageQueue.GroupedMessageQueue;
import scala.Option;

public class MyUnboundedMailbox implements MailboxType,
        ProducesMessageQueue<GroupedMessageQueue> {

    // This constructor signature must exist, it will be called by Akka
    public MyUnboundedMailbox(ActorSystem.Settings settings, Config config) {
        // put your initialization code here
    }

    // The create method is called to create the MessageQueue
    public MessageQueue create(Option<ActorRef> owner, Option<ActorSystem> system) {
        return new GroupedMessageQueue();
    }
}