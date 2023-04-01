package com.example.materialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9]+(\\.[a-zA-z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    TextInputLayout tl_username;
    TextInputLayout tl_password;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl_username = (TextInputLayout) findViewById(R.id.tl_userName);
        tl_password = (TextInputLayout) findViewById(R.id.tl_password);
        button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button , "translationX" , 200);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet set = new AnimatorSet();


        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX" , 1.0f , 1.5f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX" , 0.0f , 90.0f , 0.0f);
        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("alpha" , 1.0f , 0.3f , 1.0f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(button , valuesHolder1 , valuesHolder2 , valuesHolder3);
        objectAnimator1.setDuration(2000).start();

    } 

    public boolean validatePassword(String password){
        //验证密码格式是否正确
        return password.length() > 6 ;
    }

    public boolean validatedUserName(String username){
        //验证账号的格式是否正确
        matcher = pattern.matcher(username);
        return matcher.matches();
    }


    //点击登录
    private void login(){
        //获取用户的输入信息
        String username = tl_username.getEditText().getText().toString();
        String password = tl_password.getEditText().getText().toString();

        //验证账号和密码
        if(!validatedUserName(username)){
            tl_username.setErrorEnabled(true);
            tl_username.setError("请输入正确的邮箱地址");
        }else if(!validatePassword(password)){
            tl_password.setErrorEnabled(true);
            tl_password.setError("密码数字过少");
        }else{
            tl_username.setErrorEnabled(false);
            tl_password.setErrorEnabled(false);
        }
    }


    public static class MyView{
        private View mTarget;
        private MyView(View mTarget){
            this.mTarget = mTarget;
        }
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }
        public void setWidth(int width){
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

}