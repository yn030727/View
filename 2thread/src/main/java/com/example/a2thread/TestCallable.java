package com.example.a2thread;

import java.util.concurrent.Callable;

public class TestCallable implements Callable {
    @Override
    public String call() throws Exception {
        return "Hello world fucking !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }
}
