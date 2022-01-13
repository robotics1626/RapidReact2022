package frc.robot.commands.piston;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Piston;

public class Retract extends CommandBase {
    private final Piston m_piston;

    public Retract(Piston piston) {
        m_piston = piston;
        addRequirements(m_piston);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_piston.retract();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
