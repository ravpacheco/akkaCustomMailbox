import actor.SpecialActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomMailboxTest {

    public static void main(String[] args) {

        String message = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ActorSystem system = ActorSystem.create("rootSystem");

        ActorRef specialActor = system.actorOf(
                Props.create(SpecialActor.class)
                        .withMailbox("my-mailbox"));


        while (!message.equals("sair")){

            try {
                message = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            specialActor.tell(message, specialActor);
        }

        system.shutdown();
    }

}
