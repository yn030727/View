package com.example.mode2;

public abstract class Builder {
    public abstract void buildCPU(String cpu);
    public abstract void buildMainBoard(String mainboard);
    public abstract void buildRAM(String ram);
    public abstract Computer create();
}
