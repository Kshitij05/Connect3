package com.kshitij.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = purple, 1 = red

    int activePlayer = 0; //start will always be purple
    // int activePlayer must be STRICTLY WRITTEN ABOVE THE PUBLIC VOID DROPIN METHOD!!!!!

    boolean gameIsActive = true;

    //2 represents unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view; //this time we will only do view because this is the only thing we have to deal with rn



        System.out.println(counter.getTag().toString()); //this is to give the tags in the console
                                                         //basically to check whether the tags are working or not.

        int tappedCounter = Integer.parseInt(counter.getTag().toString()); // setting tappedCounter equal to the tag

        if (gameState[tappedCounter] == 2 && gameIsActive ) //if the box is untapped (initially it's value will always be 2)
        {

            gameState[tappedCounter] = activePlayer; //now gameState[tappedCounter]'s value will be either 1 or 0 but not 2
                                                    // now the gameState of this particular area will not be 2
                                                    // and if this area is tapped again no code will be executed
                                                    // there is no need of else statement as we need not do anything in the other condition
            counter.setTranslationY(-1000f); //this will send the image which is not visible (as there is no src) up


            if (activePlayer == 0) {


                counter.setImageResource(R.drawable.purple);

                activePlayer = 1;


            } else
            {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0; //change the activePlayer back to 0 (that is purple)

            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(500);
            for (int[] winningPosition : winningPositions)
            {


             if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                     gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
             {

                  gameIsActive = false;



                 if (gameState[winningPosition[0]] == 0) //winner is purple
                 {

                     TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                     winnerMessage.setText("Purple has won!");

                 }else if (gameState[winningPosition[2]] == 1){

                     TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                     winnerMessage.setText("Red has won!");

                 }
                 // Nifty
                 else {

                     boolean gameIsOver = true;

                     for (int counterState : gameState){


                         if (counterState == 2) gameIsOver = false;

                     }
                     if (gameIsOver) {

                         TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                         winnerMessage.setText("It's a draw");

                         LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                         layout.setVisibility(View.VISIBLE);




                     }



                 }


                 LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); //we have got out layout now

                 layout.setVisibility(View.VISIBLE); //setting visibility to visible
                 // Can animate as well by layout.setTranslation(-1000f) and sending it at the top, out of the screen.
                 // and then bringing it back using layout.animate(). and whatever animation you want to add in there.








             }

            }


        }

    }

    public void playAgain(View view){

        gameIsActive = true;


        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE); //making the layout invisible again

        activePlayer = 0; //again Setting the active player to 0

        for (int i = 0; i < gameState.length; i++){ //gameState.length = 9


            gameState[i] = 2; //setting it back to unplayed




        }
        //resetting the image sources back to 0
        //nifty code

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i < gridLayout.getChildCount(); i++)
        {



            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0); //VERY NIFTY
                                                                       // this nifty part is used to reset the images


        }






    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
