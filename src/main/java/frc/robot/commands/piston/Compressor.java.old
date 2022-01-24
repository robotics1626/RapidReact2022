package frc.robot.commands.Piston;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Piston;

public class Compressor extends CommandBase {
    private final Piston m_piston;

    public Compressor(Piston piston) {
        m_piston = piston;
        addRequirements(m_piston);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_piston.Compressor();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
