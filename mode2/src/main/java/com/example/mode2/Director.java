package com.example.mode2;

public class Director {
    Builder mBuilder = null;
    public Director(Builder builder){
        this.mBuilder = builder;
    }

    public Computer createComputer(String cpu , String mainboard , String ram){
        //规范建造流程
        this.mBuilder.buildMainBoard(mainboard);
        this.mBuilder.buildCPU(cpu);
        this.mBuilder.buildRAM(ram);
        return mBuilder.create();
    }
}
