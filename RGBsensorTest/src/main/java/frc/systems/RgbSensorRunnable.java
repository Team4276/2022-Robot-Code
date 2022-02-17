
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

    public Boolean isSensorOK = true;

    private TCS34725_I2C myRGB;
    private final int CHANGE_DELAY = 5; // Get the same error or OK this many times in a row before changing
                                        // 'isSensorOK'
    private int errorStatusChangeDelay = CHANGE_DELAY;

    @Override
    public void run() {

        SmartDashboard.putNumber("RGB-C", -1);
        SmartDashboard.putNumber("RGB-R", -1);
        SmartDashboard.putNumber("RGB-G", -1);
        SmartDashboard.putNumber("RGB-B", -1);

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

        TCS34725_I2C.TCSColor myTcsColor = new TCS34725_I2C.TCSColor(0, 0, 0, 0);

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
                SmartDashboard.putNumber("RGB-C", -1);
                SmartDashboard.putNumber("RGB-R", -1);
                SmartDashboard.putNumber("RGB-G", -1);
                SmartDashboard.putNumber("RGB-B", -1);
            } else {
                SmartDashboard.putNumber("RGB-C", myTcsColor.getC());
                SmartDashboard.putNumber("RGB-R", myTcsColor.getR());
                SmartDashboard.putNumber("RGB-G", myTcsColor.getG());
                SmartDashboard.putNumber("RGB-B", myTcsColor.getB());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
