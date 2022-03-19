package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private NetworkTable table;
  private String tableName;
  /** Creates a new Limelight. */
  public Limelight() {
    tableName = "limelight";
    table = NetworkTableInstance.getDefault().getTable(tableName);
  }

  public double ballY(){
    setPipeline(2);
    table.getEntry("pipeline").setNumber(2);
    NetworkTableEntry tx = table.getEntry("tx");
    double blueBallX = tx.getDouble(0.0);
    return blueBallX;
  }

  public double ballX(){
    setPipeline(1);
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
    table.getEntry("ledMode").setNumber(2);
    setPipeline(1);
    SmartDashboard.putNumber("Target Ball X:",ballX());
    SmartDashboard.putNumber("Target Ball Y:",ballY());

    setPipeline(2);
    SmartDashboard.putNumber("Blue Ball X:",ballX());
    SmartDashboard.putNumber("Blue Ball Y:",ballY());
  }
}

