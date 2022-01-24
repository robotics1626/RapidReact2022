// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Piston extends SubsystemBase {
  // Create a DoubleSolenoid object to control the solenoids on the piston
    
/*  private final Compressor compressor = new Compressor(
    Constants.COMPRESSOR, PneumaticsModuleType.REVPH);*/

  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.SOLENOID);
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(
    PneumaticsModuleType.REVPH, 
    Constants.DOUBLE_SOLENOID[0], 
    Constants.DOUBLE_SOLENOID[1]);
  
  public Piston() {}

  public void enable() {
    m_solenoid.set(true);
  }

  public void extend() {
    m_doubleSolenoid.set(Value.kReverse);
  }

  public void retract() {
    m_doubleSolenoid.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
