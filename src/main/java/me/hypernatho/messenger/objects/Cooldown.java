package me.hypernatho.messenger.objects;

public class Cooldown {

    private long expire = 0;

    public void expireIn(Long time) {
        expire = System.currentTimeMillis() + time;
    }

    public Boolean hasExpired() {
        return System.currentTimeMillis() >= expire;
    }

    public void expire() {
        expire = 0;
    }

    public long getExpiryTime() {
        return expire;
    }


}