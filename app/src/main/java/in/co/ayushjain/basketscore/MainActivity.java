package in.co.ayushjain.basketscore;

import android.app.Activity;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    int score_A=0;
    int score_B=0;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (TextView) findViewById(R.id.timer);
        time.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (t == 1) {
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
                    time.setTextColor(Color.BLUE);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }}

        });
    }
    public void point3AClick(View view) {
        score_A = score_A + 3;
        display_scoreA(score_A);

    }
    public void point3BClick(View view)
        {
            score_B=score_B+3;
            display_scoreB(score_B);

        }
    public void point2AClick(View view) {
        score_A = score_A + 2;
        display_scoreA(score_A);

    }
    public void point2BClick(View view)
    {
        score_B=score_B+2;
        display_scoreB(score_B);

    }
    public void point1AClick(View view) {
        score_A = score_A + 1;
        display_scoreA(score_A);

    }
    public void point1BClick(View view)
    {
        score_B=score_B+1;
        display_scoreB(score_B);

    }
    public void reset(View view)
    {
        score_A=0;
        display_scoreA(score_A);
        score_B=0;
        display_scoreB(score_B);
        starttime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedtime = 0L;
        t = 1;
        secs = 0;
        mins = 0;
        milliseconds = 0;
        handler.removeCallbacks(updateTimer);
        time.setText("00:00:00");
    }

    private void display_scoreA(int number)
    {
        TextView scoreA = (TextView) findViewById(R.id.textViewA);
        scoreA.setText(Integer.toString(number));
    }
    private void display_scoreB(int number)
    {
        TextView scoreB = (TextView) findViewById(R.id.textViewB);
        scoreB.setText(Integer.toString(number));
    }
    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            time.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }};
}
