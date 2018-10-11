package com.example.tarunpreet_singh.tictactoe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity
{
    boolean turn = true;
    int arr[] = new int[9];
    char board[][] = new char[3][3];
    int x = 0;
    int won=3;
    static private int no_won1 = 0;
    static private int no_won2 = 0;
    TextView pl1;
    TextView pl2;
    TextView sc1;
    TextView sc2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        for (int i = 0; i < 9; i++)
        {
            arr[i] = -1;
        }
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = '1';
            }
        }
        pl1 = (TextView) findViewById(R.id.textView5);
        pl2 = (TextView) findViewById(R.id.textView6);
        sc1 = (TextView)findViewById(R.id.score1);
        sc2 = (TextView)findViewById(R.id.score2);
        setbackground();
        Intent i = getIntent();
        pl1.setText(i.getStringExtra("name"));
        pl2.setText(i.getStringExtra("name1"));
        Button btn=(Button)findViewById(R.id.playAgain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

    }
    public void setbackground()
    {
        if(turn)
        {
            pl1.setBackgroundColor(0xFFE009D9);
            sc1.setBackgroundColor(0xFFE009D9);
            pl2.setBackgroundColor(0x00000000);
            sc2.setBackgroundColor(0x00000000);
        }
        else
        {
            pl2.setBackgroundColor(0xFFE009D9);
            sc2.setBackgroundColor(0xFFE009D9);
            sc1.setBackgroundColor(0x00000000);
            pl1.setBackgroundColor(0x00000000);
        }

    }
    public void imageTapped(View view)
    {
        final ImageView imageNew = (ImageView) view;
        x = Integer.parseInt(imageNew.getTag().toString());
        if (won==3)
        {
            if(arr[x]==-1)
            {

                if (turn) {
                    imageNew.setImageResource(R.drawable.cross4);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(imageNew,"rotation",360f);
                    animator.setDuration(1000);
                    animator.start();
                    turn = false;
                    arr[x] = 1;
                    boardmanager('x', x);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            imageNew.setRotation(0);
                        }
                    });

                } else {
                    imageNew.setImageResource(R.drawable.lop);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(imageNew,"rotation",360f);
                    animator.setDuration(1000);
                    animator.start();
                    turn = true;
                    arr[x] = 0;
                    boardmanager('o', x);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            imageNew.setRotation(0);
                        }
                    });
                }
                won = getStatus();
                declareWinner(won);
            }
            else
            {
                Toast.makeText(this, "PLACE TAKEN", Toast.LENGTH_SHORT).show();
            }
           setbackground();
        }
        else

        {
            reset();
            won=3;
        }


    }
    public void declareWinner(int won)
    {
        if (won == 1)
        {
            Toast.makeText(this, pl1.getText().toString() + " WON", Toast.LENGTH_LONG).show();
            PlayActivity.no_won1++;
            sc1.setText(String.valueOf(PlayActivity.no_won1));

        } else if (won == 2)
        {
            Toast.makeText(this, pl2.getText().toString() + " WON", Toast.LENGTH_LONG).show();
            PlayActivity.no_won2++;
            sc2.setText(String.valueOf(PlayActivity.no_won2));
        }
        else if (won == 0)
        {
            Toast.makeText(this, "GAME DRAW", Toast.LENGTH_LONG).show();

        }
    }
    public void boardmanager(char symbol,int x)
    {
        if(x<=2)
        {
            board[0][x]=symbol;
        }
        if(x>2&&x<6)
        {
            board[1][x-3]=symbol;
        }
        if(x>=6)
        {
            board[2][x-6]=symbol;
        }
    }
    public int getStatus() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] =='x') {
                    return 1;
                } else if (board[i][0] =='o') {
                    return 2;
                }
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] =='x') {

                    return 1;
                } else if (board[0][i] =='o') {
                    return 2;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] =='x') {
                return 1;
            } else if (board[0][0] =='o') {
                return 2;
            }
        }

        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] =='x') {
                return 1;
            } else if (board[0][2] =='o') {
                return 2;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] !='x' && board[i][j] !='o') {
                    return 3;
                }
            }
        }
        return 0;
    }
    public void reset()
    {
        for (int i = 0; i < 9; i++)
        {
            arr[i] = -1;
        }
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = '1';
            }
        }
        turn=true;
        LinearLayout linear1=(LinearLayout)findViewById(R.id.ln1);
        for (int i=0;i<linear1.getChildCount();i++)
        {
            ((ImageView) linear1.getChildAt(i)).setImageResource(R.drawable.icon);
        }
        LinearLayout linear2=(LinearLayout)findViewById(R.id.ln2);
        for (int i=0;i<linear2.getChildCount();i++)
        {
            ((ImageView) linear2.getChildAt(i)).setImageResource(R.drawable.icon);
        }
        LinearLayout linear3=(LinearLayout)findViewById(R.id.ln3);
        for (int i=0;i<linear3.getChildCount();i++)
        {
            ((ImageView) linear3.getChildAt(i)).setImageResource(R.drawable.icon);
        }
      setbackground();
    }

}