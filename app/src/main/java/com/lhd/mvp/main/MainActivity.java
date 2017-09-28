package com.lhd.mvp.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.im_bg_app)
    ImageView imBgApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
//        Config.chayRung(getBaseContext(), 1000, 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        Glide.with(this).load(R.drawable.bg_app).into(imBgApp);
    }

    public void onClick(View view) {
        Config.isRunning = true;
        int positon = Config.rungPosition(this, Integer.parseInt((String) view.getTag()));

        if (Config.isRunning) {
            updateState(positon);
            txtState.setText(getResources().getText(R.string.stop_massa));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_free);
            switch (positon) {
                case 1:
                    Log.e("leuleu", " 500, 1");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_free);
                    break;
                case 2:
                    Log.e("leuleu", "250, 250");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_250);
                    break;
                case 3:
                    Log.e("leuleu", " 500, 250");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_250);
                    break;
                case 4:
                    Log.e("leuleu", " 500, 500");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_500);
                    break;
                case 5:
                    Log.e("leuleu", "1000, 500");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_500);
                    break;
            }
            relativeLayout.startAnimation(animation);
        } else {
            relativeLayout.clearAnimation();
            txtState.clearAnimation();
            txtState.setText(getResources().getText(R.string.stop_massa));
            updateState(0);
        }
    }

    private void updateState(int positon) {
        Button[] btnItems = new Button[]{button1, button2, button3, button4, button5};
        for (Button btn : btnItems)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                btn.setBackground(getResources().getDrawable(R.drawable.shape_btn_unselect));
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && positon != 0) {
            btnItems[positon - 1].setBackground(getResources().getDrawable(R.drawable.shape_btn_select));
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
        if (Config.isRunning) {
            txtState.setText(getResources().getText(R.string.stop_massa));
            updateState(1);
        } else {
            relativeLayout.clearAnimation();
            txtState.setText(getResources().getText(R.string.start_massa));
            updateState(0);
//            txtState.clearAnimation();

        }


    }

    @OnClick(R.id.im_store)
    public void onViewClicked2() {
        Config.goToStoreByPackageName(this);
    }
}
