package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.nx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nz implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!nz.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<agw> c;
    private final Provider<lr> d;
    private final Provider<ql> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.b.get();
        aVar.b = (agw) this.c.get();
        aVar.c = (lr) this.d.get();
        aVar.d = (ql) this.e.get();
    }

    private nz(Provider<Context> provider, Provider<agw> provider2, Provider<lr> provider3, Provider<ql> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<Context> provider, Provider<agw> provider2, Provider<lr> provider3, Provider<ql> provider4) {
        return new nz(provider, provider2, provider3, provider4);
    }
}