
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.systems;

import frc.utilities.TCS34725_I2C;
import frc.utilities.TCS34725_I2C.TransferAbortedException;



import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RgbSensorRunnable implements Runnable {
    public enum COLOR {
        FAIL, RED, BLUE, NONE
    };

    public Boolean isSensorOK = true;
    public static COLOR currentColor;
    public static TCS34725_I2C.TCSColor myTcsColor = new TCS34725_I2C.TCSColor(0, 0, 0, 0);
    public static double scaledRed;
    public static double scaledGreen;
    public static double scaledBlue;
    public static double redThreshold = 2.1450;
    public static double blueThreshold = 1.6766;

    private TCS34725_I2C myRGB;
    private final int CHANGE_DELAY = 5; // Get the same error or OK this many times in a row before changing
                                        // 'isSensorOK'
    private int errorStatusChangeDelay = CHANGE_DELAY;

    @Override
    public void run() {

        myRGB = new TCS34725_I2C(I2C.Port.kOnboard, false);
        try {
            myRGB.enable();
        } catch (TransferAbortedException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            myRGB.setIntegrationTime(1);
        } catch (TransferAbortedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            myRGB.setGain(200);
        } catch (TransferAbortedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // will get rgb data and check if sensor is working
        while (true) {

            try {
                myTcsColor = myRGB.getRawData();

                if (isSensorOK) {
                    errorStatusChangeDelay = CHANGE_DELAY;
                } else {
                    if (errorStatusChangeDelay > 0) {
                        errorStatusChangeDelay--;
                    } else {
                        isSensorOK = true;
                    }
                }
            } catch (TransferAbortedException e) {
                if (!isSensorOK) {
                    errorStatusChangeDelay = CHANGE_DELAY;
                } else {
                    if (errorStatusChangeDelay > 0) {
                        errorStatusChangeDelay--;
                    } else {
                        isSensorOK = false;
                    }
                }
            }

            if (!isSensorOK) {
                //if sensor is having problems, then it will set the fail state to true
                //It will then only display fail
                SmartDashboard.putBoolean("FAIL", true);
                SmartDashboard.putBoolean("BLUE", false);
                SmartDashboard.putBoolean("RED", false);
                
            } else {
                //scales values to be appropriate for comparing
                scaledRed = myTcsColor.getR() * 0.65;
                scaledGreen = myTcsColor.getG() * 0.85;
                scaledBlue = myTcsColor.getB();
                //compares red and blue modifiers to see which is more prominent
                //then, sets the current color to that color
                if ((scaledRed / scaledBlue) > redThreshold) {
                    currentColor = COLOR.RED;
                } else if ((scaledBlue / scaledRed) > blueThreshold) {
                    currentColor = COLOR.BLUE;
                } else {
                    currentColor = COLOR.NONE;
                }
                // sets inital values to false, before setting the current color to true
                SmartDashboard.putBoolean("FAIL", false);
                SmartDashboard.putBoolean("BLUE", false);
                SmartDashboard.putBoolean("RED", false);
                SmartDashboard.putBoolean(currentColor.toString(), true);
                
            }

            try {
                //delay on how often it reads the color
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
