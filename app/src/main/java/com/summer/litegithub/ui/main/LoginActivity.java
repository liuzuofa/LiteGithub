package com.summer.litegithub.ui.main;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.summer.litegithub.Contract.LoginContract;
import com.summer.litegithub.R;
import com.summer.litegithub.base.activity.BaseActivity;
import com.summer.litegithub.data.login.User;
import com.summer.litegithub.presenter.login.LoginPresenter;
import com.summer.litegithub.util.app.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.main
 *  文件名:   LoginActivity
 *  创建者:   Summers
 *  创建时间: 2018/7/1618:16
 *  描述：    TODO
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    private LoginPresenter mPresenter;
    private String mUserName;
    private String mPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.bt_login)
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                mUserName = mEtUserName.getText().toString().trim();
                mPassword = mEtPassword.getText().toString().trim();
                mPresenter.login(mUserName,mPassword);
                break;
            case R.id.tv_register:
                JumpUtil.startActivity(LoginActivity.this,MainActivity.class);
                break;
        }
    }

    @Override
    public void loginSuccess(User user) {
        Toast.makeText(LoginActivity.this,"login success user name is " + user.getUsername(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String errorInfo) {

    }
}
