// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

import static frc.robot.RobotContainer.*;

public class TargetBlueBall extends CommandBase {
    Limelight vision = new Limelight();
    private int zeroCounter = 0;
    private boolean end = false;
    private boolean seenBall = false;

    public TargetBlueBall(double angle) {
        
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double blueBallY = vision.blueBallY();
        double blueBallX = vision.blueBallX();

        if(blueBallX!=0 && blueBallY!=0){
            seenBall = true;
        }

        if(blueBallX>=10){
            m_drivetrain.tankDrive(0.5, 0.4);
        }
        else if(blueBallX<= -10){
            m_drivetrain.tankDrive(0.4, 0.5);
        }
        else{
            m_drivetrain.tankDrive(0.5, 0.5);
        }

        if( blueBallY< 70 && seenBall){
            if(blueBallY==0){
                zeroCounter++;
                if(zeroCounter>=10){
                    end = true;
                }
                blueBallY = vision.blueBallY();
            }
            
            else if(zeroCounter == 0){
                int x = 0;
                while(x<10){
                    m_drivetrain.tankDrive(0.5, 0.5);
                    x++;
                }
                end = true;
            }
            zeroCounter = 0;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return end;
    }
}
