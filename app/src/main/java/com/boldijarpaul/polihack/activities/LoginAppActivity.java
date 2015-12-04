package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.boldijarpaul.polihack.R;
import com.squareup.picasso.Picasso;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginAppActivity extends AppCompatActivity implements OnLoginListener {

    @Bind(R.id.login_app_button)
    View mLogin;

    private SimpleFacebook mSimpleFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_app_button)
    void onLogin() {
        mSimpleFacebook.login(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mSimpleFacebook.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
        Profile.Properties properties = new Profile.Properties.Builder()
                .add(Profile.Properties.ID)
                .add(Profile.Properties.FIRST_NAME)
                .add(Profile.Properties.LAST_NAME)
                .build();
        mSimpleFacebook.getProfile(properties, new OnProfileListener() {
            @Override
            public void onComplete(Profile response) {
                super.onComplete(response);
                gotFacebookInfo(response.getId(), response.getFirstName() + " " + response.getLastName());
            }

            @Override
            public void onException(Throwable throwable) {
                super.onException(throwable);
                showToast(R.string.msg_unknown_error);
            }

            @Override
            public void onFail(String reason) {
                super.onFail(reason);
                showToast(R.string.msg_unknown_error);

            }
        });

    }

    private void gotFacebookInfo(String facebookId, String name) {
        Toast.makeText(this, facebookId + " " + name, Toast.LENGTH_SHORT).show();
    }

    private void showToast(@StringRes int textId) {
        Toast.makeText(this, textId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onException(Throwable throwable) {
        showToast(R.string.msg_unknown_error);

    }

    @Override
    public void onFail(String reason) {
        showToast(R.string.msg_unknown_error);
    }
}
