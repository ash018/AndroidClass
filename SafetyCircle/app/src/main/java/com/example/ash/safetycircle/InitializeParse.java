package com.example.ash.safetycircle;

import com.parse.Parse;
import android.app.Application;
/**
 * Created by Ash on 4/6/2016.
 */
public class InitializeParse extends Application {

    @Override
    public void onCreate() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this,"sq6YoPYvkbyinY7hi6XL3jzxsLE6IqpwX5imu7uk","kd6O3ChBgE8DqUozCU4zyVsW2f2PNtfPpNpk5uqx");
    }
}
