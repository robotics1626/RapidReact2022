// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyroscope extends SubsystemBase {
  private ADXRS450_Gyro m_gyro;
  public Gyroscope() {
    m_gyro = new ADXRS450_Gyro();
    m_gyro.reset();
    m_gyro.calibrate();
    SmartDashboard.putNumber("Gyroscope (Degrees)", 0);
  }

  public double getAngle() {
    return m_gyro.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gyroscope (Degrees)", m_gyro.getAngle());
  }
}
