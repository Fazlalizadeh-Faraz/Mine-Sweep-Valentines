package com.example.faraz098.mines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.faraz098.mines.MainActivity.NUMBER_OF_BOMBS;

/**
 * this class takes care of the options it will
 * see what the user wants then it will send the chosen information
 * to the main activity
 **/



public class Options extends AppCompatActivity {

    public static final String BOARDSIZE = "boardsize";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        createSizeOfBoard();
        createNumberOfHearts();
        btnBack();
        btnSaveChanges();
        btnReset();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createNumberOfHearts() {

        RadioGroup group = (RadioGroup)findViewById(R.id.radioBomb);
        //create buttons
        int [] options = getResources().getIntArray(R.array.num_bombs);
        for (int i = 0; i < options.length ; i++) {
            final int optiosNum = options[i];
            RadioButton  radiobtn = new RadioButton(this);
            radiobtn.setText(getString(R.string.numberofHearts, optiosNum) );
            radiobtn.setTextColor(Color.WHITE);
            radiobtn.setButtonTintList(ColorStateList.valueOf(Color.WHITE));

            group.addView(radiobtn);
        }


    }

    public boolean isSelectedSize(RadioGroup group){
        int checkedOrnot = group.getCheckedRadioButtonId();
        if(checkedOrnot ==-1){
            return false;
        }



        return true;
    }

    public boolean isSelectedHearts(RadioGroup group){
        int checkedOrnot = group.getCheckedRadioButtonId();
        if(checkedOrnot ==-1){
            return false;
        }



        return true;
    }

    private void btnSaveChanges() {
        Button save = (Button) findViewById(R.id.btnOptions_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup group = (RadioGroup)findViewById(R.id.radio_group);
                String chosenBoardSize = null;
                String chosenNumBombs = null;
                Intent intent = new Intent();

                if(isSelectedSize(group)){

                    int idOfSelected  = group.getCheckedRadioButtonId();
                    RadioButton chosenSize = (RadioButton)findViewById(idOfSelected);
                    chosenBoardSize = chosenSize.getText().toString();
                    int indexOfX = chosenBoardSize.indexOf('X');
                    chosenBoardSize = chosenBoardSize.substring(0,indexOfX);
                    chosenBoardSize = chosenBoardSize.replaceAll("[^0-9]" ,"" );
                    intent.putExtra(BOARDSIZE , chosenBoardSize);

//                    Toast.makeText(Options.this , chosenBoardSize , Toast.LENGTH_SHORT).show();
                }

                RadioGroup bombGroup = (RadioGroup)findViewById(R.id.radioBomb);

                if (isSelectedHearts(bombGroup)){

                    int idOfSelectedbomb  = bombGroup.getCheckedRadioButtonId();
                    RadioButton chosenBombs = (RadioButton)findViewById(idOfSelectedbomb);
                    chosenNumBombs = chosenBombs.getText().toString();

                    chosenNumBombs = chosenNumBombs.replaceAll("[^0-9]" ,"" );
                    intent.putExtra(NUMBER_OF_BOMBS , chosenNumBombs);

//                    Toast.makeText(Options.this , chosenNumBombs , Toast.LENGTH_SHORT).show();
                }




                setResult(Activity.RESULT_OK, intent);
                finish();



            }
        });
    }

    public void btnReset(){
        Button reset = (Button)findViewById(R.id.btnreset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(Activity.BIND_ABOVE_CLIENT, intent);
                finish();
            }
        });


    }

    private void btnBack() {

        Button back = (Button) findViewById(R.id.btnoptions_goBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createSizeOfBoard() {
        RadioGroup group = (RadioGroup)findViewById(R.id.radio_group);
        //create buttons
        int [] options = getResources().getIntArray(R.array.num_options);
        for (int i = 0; i < options.length ; i++) {
            int optiosNum = options[i];
            RadioButton  radiobtn = new RadioButton(this);
            radiobtn.setTextColor(Color.WHITE);
            radiobtn.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
            if(optiosNum == 4){
                radiobtn.setText(getString(R.string.boardSize1, optiosNum) );
                group.addView(radiobtn);
            }
            else if(optiosNum == 5){
                radiobtn.setText(getString(R.string.boardSize2, optiosNum) );
                group.addView(radiobtn);
            }
            else if(optiosNum == 6){
                radiobtn.setText(getString(R.string.boardSize3, optiosNum) );
                group.addView(radiobtn);
            }
            else {
                radiobtn.setText(getString(R.string.boardSize4, optiosNum) );
                group.addView(radiobtn);
            }

        }
    }

    public static Intent makeIntentOptions(Context context){
        return new Intent(context, Options.class);
    }
}
