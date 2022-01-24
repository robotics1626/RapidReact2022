package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

    // Drivetrain
    public Drivetrain() {
        // Brake
        motorFrontLeft.setIdleMode(IdleMode.kBrake);
        motorFrontRight.setIdleMode(IdleMode.kBrake);
        motorRearLeft.setIdleMode(IdleMode.kBrake);
        motorRearRight.setIdleMode(IdleMode.kBrake);

        /*
        motorFrontRight.setInverted(true);
        motorRearRight.setInverted(true); */
    }

    // Tank Drive
    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    public void stop() {
        drive.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}