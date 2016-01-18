package com.brg.controller;

public interface ControllerService {
    void setLoadingProgress(double percentage, String text);

    void startApplication(String[] args);
}
