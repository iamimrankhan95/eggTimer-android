package com.iamimrankhan95.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    boolean isFirst=true;
    int secondRemaining;
    CountDownTimer countDownTimer;
    SeekBar seekBar;EditText editText;
    Button button;
    public void goButton(View view){
        button=(Button) view;
        if (button.getText().toString().equals("Go!")) {
            button.setText("Pause");
            if(isFirst){
                isFirst=false;
                int time=seekBar.getProgress();
                timer(time);
            }else{
                timer(secondRemaining);
            }


        }else{
            //System.out.println(button.getText());
            button.setText("Go!");
            countDownTimer.cancel();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateSeekBar();
    }

    private void timer(int time) {
        countDownTimer=new CountDownTimer(time*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int i =secondRemaining= (int)millisUntilFinished/1000;
                updateEditText(i);
            }

            @Override
            public void onFinish() {
                ImageView imageView=findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.broken_egg);
                editText.setVisibility(View.GONE);
                button.setText("Go!");

            }
        }.start();
    }

    private void activateSeekBar() {
        seekBar= findViewById(R.id.seekBar);
        editText= findViewById(R.id.editText);
        int max=600;
        seekBar.setMax(max);
        seekBar.setProgress(max-300);
        updateEditText(300);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secondRemaining=progress;
                updateEditText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    protected void updateEditText(int time){
        String min,sec;
        int sec2;
        min=Integer.toString(time/60); sec2 = time%60;
        sec=Integer.toString(sec2);
        if(sec2<=9){
            sec="0"+sec2;
        }
        editText.setText(min+":"+sec);
    }
}
