package com.woolf.cleanapp.domain.executor;


import io.reactivex.Scheduler;

public interface IThreadExecutor {
    Scheduler getMainThread();

    Scheduler getBackgroundThread();
}
