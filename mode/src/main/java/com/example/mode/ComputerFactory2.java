package com.example.mode;

public abstract class ComputerFactory2 {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
