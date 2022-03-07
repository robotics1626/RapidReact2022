// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Create new objects to control the SPARK MAX motor controllers. */
  private final CANSparkMax leftFrontMotor = new CANSparkMax(Constants.Drivetrain.Motor.LEFT_FRONT, MotorType.kBrushless);
  private final CANSparkMax leftRearMotor = new CANSparkMax(Constants.Drivetrain.Motor.LEFT_REAR, MotorType.kBrushless);
  private final CANSparkMax rightFrontMotor = new CANSparkMax(Constants.Drivetrain.Motor.RIGHT_FRONT, MotorType.kBrushless);
  private final CANSparkMax rightRearMotor = new CANSparkMax(Constants.Drivetrain.Motor.RIGHT_REAR, MotorType.kBrushless);
  
  /** Create new motor controller groups for the left and right side of the robot. */
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightFrontMotor, rightRearMotor);

  /** Construct a drive train for driving differential drive. */
  private final DifferentialDrive drivetrain = new DifferentialDrive(leftMotors, rightMotors);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    /**
     * Restore motor controller parameters to factory default until the next controller 
     * reboot.
     */
    leftFrontMotor.restoreFactoryDefaults();
    leftRearMotor.restoreFactoryDefaults();
    rightFrontMotor.restoreFactoryDefaults();
    rightRearMotor.restoreFactoryDefaults();

    /**
     * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
     * will effectively disconnect all motor wires. This allows the motor to spin down at 
     * its own rate. 
     */
    leftFrontMotor.setIdleMode(IdleMode.kCoast);
    rightFrontMotor.setIdleMode(IdleMode.kCoast);
    leftRearMotor.setIdleMode(IdleMode.kCoast);
    rightRearMotor.setIdleMode(IdleMode.kCoast);

    /** Invert the direction of the right-side speed controllers. */
    rightMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /** This function is called once each time the the command ends or is interrupted. */
  public void stop() {
    /**
     * Stop motor movement. Motor can be moved again by calling set without having to 
     * re-enable the motor.
     */
    drivetrain.stopMotor();
  }

  /** This function makes use of the driver input to control the robot like a tank. */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    /** Set the drivetrain to operate as tank drive. */
    drivetrain.tankDrive(leftSpeed, rightSpeed, false);
  }
}
