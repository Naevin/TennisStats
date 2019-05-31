package com.slashlearn.tennisstats;

public interface NewPointListener {
    public void newPointFromFragment();
    public void errorFromFragment(int errorKey);
    public void winnerFromFragment(int winnerKey);
}
