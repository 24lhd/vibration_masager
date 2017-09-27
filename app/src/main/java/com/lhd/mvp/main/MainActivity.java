package com.lhd.mvp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhd.config.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.eco.com.eco_vibrate_massage.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtState)
    TextView txtState;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
//        Config.chayRung(getBaseContext(), 1000, 1);
    }

    public void onClick(View view) {
        Config.isRunning = true;
        Config.rungPosition(this, Integer.parseInt((String) view.getTag()));
        if (Config.isRunning) {
            txtState.setText(getResources().getText(R.string.start_massa));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibration);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
//                    expandableListViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            relativeLayout.startAnimation(animation);
//            txtState.startAnimation(animation);
        } else {
            relativeLayout.clearAnimation();
            txtState.clearAnimation();
            txtState.setText(getResources().getText(R.string.stop_massa));
        }

    }

    @OnClick(R.id.relativeLayout)
    public void onViewClicked() {
        Config.isRunning = !Config.isRunning;
        if (Config.isRunning) {
            View view = new View(this);
            view.setTag("1");
            onClick(view);
        } else Config.stopRung();
        if (Config.isRunning)
            txtState.setText(getResources().getText(R.string.start_massa));
        else {
            relativeLayout.clearAnimation();
//            txtState.clearAnimation();
            txtState.setText(getResources().getText(R.string.stop_massa));
        }


    }
}
