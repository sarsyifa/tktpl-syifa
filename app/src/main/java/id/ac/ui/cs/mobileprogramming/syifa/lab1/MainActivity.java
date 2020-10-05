package id.ac.ui.cs.mobileprogramming.syifa.lab1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("onCreate, seconds", Integer.toString(seconds));
        Log.d("onCreate, running", Boolean.toString(running));

        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds");
            this.running = savedInstanceState.getBoolean("running");
        }
        activateTimer();
    }

    /**
     * This method gets called when the Back button is pressed.
     * The activity is finishing or being destroyed by the system.
     *
     */
    @Override
    public void onBackPressed() {
        Log.d("onBackPressed, seconds", Integer.toString(seconds));
        Log.d("onBackPressed, running", Boolean.toString(running));

        AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
            alert_builder.setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("Lab 3")
                .setMessage("Are you sure you want to exit? " +
                        "All progress in this session will be lost")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }


                })
                .setNegativeButton("No", null)
                .show()
                        .getButton(DialogInterface.BUTTON_POSITIVE)
                        .setTextColor(getResources().getColor(R.color.stopButton));

    }

    /**
     * Sets the Number of seconds on the timer.
     * The activateTimer() method uses a Handler to increment the seconds and update the text view.
     *
     */
    private void activateTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),
                        "%02d.%02d.%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * Start the stopwatch running when the Start button is clicked.
     * Below method gets called when the Start button is clicked.
     *
     */
    public void onStartButtonClicked(View view) {
        Log.d("onStart, seconds", Integer.toString(seconds));
        Log.d("onStart, running", Boolean.toString(running));

        running = true;
    }

    /**
     * Stop the stopwatch running when the Stop button is clicked.
     * Below method gets called when the Stop button is clicked.
     *
     */
    public void onStopButtonClicked(View view) {
        Log.d("onStop, seconds", Integer.toString(seconds));
        Log.d("onStop, running", Boolean.toString(running));

        running = false;
    }

    /**
     * Reset the stopwatch running when the Reset button is clicked.
     * Below method gets called when the Reset button is clicked.
     *
     */
    public void onResetButtonClicked(View view) {
        Log.d("onReset, seconds", Integer.toString(seconds));
        Log.d("onReset, running", Boolean.toString(running));

        running = false;
        seconds = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * If the activity is resumed,
     *  start the stopwatch again if it was running previously.
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume, seconds", Integer.toString(seconds));
        Log.d("onResume, running", Boolean.toString(running));
    }

    /**
     * If the activity is paused, stop the stopwatch.
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause, seconds", Integer.toString(seconds));
        Log.d("onPause, running", Boolean.toString(running));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart, seconds", Integer.toString(seconds));
        Log.d("onRestart, running", Boolean.toString(running));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy, seconds", Integer.toString(seconds));
        Log.d("onDestroy, running", Boolean.toString(running));
    }

    /**
     * Save the state of the stopwatch if the activity is no longer visible
     * and the timer keeps running.
     *
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", this.seconds);
        outState.putBoolean("running", this.running);

        Log.d("onSaveInstanceState, seconds", Integer.toString(seconds));
        Log.d("onSaveInstanceState, running", Boolean.toString(running));
    }
}