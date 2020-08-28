package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> options = new ArrayList<Integer>();
    Button goButton;
    Button button1;
    Button button2;
    Button button3;
    Button button0;
    TextView timerTextView;
    TextView scoreTextView;
    TextView sumTextView;
    GridLayout optionsGridLayout;
    String sum;
    int a,b,pos;
    int wrongAnswer;
    TextView resultTextView;
    int totalQuestions = 0;
    int correctQuestions = 0;
    Button playAgainButton;
    CountDownTimer countDownTimer;
    public void start(View view)
    {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button3.setEnabled(false);
                button2.setEnabled(false);
                button1.setEnabled(false);


            }
        }.start();
        goButton.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        generateSum();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button)findViewById(R.id.goButton);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setVisibility(View.INVISIBLE);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        sumTextView.setVisibility(View.INVISIBLE);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button0 = (Button)findViewById(R.id.button0);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);


    }
    public void generateSum()
    {
        Random random = new Random();
        a = random.nextInt(21);
        b = random.nextInt(21);
        sum = Integer.toString(a+b);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        pos = random.nextInt(4);
        options.clear();
        for(int i=0; i<4; i++)
        {
            if(i==pos)
            {
                options.add(a+b);
            }
            else
            {
                wrongAnswer = random.nextInt(41);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer = random.nextInt(41);
                }
                options.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(options.get(0)));
        Log.i("Option",Integer.toString(options.get(0)));
        button1.setText(Integer.toString(options.get(1)));
        Log.i("Option",Integer.toString(options.get(1)));
        button2.setText(Integer.toString(options.get(2)));
        Log.i("Option",Integer.toString(options.get(2)));
        button3.setText(Integer.toString(options.get(3)));
        Log.i("Option",Integer.toString(options.get(3)));

    }
    public void chooseOption(View view)
    {
        Log.i("Option chosen by user",view.getTag().toString());
        totalQuestions++;
        if(Integer.valueOf(view.getTag().toString())==pos)
        {
            resultTextView.setText("CORRECT!");
            correctQuestions++;
        }
        else
        {
            resultTextView.setText("INCORRECT!");
        }
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(Integer.toString(correctQuestions) + "/" + Integer.toString(totalQuestions));
        generateSum();

    }
    public void playAgain(View view)
    {
        countDownTimer.start();
        generateSum();
        button0.setEnabled(true);
        button3.setEnabled(true);
        button2.setEnabled(true);
        button1.setEnabled(true);
        scoreTextView.setText("0/0");
        totalQuestions = 0;
        correctQuestions = 0;

    }


}
