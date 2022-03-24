package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  boolean red = true;
  double xSetpoint = 0.0;
  double Kp = 1.0;
  double Ki = 0.0;
  double Kd = 1.0;
  double p = 0.0;
  double PID = 0.0;
  double previousI = 0.0;
  double previousError = 1.0;
  double previousPID = 0.0;
  double i = 0.0;
  double d = 0.0;  
  long startTime = System.nanoTime();
  long endTime = System.nanoTime();
  private NetworkTable table;
  private String tableName;
  /** Creates a new Limelight. */
  public Limelight() {
    
    tableName = "limelight";
    table = NetworkTableInstance.getDefault().getTable(tableName);
  }

  public double blueBallY(){
    setPipeline(1);
    NetworkTableEntry tx = table.getEntry("tx");
    double blueBallX = tx.getDouble(0.0);
    return blueBallX;
  }

  public double blueBallX(){
    setPipeline(1);
    NetworkTableEntry ty = table.getEntry("ty");
    double blueBallY = ty.getDouble(0.0);
    return blueBallY;
  }

  public double redBallY(){
    setPipeline(2);
    NetworkTableEntry tx = table.getEntry("tx");
    double blueBallX = tx.getDouble(0.0);
    return blueBallX;
  }

  public double redBallX(){
    setPipeline(2);
    NetworkTableEntry ty = table.getEntry("ty");
    double blueBallY = ty.getDouble(0.0);
    return blueBallY;
  }

  public void setPipeline(Integer pipeline) {
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    pipelineEntry.setNumber(pipeline);
  }

  public double getPIDBlue() {
    // P = Kp * error
    if(blueBallX()==0){
      previousPID = PID;
    }
    else{
      p = Kp * blueBallX();
      i = previousI + Ki * blueBallX();
      
      double duration = -((double)endTime - (double)startTime)/100000000;
      if(previousError == 0){
        previousError = 1;
      }
      d = Kd *(blueBallX()/previousError)/duration;

      PID = p+i+d;
      previousPID = PID;
      previousError = blueBallX();
      previousI = i;
      SmartDashboard.putNumber("duration:",duration);
    }

    SmartDashboard.putNumber("P:",p);
    SmartDashboard.putNumber("I:",i);
    SmartDashboard.putNumber("D:",d);
    SmartDashboard.putNumber("PID:",PID);

    SmartDashboard.putNumber("Previuous Error:",previousError);
    SmartDashboard.putNumber("endTime:",endTime);
    SmartDashboard.putNumber("startTime:",startTime); 
    return PID;
  }

  @Override
  public void periodic() {
    startTime = System.nanoTime();
    if(red){
      SmartDashboard.putNumber("Blue Ball X:",blueBallX());
      SmartDashboard.putNumber("Blue Ball Y:",blueBallY());
      getPIDBlue();
      red=false;
    }
    else{
      SmartDashboard.putNumber("Red Ball X:",redBallX());
      SmartDashboard.putNumber("Red Ball Y:",redBallY());
      red=true;
    }
    endTime = System.nanoTime();
  }
}

