package com.example.rxjava;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ObSwordsman extends BaseObservable {
    private String name;
    private String level;
    public ObSwordsman(String name , String level){
        this.name = name;
        this.level = level;
    }

    @Bindable
    public String getLevel() {
        return level;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setLevel(String level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
