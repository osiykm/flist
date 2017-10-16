package com.osiykm.flist.services.programs;

/***
 * @author osiykm
 * created 29.09.2017 12:41
 */
public abstract class BaseProgram implements Runnable {
    private boolean alive = false;

    public void start() {
        alive = true;
        new Thread(this).start();
    }

    public void stop() {
        alive = false;
    }


    @SuppressWarnings("WeakerAccess")
    public boolean isAlive() {
        return alive;
    }
}
