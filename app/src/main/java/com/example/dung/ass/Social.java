package com.example.dung.ass;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Social extends AppCompatActivity {
    private ProfilePictureView imgProfilePictureView;
    private LoginButton loginButton;
    private Button btnDangxuat;
    private TextView textviewName;
    private TextView textviewEmail;
    private TextView textviewFirstName;
    private Button btnChucnang;
    CallbackManager callbackManager;
    private String name,email,first_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_social);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.dung.ass",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        Anhxa();

        btnChucnang.setVisibility(View.INVISIBLE);
        btnDangxuat.setVisibility(View.INVISIBLE);
        textviewEmail.setVisibility(View.INVISIBLE);
        textviewName.setVisibility(View.INVISIBLE);
        textviewFirstName.setVisibility(View.INVISIBLE);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));

        setLogin_Button();
        setLogout_Button();
        chuyenManhinh();

    }
    private void chuyenManhinh(){
        btnChucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Social.this,Manhinhchucnang.class);
                startActivity(intent);
            }
        });
    }
    private void setLogout_Button(){
        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btnDangxuat.setVisibility(View.INVISIBLE);
                btnChucnang.setVisibility(View.INVISIBLE);

                textviewEmail.setVisibility(View.INVISIBLE);
                textviewName.setVisibility(View.INVISIBLE);
                textviewFirstName.setVisibility(View.INVISIBLE);
                textviewEmail.setText("");
                textviewName.setText("");
                textviewFirstName.setText("");
                imgProfilePictureView.setProfileId(null);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setLogin_Button(){
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginButton.setVisibility(View.INVISIBLE);
                btnChucnang.setVisibility(View.VISIBLE);
                btnDangxuat.setVisibility(View.VISIBLE);
                textviewEmail.setVisibility(View.VISIBLE);
                textviewName.setVisibility(View.VISIBLE);
                textviewFirstName.setVisibility(View.VISIBLE);
                result();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    private void result(){
        GraphRequest graphRequest=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email=object.getString("email");
                    name=object.getString("name");
                    first_name=object.getString("first_name");
                    imgProfilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    textviewEmail.setText(email);
                    textviewName.setText(name);
                    textviewFirstName.setText(first_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }

    public void Anhxa() {
        imgProfilePictureView = (ProfilePictureView) findViewById(R.id.imgProfilePictureView);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        btnDangxuat = (Button) findViewById(R.id.btnDangxuat);
        textviewName = (TextView) findViewById(R.id.textviewName);
        textviewEmail = (TextView) findViewById(R.id.textviewEmail);
        textviewFirstName = (TextView) findViewById(R.id.textviewFirstName);
        btnChucnang = (Button) findViewById(R.id.btnChucnang);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
