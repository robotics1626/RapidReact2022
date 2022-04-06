package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  boolean red = true;
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

  @Override
  public void periodic() {
    startTime = System.nanoTime();
    if(red){
      SmartDashboard.putNumber("Blue Ball X:",blueBallX());
      SmartDashboard.putNumber("Blue Ball Y:",blueBallY());
      // red=false;
    }
    else{
      SmartDashboard.putNumber("Red Ball X:",redBallX());
      SmartDashboard.putNumber("Red Ball Y:",redBallY());
      red=true;
    }
    endTime = System.nanoTime();
  }
}

