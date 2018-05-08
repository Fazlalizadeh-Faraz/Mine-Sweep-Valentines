package com.example.faraz098.mines;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * this class helps the user
 * it is for more clarification and for the user
 * to understand how to play the game
 * also this class holds the information about citation and where each
 * resource came from
 * */



public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        txtAuthors();
        txtDescription();
        txtLinks();
        txtCopy();
    }

    private void txtCopy() {
        TextView copy = (TextView)findViewById(R.id.txtcopyR);
        copy.setText("DO NOT COPY bro! not good\n");
    }

    private void txtLinks() {
        TextView DEscription = (TextView)findViewById(R.id.txtLinks);
        TextView DEscription2 = (TextView)findViewById(R.id.txtLink2);
        TextView DEscription3 = (TextView)findViewById(R.id.txtLink3);
        TextView DEscription4 = (TextView)findViewById(R.id.txtLink4);
        DEscription.setText("Links:\n" +
                "Thank you to all these AMAZING sites for great pictures:\n");
        DEscription2.setText("Back Ground: \n");
        DEscription2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToURL("https://videohive.net/item/valentine-background/19249853");
            }
        });
        DEscription3.setText("Intro Icon: \n");
        DEscription3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToURL("http://icons.mysitemyway.com/legacy-icon/113374-glowing-purple-neon-icon-culture-heart-transparent/");
            }
        });
        DEscription4.setText("Heart Icon: \n" );
        DEscription4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToURL("http://borderlands.wikia.com/wiki/File:Heart_Icon.png");
            }
        });
        txtCopy();
    }

    private void txtDescription() {
        TextView DEscription = (TextView)findViewById(R.id.txtHelpDesc);
        DEscription.setText(
                "Description: \n" +
                        "In the game of love seeker you will have to \n" +
                        "find the number of hearts with the least amount\n" +
                        "of scans, if you click on a cell and it shows you\n" +
                        "a number that number indicates the number of hearts\n" +
                        "hidden in the same row and col as the cell you selected\n" +
                        "once you find all the heart you win my heart.\n" +
                        "in order to reset options and high score click the\n" +
                        "reset button in option screen!\n\n" +
                        "Theme: \n" +
                        "The valentine theme is for all the lonely souls out there\n" +
                        "this game is for all the people who can't get other people's heart\n" +
                        "Hope you enjoy this game!!!!\n" +
                        "Much LOVE...\n\n\n"
        );
    }

    private void txtAuthors() {

        TextView help = (TextView)findViewById(R.id.txtHelp);
        TextView Link = (TextView)findViewById(R.id.txtHyperlink);

        Link.setText("CMPT 276 Simon Fraser University\n\n ");
        Link.setClickable(true);
        Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToURL("https://www.cs.sfu.ca/CourseCentral/276/bfraser/index.html");
            }
        });
        help.setText("Author: \n" + "This game has been written by Faraz Fazlalizadeh with the help of online tutorials." );




    }

    void GoToURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public static Intent makeIntentHelp(Context context){
        return new Intent(context, Help.class);
    }
}
