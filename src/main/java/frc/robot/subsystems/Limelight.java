// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  public Limelight() {
  }

  public boolean getValidTargets(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getBoolean(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(2);
    System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));

    // NetworkTable table = NetworkTableInstance.getDefault().getpipe(1);
    // NetworkTableEntry tx = table.getEntry("tx");
    // NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry ta = table.getEntry("ta");
    // NetworkTableEntry pipe = table.getpipe(1);

    // //read values periodically
    // double x = tx.getDouble(0.0);
    // double y = ty.getDouble(0.0);
    // double area = ta.getDouble(0.0);

    // //post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
  }
}
