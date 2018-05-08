package com.example.faraz098.mines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.faraz098.mines.Options.BOARDSIZE;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1111;
    public static final int RESULT_OPTIONS = 1110;
    public static final String BOARD_SIZE_ROW = "boardSizeR";
    public static final String BOARD_SIZE_COL = "boardSizeC";
    public static final String NUMBER_OF_BOMBS = "number of bombs";
    public static final String NUMBER_OF_BOMBS1 = "numberOfBombs";
    public static final String TESTING = "testing";
    public static final String THE_PREFNUMBER = "thePrefnumber";
    public static final String THE_PREFNUMBER1 = "thePrefnumber";
    public static final String PLAYED = "played";
    public static final String PLAYED1 = "played";
    public static final String THE_PREF = "thePref";
    public static final String CHOSEN_NUMBER_OF_HEARTS = "chosen number of hearts";
    public static final String THE_PREF1 = "thePref";
    public static final String CHOSEN_NUMBER_OF_HEARTS1 = "chosen number of hearts";
    public static final String THE_PREF_2 = "thePref2";
    public static final String CHOSEN_NUMBER_OF_SIZE = "chosen number of size";
    public static final String THE_PREF2 = "thePref";
    public static final String SCORE = "score";
    public static final String THE_PREF3 = "thePref";
    public static final String THE_PREF4 = "thePref";
    public static final String SCORE1 = "score";
    public static final String THE_PREF_21 = "thePref2";
    public static final String THE_PREF5 = "thePref";
    public static final String THE_PREFNUMBER2 = "thePrefnumber";
    public static final String HIGH_SCORE = "highScore";
    public static final String HIGH_SCORE1 = "highScore";


    private int NUM_OF_BOMBS = 2;
    private int SIZE_OF_BOARD_ROW = 0;
    private int SIZE_OF_BOARD_COL = 0;
    private int games = 0;
    private boolean checkWelcome = false;

    /**
     * main class takes care of the user actions
     * it allows the user to move within activities and
     * it also allows the user to select either play...option...help....or exit
     * another feature of this class is that it holds the high score
     * and this class takes care of the saving the board size and ect
     *
     * */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        checkWelcome = intent.getBooleanExtra(TESTING,false);
        if(!checkWelcome){
            Intent intent2 = new Intent(MainActivity.this,WelcomePage.class);
            startActivity(intent2);

            checkWelcome = true;
        }

        btnExit();
        btnOption();
        btnPlay();
        btnHelp();
        txtHighScore();

    }

    private void txtHighScore() {


        TextView highScore =(TextView)findViewById(R.id.txtHighScore);

        int score = getTheScore(this , SIZE_OF_BOARD_ROW);
        highScore.setTextColor(Color.WHITE);
        highScore.setText("highest score is: " + score + "  size: "+ SIZE_OF_BOARD_ROW +
                " X " + SIZE_OF_BOARD_COL );

    }

    private void btnHelp() {
        Animation bubble = AnimationUtils.loadAnimation(this,R.anim.bubble);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        bubble.setInterpolator(interpolator);
        Button help =  (Button)findViewById(R.id.btnHelp);
        help.setAnimation(bubble);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = Help.makeIntentHelp(MainActivity.this );
                startActivity(intent);
            }
        });
        
    }

    public void btnExit(){

        Animation bubble = AnimationUtils.loadAnimation(this,R.anim.bubble);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        bubble.setInterpolator(interpolator);

        Button exitBtn = (Button)findViewById(R.id.btnExit);
        exitBtn.setAnimation(bubble);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

    public void btnOption(){
        Animation bubble = AnimationUtils.loadAnimation(this,R.anim.bubble);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        bubble.setInterpolator(interpolator);
        final Button options = (Button)findViewById(R.id.btnOptions_save);
        options.setAnimation(bubble);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = Options.makeIntentOptions(MainActivity.this );
                startActivityForResult(intent, RESULT_OPTIONS);

            }
        });



    }

    private void btnPlay() {
        Animation bubble = AnimationUtils.loadAnimation(this,R.anim.bubble);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        bubble.setInterpolator(interpolator);


        final Button play = (Button)findViewById(R.id.btnPlay);





        play.setAnimation(bubble);
        int oldBombs = getTheHearts(this);
        int oldSize = getTheSize(this);
        if(oldBombs != 0){
            NUM_OF_BOMBS = oldBombs;
        }
        if (oldSize!=0 ){
            setSize(oldSize);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent  = Play.makeIntentPlay(MainActivity.this );
                intent.putExtra(NUMBER_OF_BOMBS1 , NUM_OF_BOMBS);
                intent.putExtra(BOARD_SIZE_ROW, SIZE_OF_BOARD_ROW);
                intent.putExtra(BOARD_SIZE_COL, SIZE_OF_BOARD_COL);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    public void setGamePlay(int numGame){
        SharedPreferences pref = this.getSharedPreferences(THE_PREFNUMBER , MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(PLAYED, numGame);
        edt.apply();
    }
    static public int gettheGamePlay(Context context){

        SharedPreferences pref = context.getSharedPreferences(THE_PREFNUMBER1, MODE_PRIVATE);
        return pref.getInt(PLAYED1, 0);

    }

    public void setSize(int size){
        if(size ==4){
            SIZE_OF_BOARD_ROW = size;
            SIZE_OF_BOARD_COL = 6;


        }
        else if(size ==5){
            SIZE_OF_BOARD_ROW = size;
            SIZE_OF_BOARD_COL = 10;


        }
        else if(size == 6){
            SIZE_OF_BOARD_ROW = size;
            SIZE_OF_BOARD_COL = 15;


        }else{

            SIZE_OF_BOARD_ROW = size;
            SIZE_OF_BOARD_COL = size;
        }
    }

    public void setValues(String boardSize , String bomb_total){
        if(boardSize != null){

            int size = Integer.parseInt(boardSize);
            saveSize(size);
            setSize(size);


        }
        if(bomb_total != null){
            int bombcount = Integer.parseInt(bomb_total);
            NUM_OF_BOMBS = bombcount;
            saveHearts(bombcount);
        }




    }

    public void saveHearts(int optNumber){

        SharedPreferences pref = this.getSharedPreferences(THE_PREF, MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();

        edt.putInt(CHOSEN_NUMBER_OF_HEARTS, optNumber);
        edt.apply();

    }

    static public int getTheHearts(Context context){



        SharedPreferences pref = context.getSharedPreferences(THE_PREF1, MODE_PRIVATE);
        return pref.getInt(CHOSEN_NUMBER_OF_HEARTS1, 0);


    }

    public void saveSize(int optSize){

        SharedPreferences pref = this.getSharedPreferences(THE_PREF_2, MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(CHOSEN_NUMBER_OF_SIZE, optSize);
        edt.apply();


    }

    static public int getTheSize(Context context){



        SharedPreferences pref = context.getSharedPreferences("thePref2" , MODE_PRIVATE);
        return pref.getInt("chosen number of size" , 0);


    }

    public void saveScore(int score , int size){

        SharedPreferences pref = this.getSharedPreferences(THE_PREF2 +size , MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(SCORE +size , score);
        edt.apply();


    }
    public void clear( int size){

        SharedPreferences pref = this.getSharedPreferences(THE_PREF3 +size , MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.clear();
        edt.apply();


    }

    static public int getTheScore(Context context , int size){



        SharedPreferences pref = context.getSharedPreferences(THE_PREF4 +size , MODE_PRIVATE);
        return pref.getInt(SCORE1 +size , 0);


    }

    public void setnumberOfGames(){

        games++;
        setGamePlay(games);
        TextView gamesPlayed =(TextView)findViewById(R.id.txtGames);
        gamesPlayed.setTextColor(Color.WHITE);
        gamesPlayed.setText("played: " +gettheGamePlay(this));
    }

    public void clearPref(){
        SharedPreferences pref2 = this.getSharedPreferences(THE_PREF_21, MODE_PRIVATE);
        SharedPreferences.Editor edt2 = pref2.edit();
        edt2.clear();
        edt2.apply();
        SharedPreferences pref1 = this.getSharedPreferences(THE_PREF5, MODE_PRIVATE);
        SharedPreferences.Editor edt1 = pref1.edit();
        edt1.clear();
        edt1.apply();
        SharedPreferences pref3 = this.getSharedPreferences(THE_PREFNUMBER2, MODE_PRIVATE);
        SharedPreferences.Editor edt3 = pref3.edit();
        edt3.clear();
        edt3.apply();

       clear(4);
       clear(6);
       clear(5);
       clear(7);
       clear(9);

        finish();
        startActivity(getIntent());
        }

    public void whichBoard( int HighScore){
        if(SIZE_OF_BOARD_ROW == 4 ){
            saveScore(HighScore,4);


        }
        else if(SIZE_OF_BOARD_ROW == 5 ){
            saveScore(HighScore,5);


        }
        else if(SIZE_OF_BOARD_ROW == 6 ){
            saveScore(HighScore,6);


        }
        else{
            saveScore(HighScore,1);


        }
        txtHighScore();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_OPTIONS:
                if(resultCode == Activity.RESULT_OK){

                    String boardSize =null;
                    String bomb_total = null;
                    if(!data.hasExtra(BOARDSIZE)){

                        bomb_total = data.getStringExtra(NUMBER_OF_BOMBS);
                        setValues(boardSize,bomb_total);

                    }
                    if (!data.hasExtra(NUMBER_OF_BOMBS)){
                        boardSize = data.getStringExtra(BOARDSIZE);
                        setValues(boardSize,bomb_total);
                    }
                    if(data.hasExtra(BOARDSIZE) && data.hasExtra(NUMBER_OF_BOMBS)){
                        bomb_total = data.getStringExtra(NUMBER_OF_BOMBS);
                        boardSize = data.getStringExtra(BOARDSIZE);
                        setValues(boardSize,bomb_total);

                    }

//                    Toast.makeText(MainActivity.this , "size: " + SIZE_OF_BOARD_ROW , Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());

                }
                if(resultCode == Activity.BIND_ABOVE_CLIENT){
                    clearPref();
                }
                break;
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_CANCELED){
                    setnumberOfGames();
                    String scans = data.getStringExtra(HIGH_SCORE);
                    int theHighScore = Integer.parseInt(scans);
                    int oldScore = getTheScore(this , SIZE_OF_BOARD_ROW);

                    if (oldScore!= 0){

                        if(theHighScore <    oldScore ){

                          whichBoard(theHighScore);
                        }
                    }else{
                        whichBoard(theHighScore);
                    }
                    btnPlay();
                    Button play = (Button)findViewById(R.id.btnPlay);
                    play.performClick();
                    Toast.makeText(MainActivity.this, "Thank you for playing" , Toast.LENGTH_SHORT).show();

                }
                if(resultCode==Activity.CONTEXT_INCLUDE_CODE){

                    String scans = data.getStringExtra(HIGH_SCORE1);
                    int theHighScore = Integer.parseInt(scans);
                    if (theHighScore != 454545){
                        setnumberOfGames();

                        int oldScore = getTheScore(this ,SIZE_OF_BOARD_ROW );
                        if (oldScore!= 0){

                            if(theHighScore < oldScore ){
                                whichBoard(theHighScore);
                            }
                        }else{
                            whichBoard(theHighScore);
                        }
                    }
                    Toast.makeText(MainActivity.this, "Thank you for playing" , Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }
}
