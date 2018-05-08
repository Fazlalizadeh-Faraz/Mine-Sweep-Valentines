package com.example.faraz098.mines;

import android.widget.Button;

/**
 * class for taking care of each cell within the game
 */

public class btnCell {

    private Button currentButton ;
    boolean isBomb = false;
    boolean isFlippede = false;
    boolean showScan = false;


    public static btnCell instance;





    public void setShowScan(boolean showScan) {
        this.showScan = showScan;
    }

    public boolean isShowScan() {

        return showScan;
    }

    int surroundingBomb=0;

    public int getSurroundingBomb() {
        return surroundingBomb;
    }

    public void setSurroundingBomb(int surroundingBomb) {
        this.surroundingBomb = surroundingBomb;
    }

    public void decSurroindingBomb(){
        this.surroundingBomb --;
    }

    public boolean isFlippede() {
        return isFlippede;
    }

    public void setFlippede(boolean flippede) {

        isFlippede = flippede;
    }

    public btnCell(Button currentButton) {
        this.currentButton = currentButton;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public Button getInstance() {



        return currentButton;
    }
}
