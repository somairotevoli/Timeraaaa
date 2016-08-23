package com.ultrasonicnanosilver.apps.timeraaaa;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.CountDownTimer;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.lzyzsd.circleprogress.Utils;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import static android.R.attr.animationDuration;


public class MainActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    //private NotificationManager manager;
   // private Notification myNotication;
    private ImageView buttonStartTime;
    //private Button buttonInstruct;
    private ImageView buttonStopTime;
    DonutProgress progressBar;
    CircularProgressBar circularProgressBar;
    CircularProgressBar circularProgressBar2;
    //private EditText edtTimerValue;
    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private CountDownTimer countDownTimer2;
    private CountDownTimer countDownTimer3;
    private CountDownTimer countDownTimer4;
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long totalTimeCountInMilliseconds2;
    private long totalTimeCountInMilliseconds3;
    private long totalTimeCountInMilliseconds4;

    private long timeBlinkInMilliseconds; // start time of start blinking

    private boolean blink; // controls the blinking .. on and off
    private MediaPlayer player;
    private MediaPlayer player2;
    /** Called when the activity is first created. */

    //private FloatingActionButton btnFab;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar=(DonutProgress) findViewById(R.id.circle_progress);
        circularProgressBar = (CircularProgressBar)findViewById(R.id.cp);
        circularProgressBar2 = (CircularProgressBar)findViewById(R.id.cp2);
        setSupportActionBar(toolbar);
       // manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent myIntent = new Intent(MainActivity.this, InstructTab.class);
                // myIntent.putExtra("key", value); //Optional parameters
        //        MainActivity.this.startActivity(myIntent);
        //    }
       // });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        player = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        player2 = MediaPlayer.create(getApplicationContext(), R.raw.alarm2);
        buttonStartTime = (ImageView) findViewById(R.id.imageView);
        buttonStopTime = (ImageView) findViewById(R.id.imageView2);
        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);

        //CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.cp);
        //circularProgressBar.setProgress(0);
        //circularProgressBar2.setProgress(0);




        //CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.cp);
        // circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
        //circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        // circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        // circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        //int animationDuration = 5250000; // 2500ms = 2,5s


        TextView tx = (TextView)findViewById(R.id.tvTimeCount);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/abc.ttf");

        tx.setTypeface(custom_font);


        //edtTimerValue = (EditText) findViewById(R.id.edtTimerValue);

        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);

        buttonStopTime.setClickable(false);
        final ImageView buttonInstruct = (ImageView) findViewById(R.id.imageView7);
        buttonInstruct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add your code here..
                Intent myIntent = new Intent(MainActivity.this, InstructTab.class);
                // myIntent.putExtra("key", value); //Optional parameters
                        MainActivity.this.startActivity(myIntent);
            }
        });
        final ImageView buttonAnaly = (ImageView) findViewById(R.id.imageView8);
        buttonAnaly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add your code here..
                Intent myIntent = new Intent(MainActivity.this, Analysis.class);
                // myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });




    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        circularProgressBar2.clearAnimation();
        circularProgressBar.clearAnimation();
        countDownTimer.cancel();
        countDownTimer2.cancel();
        countDownTimer3.cancel();
        countDownTimer4.cancel();

    }

    @Override
    public void onClick(View v) {
        //cancel the old countDownTimer
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }

        if(countDownTimer2!=null){
            countDownTimer2.cancel();
        }

        if(countDownTimer3!=null){
            countDownTimer3.cancel();
        }
        if(countDownTimer4!=null){
            countDownTimer4.cancel();
        }
        if (v.getId() == R.id.imageView) {
           // textViewShowTime.setTextAppearance(getApplicationContext(),
                  //  R.style.normalText);
            setTimer();
            setTimer2();
            setTimer3();
            setTimer4();

            buttonStopTime.setClickable(true);
            buttonStopTime.setVisibility(View.VISIBLE);

            buttonStartTime.setVisibility(View.VISIBLE);
            circularProgressBar.setProgressWithAnimation(100, 2100000);
            circularProgressBar2.setProgressWithAnimation(100);
            //circularProgressBar2.setBackgroundColor(55000000);

            //edtTimerValue.setVisibility(View.GONE);
            //edtTimerValue.setText("");
            startTimer();
            startTimer2();
            startTimer3();
            startTimer4();

           buttonStartTime.setClickable(false);





        }  if (v.getId() == R.id.imageView2) {
            circularProgressBar2.setProgress(0);
            circularProgressBar.setProgress(0);
            countDownTimer.cancel();
            countDownTimer2.cancel();
            countDownTimer3.cancel();
            countDownTimer4.cancel();
            buttonStartTime.setVisibility(View.VISIBLE);
            buttonStopTime.setVisibility(View.VISIBLE);

            setTimer();
            setTimer2();
            setTimer3();
            setTimer4();

            //edtTimerValue.setVisibility(View.GONE);
            buttonStartTime.setClickable(true);
            textViewShowTime.setText("35:00");

        }

    }

    private void setTimer() {
        int time =35;
        totalTimeCountInMilliseconds = 60 * time * 1000;

        timeBlinkInMilliseconds = 30 * 1000;
    }

    private void setTimer2() {
        int time = 18;
        totalTimeCountInMilliseconds2 = 60 * time * 1000;
    }

    private void setTimer3() {
        int time = 1;
        totalTimeCountInMilliseconds3 = 60 * time * 1000;
    }
    private void setTimer4() {
        int time = 35;
        totalTimeCountInMilliseconds4 = 60 * time * 1000;
    }


    private void startTimer3() {
        countDownTimer3 = new CountDownTimer(totalTimeCountInMilliseconds3, 1000) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
               // int progress = (int) (leftTimeInMilliseconds/1000);
                int progress2 = (int) (leftTimeInMilliseconds/1000);


                progressBar.setProgress(progressBar.getProgress() -progress2 );
                circularProgressBar2.setProgressWithAnimation(circularProgressBar2.getProgress() -2);


            }

            @Override
            public void onFinish(){
            }

        }.start();
    }

    private void startTimer2() {
        countDownTimer2 = new CountDownTimer(totalTimeCountInMilliseconds2, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
            }

            @Override
            public void onFinish() {
                player2.start();
            }

        }.start();
    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;




                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    textViewShowTime.setTextAppearance(getApplicationContext(),
                            R.style.blinkText);
                    // change the style of the textview .. giving a red
                    // alert style

                    if (blink) {
                        textViewShowTime.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        textViewShowTime.setVisibility(View.INVISIBLE);
                    }

                    blink = !blink; // toggle the value of blink
                }

                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format

            }


            // Default duration = 1500ms


            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished

                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// This example will cause the phone to vibrate "SOS" in Morse Code
// In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
// There are pauses to separate dots/dashes, letters, and words
// The following numbers represent millisecond lengths
                int dot = 200;      // Length of a Morse Code "dot" in milliseconds
                int dash = 500;     // Length of a Morse Code "dash" in milliseconds
                int short_gap = 200;    // Length of Gap Between dots/dashes
                int medium_gap = 500;   // Length of Gap Between Letters
                int long_gap = 1000;    // Length of Gap Between Words
                long[] pattern = {
                        0,  // Start immediately
                        dot, short_gap, dot, short_gap, dot,    // s
                        medium_gap,
                        dash, short_gap, dash, short_gap, dash, // o
                        medium_gap,
                        dot, short_gap, dot, short_gap, dot,    // s
                        long_gap
                };

                //  Intent intent = new Intent("com.ultrasonicnanosilver.apps.timeraaaa");

                //  PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, 0);

                //   Notification.Builder builder = new Notification.Builder(MainActivity.this);

                //   builder.setAutoCancel(false);
                //   builder.setTicker("this is ticker text");
                //   builder.setContentTitle("WhatsApp Notification");
                //   builder.setContentText("You have a new message");
                //builder.setSmallIcon(R.drawable.ic_launcher);
                //   builder.setContentIntent(pendingIntent);
                //   builder.setOngoing(true);
                //   builder.setSubText("This is subtext...");   //API level 16
                //  builder.setNumber(100);
                //  builder.build();

                //  myNotication = builder.getNotification();
                //  manager.notify(11, myNotication);


                textViewShowTime.setText("Complete");
                textViewShowTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.VISIBLE);
                player.start();
                Toast.makeText(getApplicationContext(), "The Process Is Now Complete.",
                        Toast.LENGTH_LONG).show();
                v.vibrate(pattern, -1);

                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                if (countDownTimer2 != null) {
                    countDownTimer2.cancel();
                }
            }

        }.start();


    }
    private void startTimer4() {
        countDownTimer4 = new CountDownTimer(totalTimeCountInMilliseconds4,10) {
            // 500 means, onTick function will be called at every 500
            // milliseconds


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                int progress = (int) (leftTimeInMilliseconds / 1000);

                //circularProgressBar.setProgress(circularProgressBar.getProgress()+1);

            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }

        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }


            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

        }


    }






    private Context getActivity() {
        return null;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent myIntent = new Intent(MainActivity.this, About.class);
            MainActivity.this.startActivity(myIntent);
            return true;

        } else if (id == R.id.nav_privacy) {
            Intent myIntent = new Intent(MainActivity.this, Privacy.class);
            MainActivity.this.startActivity(myIntent);
            return true;

        } else if (id == R.id.nav_warranty) {
            Intent myIntent = new Intent(MainActivity.this, Warranty.class);
            MainActivity.this.startActivity(myIntent);
            return true;

        } else if (id == R.id.nav_maintenance) {
            Intent myIntent = new Intent(MainActivity.this, Maintenance.class);
            MainActivity.this.startActivity(myIntent);
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
