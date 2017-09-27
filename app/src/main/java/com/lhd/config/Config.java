package com.lhd.config;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by d on 9/26/2017.
 */

public class Config {
    public static Vibrator vibrator;
    public static boolean isRunning = false;

    public static void chayRung(Context context, int running, int sleep) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] run = {0, running, sleep};
        isRunning = true;
        vibrator.vibrate(run, 0);
    }

    public static void stopRung() {
        isRunning = false;
        vibrator.cancel();
    }

//    public static void run() {
//        isRunning = !isRunning;
//        if (isRunning) {
//
//        }
//    }

    public static void rungPosition(Context context, int positon) {
        Toast.makeText(context, "" + positon, Toast.LENGTH_SHORT).show();
        if (isRunning) {
            switch (positon) {
                case 1:
                    Log.e("leuleu", " 500, 1");
                    chayRung(context, 1000, 1);
                    break;
                case 2:
                    Log.e("leuleu", " 500, 1");
                    chayRung(context, 500, 1);
                    break;
                case 3:
                    Log.e("leuleu", "250, 250");
                    chayRung(context, 250, 250);
                    break;
                case 4:
                    Log.e("leuleu", " 500, 250");
                    chayRung(context, 500, 250);
                    break;
                case 5:
                    Log.e("leuleu", " 500, 500");
                    chayRung(context, 500, 500);
                    break;
                case 6:
                    Log.e("leuleu", "1000, 500");
                    chayRung(context, 1000, 500);
                    break;
            }
        } else {
            stopRung();
        }

    }


}
