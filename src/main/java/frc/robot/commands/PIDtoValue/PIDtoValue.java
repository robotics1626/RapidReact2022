package frc.robot.commands.PIDtoValue;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.*;

import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.Limelight;

public class PIDtoValue extends CommandBase{
    // public double changeToMotorValueLeft(Limelight limelight, DoubleSupplier PID) {
    //     Limelight m_limelight = limelight;
    //     DoubleSupplier m_PID = PID;
    //     double motorLeft = 0;
    //     m_PID
    //     return motorLeft;
    // }
    private double initialGyro;
    private double currentGyro = 0;

    public PIDtoValue(double initialGyro){
        this.initialGyro = initialGyro;
    }


    @Override
    public void execute() {
        currentGyro = Gyroscope.getAngle();
        while (currentGyro < initialGyro +180) {
            m_drivetrain.tankDrive(0.5, -0.5);
            currentGyro = Gyroscope.getAngle();
            SmartDashboard.putNumber("Gyroscope Angle For Spinner", currentGyro);
        }
    }
}
