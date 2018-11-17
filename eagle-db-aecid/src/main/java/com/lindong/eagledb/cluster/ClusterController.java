package com.lindong.eagledb.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;

import static akka.cluster.ClusterEvent.initialStateAsEvents;

public class ClusterController extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    Cluster cluster;//TODO

    List<ActorRef> workers = new ArrayList<>();

    @Override
    public void preStart() {
        cluster.subscribe(self(), initialStateAsEvents(),
                ClusterEvent.MemberEvent.class, ClusterEvent.UnreachableMember.class);
    }

    @Override
    public void postStop() {
        cluster.unsubscribe(self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClusterEvent.MemberEvent.class, message -> {
                    if(message.getClass() == ClusterEvent.MemberUp.class){
                        System.out.println("member up: " + message.member().address());
                    }
                    log.info("MemberEvent: {}", message);
                }).
                match(ClusterEvent.UnreachableMember.class, message -> {
                    log.info("UnreachableMember: {}", message);
                }).build();
    }
}
