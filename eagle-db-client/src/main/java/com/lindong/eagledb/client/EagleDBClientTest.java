package com.lindong.eagledb.client;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EagleDBClientTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EagleDBClient client = new EagleDBClient("127.0.0.1:2552");
        client.set("123", 123);
        Integer result = (Integer) ((CompletableFuture) client.get("123")).get();
        System.out.println("result :" + result);
    }
}