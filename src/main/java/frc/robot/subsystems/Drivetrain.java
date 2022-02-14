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
    private final CANSparkMax motorFrontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
    private final CANSparkMax motorRearLeft = new CANSparkMax(Constants.DRIVE_REAR_LEFT, MotorType.kBrushless);
    private final CANSparkMax motorFrontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
    private final CANSparkMax motorRearRight = new CANSparkMax(Constants.DRIVE_REAR_RIGHT, MotorType.kBrushless);

    // MotorControllerGroup
    private final MotorControllerGroup motorsLeft = new MotorControllerGroup(motorFrontLeft, motorRearLeft);
    private final MotorControllerGroup motorsRight = new MotorControllerGroup(motorFrontRight, motorRearRight);

    // DifferentialDrive
    private final DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);

    // Drivetrain
    public Drivetrain() {
        // Coast while idle
        motorFrontLeft.setIdleMode(IdleMode.kCoast);
        motorFrontRight.setIdleMode(IdleMode.kCoast);
        motorRearLeft.setIdleMode(IdleMode.kCoast);
        motorRearRight.setIdleMode(IdleMode.kCoast);
        // Invert right-side Motors
        motorFrontRight.setInverted(true);
        motorRearRight.setInverted(true);
    }

    // Tank Drive
    public void tankDrive(double left, double right) {
        drive.tankDrive(
                Math.pow(left,Constants.JOYSTICK_CURVE), 
                Math.pow(right,Constants.JOYSTICK_CURVE)
            );
    }

    public void stop() {
        drive.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}