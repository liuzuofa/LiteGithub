package com.summer.litegithub.contract;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.contract
 *  文件名:   WebViewContract
 *  创建者:   Summers
 *  创建时间: 2018/8/2120:52
 *  描述：    TODO
 */
public class WebViewContract {
    public interface View extends AbstractView{
        
    }

    public interface Presenter extends AbsPresenter<View>{

    }
}
