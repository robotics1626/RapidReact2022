// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  
  /** Creates a new Limelight. */
  public Limelight() {

  }

  public double redballX(){
    double redBallX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    return redBallX;
  }

  public double redballY(){
    double redBallY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return redBallY;
  }

  public double blueballX(){
    double blueBallX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    return blueBallX;
  }

  public double blueballY(){
    double blueBallY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return blueBallY;
  }

  @Override
  public void periodic() {

  SmartDashboard.putNumber("Red Ball X:",redballX());
  SmartDashboard.putNumber("Red Ball Y:",redballY());

  SmartDashboard.putNumber("Blue Ball X:",blueballX());
  SmartDashboard.putNumber("Blue Ball Y:",blueballY())

  }
}
