package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //0 is yellow , 1 is red , 2:empty
    int activePlayer=0;
    boolean gameActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.getTag();
        Log.i("Info", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2&&gameActive==true) {
            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                //Someone has one
                {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgain=(Button)findViewById(R.id.playAgain);
                    TextView WinnerTextView=(TextView)findViewById(R.id.WinnerTextView);
                    WinnerTextView.setText(winner + " has won.");
                    playAgain.setVisibility(View.VISIBLE);
                    WinnerTextView.setVisibility(View.VISIBLE);
                }

            }
        }
    }
        public void playAgain(View view) throws IOException {

            Button playAgainButton = (Button) findViewById(R.id.playAgain);

            TextView winnerTextView = (TextView) findViewById(R.id.WinnerTextView);

            playAgainButton.setVisibility(View.INVISIBLE);

            winnerTextView.setVisibility(View.INVISIBLE);

            androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
            for(int i=0; i<gridLayout.getChildCount(); i++) {

                ImageView counter = (ImageView) gridLayout.getChildAt(i);

                counter.setImageDrawable(null);

            }

            for (int i=0; i<gameState.length; i++) {

                gameState[i] = 2;

            }

            activePlayer = 0;

            gameActive = true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
