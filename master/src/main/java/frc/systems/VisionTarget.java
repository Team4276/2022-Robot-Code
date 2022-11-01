package frc.systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionTarget {

    public Boolean isValidVisionTarget = false;
    public double targetOffsetAngle_Horizontal = 0.0;     // -27 to +27 degrees
    public double targetOffsetAngle_Vertical = 0.0;      // -24.85 to +24.85 degrees

    public NetworkTable limelightNetworkTable; 
    public NetworkTableEntry tv;
    public NetworkTableEntry tx;
    public NetworkTableEntry ty;

    private final int FILTER_COUNT = 10;  // Count this many good samples before public indication of validity
    private int nWaitForValid = FILTER_COUNT;  
    private Boolean isCurrentSampleValid = false;
  
    public void init() {
        limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
        tv = limelightNetworkTable.getEntry("tv");
        tx = limelightNetworkTable.getEntry("tx");
        ty = limelightNetworkTable.getEntry("ty");
    }

    public void update() {
        targetOffsetAngle_Horizontal = tx.getDouble(0.0);
        targetOffsetAngle_Vertical = ty.getDouble(0.0);

        isCurrentSampleValid = (0 != tv.getDouble(0.0));
        if(!isCurrentSampleValid) {
            isValidVisionTarget = false;
            if(nWaitForValid < FILTER_COUNT) {
                nWaitForValid++;
            }  
        } else {
            if(nWaitForValid > 0) {
                nWaitForValid--;
            } else {
                isValidVisionTarget = true;            
            }
        }
    }

};

