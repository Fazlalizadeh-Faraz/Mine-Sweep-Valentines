package com.example.faraz098.mines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import static com.example.faraz098.mines.MainActivity.BOARD_SIZE_COL;
import static com.example.faraz098.mines.MainActivity.BOARD_SIZE_ROW;

/**
 * this class is for playing the game
 * it will get the size of the board sent by the
 * main activity and then it will set up a board filled with all buttons
 * this class also takes care of the logic of the game
 *
 **/
public class Play extends AppCompatActivity {
    public static final String SECRET = "454545";
    public static final String HIGH_SCORE = "highScore";
    private int NUM_ROW_DEFAULT = 5 ;
    private int NUM_COL_DEFAULT = 5;
    private int numberOfBombsRevealed = 0;
    private int totalBombs = 3;
    private int total_scans;


    btnCell bombList[][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getData();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent newGmae = new Intent();
            setResult(Activity.RESULT_OK, newGmae);
            newGmae.putExtra(HIGH_SCORE, SECRET);
            finish();
        }
        return true;
    }


    public void populateTable(int num_row, int num_col){

        TableLayout table = (TableLayout)findViewById(R.id.tbTable);
        for (int row = 0; row < num_row ; row++) {
            TableRow tablerow =     new TableRow(this);

            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f


            ));
            table.addView(tablerow);
            for (int col = 0; col < num_col; col++) {

                final int ROW = row;
                final int COL = col;
                final Button newButton = new Button(this);
                btnCell cell = new btnCell(newButton);
                newButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                newButton.setPadding(0,0,0,0);

                final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
                newButton.setOnClickListener(new View.OnClickListener() {



                    public void onClick(View view) {

                        mp.start();
                        Log.i("FARAZ", "BUTTON CLICKED");
                        checkButton(COL,ROW);

                    }


                });
                tablerow.addView(newButton);
                bombList[row][col] = cell;
            }
        }


    }

    public void SetMine(int total){
        while(total!=0) {
            final Random rand = new Random();
            int ro = rand.nextInt(NUM_ROW_DEFAULT);
            int kol = rand.nextInt(NUM_COL_DEFAULT);
            if(!bombList[ro][kol].isBomb()){

                bombList[ro][kol].setBomb(true);
                total--;
                Log.i("SWitching image", "CHANGING IMAGE");
            }else{ continue;}

        }

    }

    private void checkButton(int col, int row) {


        if(bombList[row][col].isBomb() && !bombList[row][col].isFlippede() ){
            lockButton();
            numberOfBombsRevealed++;

            Animation bubble = AnimationUtils.loadAnimation(this,R.anim.bubble);
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            bubble.setInterpolator(interpolator);

            Button btn = bombList[row][col].getInstance();
            bombList[row][col].setBomb(false);
            bombList[row][col].setFlippede(true);
            bombList[row][col].setShowScan(true);
            int btnWidth = btn.getWidth();
            int btnHeight = btn.getHeight();

            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart_icon);
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, btnWidth ,   btnHeight, true);
            Resources res = getResources();
            btn.setBackground(new BitmapDrawable(res ,scaleBitmap));
            btn.setAnimation(bubble);
            setSurrounding();
            updateBTN();




        }else if (bombList[row][col].isShowScan()) {
            total_scans++;
            Button btn = bombList[row][col].getInstance();
            btn.setTextColor(Color.WHITE);
            btn.setText(""+bombList[row][col].getSurroundingBomb());
            bombList[row][col].setShowScan(false);

        }else if(!bombList[row][col].isFlippede()) {
            total_scans++;

            setSurrounding();
            Button btn = bombList[row][col].getInstance();
            bombList[row][col].setFlippede(true);
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setTextColor(Color.WHITE);
            btn.setText(""+bombList[row][col].getSurroundingBomb());

        }

        TextView revealedBombs =(TextView)findViewById(R.id.txtDiscoveredTanks);
        TextView TotalBomb =(TextView)findViewById(R.id.txtTotalTanks);

        if(!gameEnd()){
            revealedBombs.setText(""+ numberOfBombsRevealed + " out of "+ totalBombs + " revealed");
            TotalBomb.setText("total # of scans: "+ total_scans);

        }else{

            revealedBombs.setText(""+ numberOfBombsRevealed + " out of "+ totalBombs + " revealed");
            TotalBomb.setText("total # of scans: "+ total_scans);

            FragmentManager manager = getSupportFragmentManager();
            msgFraqment dialog = new msgFraqment();
            Bundle bundle = new Bundle();
            bundle.putString("TotalScans" , String.valueOf(total_scans));
            dialog.setArguments(bundle);
            dialog.show(manager,"pop_up");

        }



    }

    private boolean gameEnd() {
        if(numberOfBombsRevealed == totalBombs){
            Toast.makeText(Play.this, "THEY WERE EQUAL" , Toast.LENGTH_SHORT);
            return true;
        }


        return false;
    }

    public void updateBTN(){
        for (int row = 0; row < NUM_ROW_DEFAULT; row++) {
            for (int col = 0; col < NUM_COL_DEFAULT; col++) {

                Button bb = bombList[row][col].getInstance();
                if(bb.getText().length()!=0){
                    bb.setText(""+ bombList[row][col].getSurroundingBomb());
                }
            }
        }
    }

    public void setSurrounding(){
        for (int row = 0; row < NUM_ROW_DEFAULT; row++) {
            for (int col = 0; col < NUM_COL_DEFAULT; col++) {
                bombList[row][col].setSurroundingBomb(getTotalNumberOfHeartsSurrounding(row,col));

            }
        }


    }

    public int getTotalNumberOfHeartsSurrounding(int row, int col){
        int sum = 0;
        for (int currentRow = 0; currentRow < NUM_ROW_DEFAULT ; currentRow++) {
            if(bombList[currentRow][col].isBomb()){
                sum++;
            }else{
                continue;
            }
        }
        for (int j = 0; j < NUM_COL_DEFAULT; j++) {
            if(bombList[row][j].isBomb()){
                sum++;
            }
            else{
                continue;
            }
        }
        return sum;

    }

    private void lockButton() {
        for (int i = 0; i <NUM_ROW_DEFAULT ; i++) {
            for (int j = 0; j < NUM_COL_DEFAULT; j++) {
                Button btn = bombList[i][j].getInstance();
                int width = btn.getWidth();
                btn.setMinWidth(width);
                btn.setMaxWidth(width);

                int height = btn.getHeight();
                btn.setMinHeight(height);
                btn.setMaxHeight(height);
            }
        }
    }

    public void getData(){
        Intent intent  = getIntent();
        int x = intent.getIntExtra("numberOfBombs" ,0);
        int sizeR = intent.getIntExtra(BOARD_SIZE_ROW ,0);
        int sizeC = intent.getIntExtra(BOARD_SIZE_COL ,0);

        Toast.makeText(Play.this, "row "+ sizeR + " col " + sizeC , Toast.LENGTH_SHORT).show();
        totalBombs = x;

        if(sizeR!= 0 && sizeC!= 0 ){


            NUM_COL_DEFAULT = sizeC;
            NUM_ROW_DEFAULT = sizeR;
            bombList = new btnCell[NUM_ROW_DEFAULT][NUM_COL_DEFAULT];
            populateTable(NUM_ROW_DEFAULT,NUM_COL_DEFAULT);
            SetMine(totalBombs);

        }else{
            bombList = new btnCell[NUM_ROW_DEFAULT][NUM_COL_DEFAULT];
            populateTable(NUM_ROW_DEFAULT,NUM_COL_DEFAULT);
            SetMine(totalBombs);

        }

    }

    public static Intent makeIntentPlay(Context context){
        return new Intent(context,Play.class);
    }
}
