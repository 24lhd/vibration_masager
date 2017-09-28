package com.lhd.config;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    public static void goToStoreByPackageName(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
//        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
//                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static int rungPosition(Context context, int positon) {
        Toast.makeText(context, "" + positon, Toast.LENGTH_SHORT).show();
        if (isRunning) {
            switch (positon) {
                case 1:
                    Log.e("leuleu", " 500, 1");
                    chayRung(context, 1000, 1);
                    return 1;
                case 2:
                    Log.e("leuleu", "250, 250");
                    chayRung(context, 250, 250);
                    return 2;
                case 3:
                    Log.e("leuleu", " 500, 250");
                    chayRung(context, 500, 250);
                    return 3;
                case 4:
                    Log.e("leuleu", " 500, 500");
                    chayRung(context, 500, 500);
                    return 4;
                case 5:
                    Log.e("leuleu", "1000, 500");
                    chayRung(context, 1000, 500);
                    return 5;
            }
        } else {
            stopRung();
            return 0;
        }

        return positon;
    }


}
