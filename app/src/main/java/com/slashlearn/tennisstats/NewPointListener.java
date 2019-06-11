package com.slashlearn.tennisstats;

public interface NewPointListener {
    void newPointFromFragment();
    void errorFromFragment(int errorKey, int serveHit);
    void winnerFromFragment(int winnerKey, int serveHit);
    void rallyFromFragment(int serveHit);
}
