package com.woolf.cleanapp.util.executor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThreadExecutor implements IThreadExecutor {

    @Override
    public Scheduler getMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler getBackgroundThread() {
        return Schedulers.io();
    }
}
