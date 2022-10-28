package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTarget {

    Boolean isValidVisionTarget = false;
    double targetOffsetAngle_Horizontal = 0.0;     // -27 to +27 degrees
    double targetOffsetAngle_Vertical = 0.0;      // -24.85 to +24.85 degrees

    NetworkTable limelightNetworkTable; 
    NetworkTableEntry tv;
    NetworkTableEntry tx;
    NetworkTableEntry ty;

    public void init() {
        limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
        tv = limelightNetworkTable.getEntry("tv");
        tx = limelightNetworkTable.getEntry("tx");
        ty = limelightNetworkTable.getEntry("ty");
    }

    public void update() {
        isValidVisionTarget = (0 != tv.getDouble(0.0));
        targetOffsetAngle_Horizontal = tx.getDouble(0.0);
        targetOffsetAngle_Vertical = ty.getDouble(0.0);

        if(isValidVisionTarget) {
            SmartDashboard.putNumber("Limelight_H", targetOffsetAngle_Horizontal);          
            SmartDashboard.putNumber("Limelight_V", targetOffsetAngle_Vertical);          
        } else {
            SmartDashboard.putNumber("Limelight_H", -99999.0);          
            SmartDashboard.putNumber("Limelight_V", -99999.0);          
        }
  
    }

};

