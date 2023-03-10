package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    @SuppressLint({"MissingInflatedId", "ObjectAnimatorBinding"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        EditText editText = (EditText) findViewById(R.id.text1);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        //如果是View，就是getBackground
//        TransitionDrawable drawable = (TransitionDrawable) imageView.getDrawable();
//        drawable.startTransition(1000);
//
//        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
//        ScaleDrawable scaleDrawable = (ScaleDrawable) imageView1.getDrawable();
//        scaleDrawable.setLevel(1);
//
//        //剪切
//        ImageView testClip = (ImageView) findViewById(R.id.imageView3);
//        ClipDrawable clipDrawable = (ClipDrawable) testClip.getDrawable();
//        clipDrawable.setLevel(5000);
//        Log.d("Ning","1");
//        Button button = (Button) findViewById(R.id.button);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.filename);
//        button.startAnimation(animation);
//        button.setOnClickListener(this);


        //代码设置动画
        //创建了一个透明度动画，在300ms内将一个Button从0变为1
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
//        alphaAnimation.setDuration(3000);
//        button.startAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

//        Button mButton = (Button) findViewById(R.id.button);
//        mButton.setBackgroundResource(R.drawable.animation_drawable);
//        AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();
//        drawable.start();
//
//        ListView listView = (ListView) findViewById(R.id.list1);
//        String[] data = {"apply","banana","sfaf","sfafaw","fawerwe","24","fwefa","fawfawf"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this , android.R.layout.simple_list_item_1,data);
//        listView.setAdapter(arrayAdapter);
//        Animation animation = AnimationUtils.loadAnimation(this , R.anim.anim_item);
//        LayoutAnimationController controller = new LayoutAnimationController(animation);
//        controller.setDelay(0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listView.setLayoutAnimation(controller);

//        Object mObject = new Object();
//        ObjectAnimator.ofFloat(mObject , "translationY" , -35).start();

//        ValueAnimator colorAnim = ObjectAnimator.ofInt(listView , "backgroundColor" , 0xFFFF8080 , 0xFF8080FF);
//        colorAnim.setDuration(3000);
//        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
//        colorAnim.start();
//
//
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(listView , "rotationX" , 0 ,360),
//                ObjectAnimator.ofFloat(listView , "rotationY" , 0 ,180),
//                ObjectAnimator.ofFloat(listView , "rotation" , 0 ,-90),
//                ObjectAnimator.ofFloat(listView , "translationX" , 0 ,90),
//                ObjectAnimator.ofFloat(listView , "translationY" , 0 ,90),
//                ObjectAnimator.ofFloat(listView , "scaleX" , 1 ,1.5f),
//                ObjectAnimator.ofFloat(listView , "scaleY" , 1 ,0.5f),
//                ObjectAnimator.ofFloat(listView , "alpha" , 1 ,0.25f,1)
//        );
//        set.setDuration(5 * 1000).start();

        button = (Button)findViewById(R.id.button);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator( button.getContext(), R.animator.donghua1);
        set.setTarget(button);
        button.setOnClickListener(this);
        Log.d("Ning","21");


    }

    private void performAnimate(){
        ViewWrapper wrapper = new ViewWrapper(button);
        ObjectAnimator.ofInt(wrapper , "width", 500).setDuration(5000).start();;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                performAnimate1(button , button.getWidth() , 500);
                Log.d("Ning","click");
        }
    }

    private static class ViewWrapper{
        private View mTarget;

        public ViewWrapper(View target){
            mTarget = target;
        }

        public int getWidth(){
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width){
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();;
        }
    }


    private void performAnimate1(final View target , final int start , final int end){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候调用。
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前动画的进度值，整形1~100之间。
                int currentValue = (Integer)animation.getAnimatedValue();
                Log.d("Ning", "onAnimationUpdate: " + currentValue);

                //获取当前进度占整个动画过程的比例、浮点型，0~1之间
                float fraction = animation.getAnimatedFraction();

                //直接调用整形估值器，通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction , start , end);
                target.requestLayout();;
            }
        });

        valueAnimator.setDuration(5000).start();
    }


}