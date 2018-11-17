package com.lindong.eagledb.greet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

public class AkkaQuickstart {
  public static void main(String[] args) {
    final ActorSystem system = ActorSystem.create("chat-room");
    try {
      //#create-actors
      final ActorRef displayActor =
        system.actorOf(Props.create(Printer.class, Printer::new), "display-window");
      final ActorRef wendyGreeter =
        system.actorOf(Props.create(Greeter.class, () -> new Greeter("Good morning!", displayActor)), "Wendy");
      final ActorRef nancyGreeter =
        system.actorOf(Props.create(Greeter.class, () -> new Greeter("Hello, everyone!", displayActor)), "Nancy");
      final ActorRef jimGreeter =
        system.actorOf(Props.create(Greeter.class, () -> new Greeter("It's a good day!", displayActor)), "Jim");
      //#create-actors

      //#main-send-messages
      wendyGreeter.tell(new WhoToGreet("Jim"), wendyGreeter);
      wendyGreeter.tell(new Greet(), wendyGreeter);

      wendyGreeter.tell(new WhoToGreet("Nancy"), wendyGreeter);
      wendyGreeter.tell(new Greet(), wendyGreeter);

      nancyGreeter.tell(new WhoToGreet("Wendy"), nancyGreeter);
      nancyGreeter.tell(new Greet(), nancyGreeter);

      jimGreeter.tell(new WhoToGreet("Wendy"), jimGreeter);
      jimGreeter.tell(new Greet(), jimGreeter);
      //#main-send-message

      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ioe) {
    } finally {
      system.terminate();
    }
  }
}
