package com.lindong.eagledb;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.lindong.eagledb.actor.EagleDBActor;

public class EagleDBActorMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("eagle");
        system.actorOf(Props.create(EagleDBActor.class), "eagle-server");
    }
}
