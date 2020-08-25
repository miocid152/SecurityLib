package com.er.carrascome.secutiry;

public enum BundleArguments {
    USUARIO("USUARIO"),
    DATO("DATO")
    ;
    private String value;
    private BundleArguments(String brand) {
        this.value = brand;
    }

    @Override
    public String toString(){
        return value;
    }

}



