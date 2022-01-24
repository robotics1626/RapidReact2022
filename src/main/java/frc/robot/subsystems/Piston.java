// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Piston extends SubsystemBase {
  // Create a DoubleSolenoid object to control the solenoids on the piston
    
/*  private final Compressor compressor = new Compressor(
    Constants.COMPRESSOR, PneumaticsModuleType.REVPH);*/

  private final DoubleSolenoid solenoid = new DoubleSolenoid(
    PneumaticsModuleType.REVPH, 
    Constants.TEMP_SOLENOID[0], 
    Constants.TEMP_SOLENOID[1]);
  
  public Piston() {}

  /* public void Compressor() {
    compressor.enableDigital();
  } */

  public void extend() {
    solenoid.set(Value.kReverse);
  }

  public void retract() {
    solenoid.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
