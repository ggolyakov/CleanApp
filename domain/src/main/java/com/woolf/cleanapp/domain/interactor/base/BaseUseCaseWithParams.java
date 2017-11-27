package com.woolf.cleanapp.domain.interactor.base;

import com.woolf.cleanapp.domain.executor.IThreadExecutor;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;


public abstract class BaseUseCaseWithParams<T, Params> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Single<T> buildUseCaseObservable(Params params);

    public BaseUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }

    /**
     * Need call
     */
    public void execute(final DisposableSingleObserver<T> disposableSubscriber, final Params params) {
        final Single<T> responseFlowable = getResponseSingle(params);
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

    private Single<T> getResponseSingle(final Params params) {
        return buildUseCaseObservable(params)
                .subscribeOn(threadExecutor.getBackgroundThread())
                .observeOn(threadExecutor.getMainThread());
    }

}
