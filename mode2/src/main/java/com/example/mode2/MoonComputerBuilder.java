package com.example.mode2;

public class MoonComputerBuilder extends Builder{
    private Computer mComputer = new Computer();
    @Override
    public void buildCPU(String cpu) {
        mComputer.setmCPU(cpu);
    }

    @Override
    public void buildMainBoard(String mainboard) {
        mComputer.setmMainBoard(mainboard);
    }

    @Override
    public void buildRAM(String ram) {
        mComputer.setmRAM(ram);
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
