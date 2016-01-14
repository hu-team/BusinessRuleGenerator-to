package com.brg.common;

import java.util.Observable;

abstract public class AbstractFacadeService extends Observable {
    public abstract void willExitApplication();
}
