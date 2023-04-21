package com.example.mode2;

public class Client3 {
    public static void main(String[] args) {
        Context context;
        context = new Context(new WeakRivalStrategy());
        context.fighting();

        context = new Context(new CommonRivalStrategy());
        context.fighting();

        context = new Context(new StrongRivalStrategy());
        context.fighting();
    }
}
