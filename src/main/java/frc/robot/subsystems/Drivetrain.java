// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
    // Motors
    CANSparkMax motorFrontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
    CANSparkMax motorRearLeft = new CANSparkMax(Constants.DRIVE_REAR_LEFT, MotorType.kBrushless);
    CANSparkMax motorFrontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
    CANSparkMax motorRearRight = new CANSparkMax(Constants.DRIVE_REAR_RIGHT, MotorType.kBrushless);

    // MotorControllerGroup
    MotorControllerGroup motorsLeft = new MotorControllerGroup(motorFrontLeft, motorRearLeft);
    MotorControllerGroup motorsRight = new MotorControllerGroup(motorFrontRight, motorRearRight);

    // DifferentialDrive
    DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);

    // Gear Shift
    boolean gear = false;

    // Drivetrain
    public Drivetrain() {
        // Coast
        motorFrontLeft.setIdleMode(IdleMode.kCoast);
        motorFrontRight.setIdleMode(IdleMode.kCoast);
        motorRearLeft.setIdleMode(IdleMode.kCoast);
        motorRearRight.setIdleMode(IdleMode.kCoast);



        motorFrontRight.setInverted(true);
        motorRearRight.setInverted(true);
    }

    public void shiftGears(){
        gear = !gear;
    }

    // Tank Drive
    public void tankDrive(double left, double right) {
        if (gear) {
            drive.tankDrive(left, right);
        } else {
            drive.tankDrive(Math.pow(left,(5/3)), Math.pow(right,(5/3)));
        }
        
    }

    public void stop() {
        drive.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}