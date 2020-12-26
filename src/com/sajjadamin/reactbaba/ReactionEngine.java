package com.sajjadamin.reactbaba;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ReactionEngine {

    private Robot robot;
    private final int index;

    ReactionEngine(int index){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        this.index = index;
    }

    public void startReact(){
        keyPress(KeyEvent.VK_J);
        delay(500);
        keyPress(KeyEvent.VK_L);
        delay(200);
        if (index > 0){
            for (int i = 1; i <= index; i++){
                keyPress(KeyEvent.VK_RIGHT);
                delay(200);
            }
        }
        delay(200);
        keyPress(KeyEvent.VK_ENTER);
        delay(500);
    }

    private void keyPress(int key){
        robot.keyPress(key);
        robot.keyRelease(key);
    }
    public void delay(int millisecond){
        robot.delay(millisecond);
    }
}
