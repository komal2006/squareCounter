package com.example.squarecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    //    Display display = getWindowManager().getDefaultDisplay();
    boolean isClockwise = true;
    boolean goToTop = false;
    boolean goToBottom = false;
    boolean goToLeft = false;
    boolean goToRight = true;
    View square;
    TextView labelCounter;
    ConstraintLayout constraintLayout;
    int counter = 0;
    //setting random color
    Random rand = new Random();
    int r = rand.nextInt(254)+1;
    int g = rand.nextInt(254)+1;
    int b = rand.nextInt(254)+1;
    int randomColor = Color.rgb(r,g,b);
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.mainLayout);
        square= findViewById(R.id.square);
        labelCounter = findViewById(R.id.lblCounter);
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                moveBox();
            }
        },0, 50);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                moveBox();
//            }
//        };
    }
    private void moveBox(){
        int screenHeight = constraintLayout.getHeight();
        int screenWidth = constraintLayout.getWidth();
        float xPosition = square.getX();
        float yPosition = square.getY();
        if (isClockwise) {
            if (xPosition > screenWidth - 55) {
                resetBools();
                goToBottom = true;
            }
            if (yPosition > screenHeight - 55) {
                resetBools();
                goToLeft = true;
            }
            if (goToLeft && xPosition < 10) {
                resetBools();
                goToTop = true;
            }
            if (goToTop && yPosition < 50) {
                resetBools();
                goToBottom = true;
                isClockwise = false;
                square.setBackgroundColor(randomColor);
                //viewBox.backgroundColor = .random
                counter += 1;
                //labelCounter.setText(counter);
                //viewLbl.text = "\(counter)"
            }
        }else {
            if (goToBottom && yPosition > screenHeight - 55) {
                resetBools();
                goToRight = true;
            }
            if (goToRight && xPosition > screenWidth - 55) {
                resetBools();
                goToTop = true;
            }
            if (goToTop && yPosition < 50) {
                resetBools();
                goToLeft = true;
            }
            if (goToLeft && xPosition < 10) {
                resetBools();
                goToRight = true;
                isClockwise = true;
                square.setBackgroundColor(randomColor);
                //viewBox.backgroundColor = .random
                counter += 1;
                //viewLbl.text = "\(counter)"
                // labelCounter.setText(counter);
            }
        }
        if (goToBottom) {
            yPosition += 10;
        } else if (goToTop) {
            yPosition -= 10;
        } else if (goToLeft) {
            xPosition -= 10;
        } else if (goToRight) {
            xPosition += 10;
        }
        //print("x position \(xPosition)")
        //print("y position \(yPosition)")
//        let width = viewBox.frame.size.width
//        let height = viewBox.frame.size.height
        square.setX(xPosition);
        square.setY(yPosition);
        //self.viewBox.frame = CGRect(x: xPosition, y: yPosition, width: width, height: height)
    }
    private void resetBools() {
        goToTop = false;
        goToBottom = false;
        goToLeft = false;
        goToRight = false;
    }
}



