package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.b.a;

class s implements Runnable {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public void run() {
        if (this.a.e || this.a.j.size() > 1) {
            a.a("SmaAdVideoManager", "[showVideoAd] already has 2 video ad file");
        } else {
            this.a.g();
        }
    }
}