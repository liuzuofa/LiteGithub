package com.summer.litegithub.model.api;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 *  项目名：  LiteGithub
 *  包名：    com.summer.litegithub.model.api
 *  文件名:   HttpObserver
 *  创建者:   Summers
 *  创建时间: 2018/7/17 23:11
 *  描述：    TODO
 */
public abstract class HttpObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t);

    @Override
    public abstract void onError(Throwable e);

    @Override
    public void onComplete() {

    }
}
