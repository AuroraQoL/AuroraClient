package me.aurora.client.utils;

public class Timer {
    public long lastMS = System.currentTimeMillis();

    public boolean timeBetween(long time, boolean reset) {
        if (System.currentTimeMillis() - this.lastMS > time) {
            if (reset) this.lastMS = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}