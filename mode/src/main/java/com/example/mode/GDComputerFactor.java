package com.example.mode;

public class GDComputerFactor extends ComputerFactory2{

    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String className = clz.getName();
        try{
            //通过反射机制来生产不同厂家的计算机
            computer = (Computer) Class.forName(className).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (T) computer;
    }
}
