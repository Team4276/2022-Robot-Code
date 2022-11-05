package frc.systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTarget {

    public Boolean isValidVisionTarget = false;
    public double targetOffsetAngle_Horizontal = 0.0; // -27 to +27 degrees
    public double targetOffsetAngle_Vertical = 0.0; // -24.85 to +24.85 degrees

    public NetworkTable limelightNetworkTable;
    public NetworkTableEntry tv;
    public NetworkTableEntry tx;
    public NetworkTableEntry ty;

    private final int FILTER_COUNT = 10; // Count this many good samples before public indication of validity
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
        if (!isCurrentSampleValid) {
            isValidVisionTarget = false;
            if (nWaitForValid < FILTER_COUNT) {
                nWaitForValid++;
            }
        } else {
            if (nWaitForValid > 0) {
                nWaitForValid--;
            } else {
                isValidVisionTarget = true;
            }
        }
        SmartDashboard.putNumber("Goal distance inches: ", targetDistanceInches());
    }

    public double targetDistanceInches() {
        // Range of ty vertical angle = -24.85 to +24.85 degrees
        // Camera height = 38"
        // High goal height =

        // how many degrees back is your limelight rotated from perfectly vertical?
        double limelightMountAngleDegrees = 12.0;

        // distance from the center of the Limelight lens to the floor
        double limelightLensHeightInches = 38.0;

        // distance from the target to the floor
        double goalHeightInches = 104.0;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        // calculate distance
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
                / Math.tan(angleToGoalRadians);

        return distanceFromLimelightToGoalInches;
    }

    public void setLed(Boolean isOn) {
        if(isOn) {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        } else {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
        }
    }
};
