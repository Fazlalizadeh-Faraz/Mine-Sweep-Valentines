package com.example.faraz098.mines;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * this class is for the pop up that will show up after the end game
 *
 */

public class msgFraqment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_up,null);


        TextView msg  = (TextView)view.findViewById(R.id.txtError2);
        final String scans = getArguments().getString("TotalScans");
        msg.setText("you won my heart with " + scans + " Scans!!\n" +
                "would you like to play again?");




        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i)
                {
                    case DialogInterface.BUTTON_POSITIVE:




                        Intent newGmae = new Intent();
                        getActivity().setResult(Activity.RESULT_CANCELED, newGmae);
                        newGmae.putExtra("highScore" , scans);
                        getActivity().finish();

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:



                        Intent finsihGmae = new Intent();
                        getActivity().setResult(Activity.CONTEXT_INCLUDE_CODE, finsihGmae);
                        finsihGmae.putExtra("highScore" , scans);

                        getActivity().finish();

                        break;
                }
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setView(view)
                .setNegativeButton("NO",listener)
                .setPositiveButton("Yes",listener)
                .create();


    }

}
