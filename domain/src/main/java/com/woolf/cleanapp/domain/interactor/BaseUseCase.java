package com.woolf.cleanapp.domain.interactor;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;


public abstract class BaseUseCase<T, Params> {

    private CompositeDisposable disposables;
    private Scheduler mainThread;
    private Scheduler backgroundThread;

    abstract Flowable<T> buildUseCaseObservable(Params params);

    public BaseUseCase(Scheduler mainThread,Scheduler backgroundThread) {
        this.mainThread = mainThread;
        this.backgroundThread = backgroundThread;
        disposables = new CompositeDisposable();
    }

    /**
     * Need call
     */
    public void execute(final DisposableSubscriber<T> disposableSubscriber, final Params params) {
        final Flowable<T> responseFlowable = getResponseFlowable(params);
        addDisposable(
                responseFlowable.subscribeWith(disposableSubscriber)
        );
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable localDisposable) {
        if (localDisposable == null) return;
        disposables.add(localDisposable);
    }

    private Flowable<T> getResponseFlowable(final Params params) {
        return buildUseCaseObservable(params)
                .onBackpressureLatest()
                .subscribeOn(backgroundThread)
                .observeOn(mainThread);
    }

}
