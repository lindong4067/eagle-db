package com.lindong.eagledb.client;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.lindong.eagledb.message.GetRequest;
import com.lindong.eagledb.message.SetRequest;

import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

public class EagleDBClient {

    private final ActorSystem system = ActorSystem.create("LocalSystem");
    private final ActorSelection remoteDb;

    public EagleDBClient(String remoteAddress) {
        //akka.tcp://eagle@127.0.0.1:2552
        remoteDb = system.actorSelection("akka.tcp://eagle@" +  remoteAddress + "/user/eagle-server");
    }

    public CompletionStage set(String key, Object value) {
        return toJava(ask(remoteDb, new SetRequest(key, value),  2000));
    }

    public CompletionStage<Object> get(String key){
        return toJava(ask(remoteDb, new GetRequest(key), 2000));
    }
}
