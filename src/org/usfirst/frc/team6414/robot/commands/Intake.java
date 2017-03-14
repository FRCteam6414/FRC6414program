package org.usfirst.frc.team6414.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6414.robot.Robot;

/**
 *
 */
public class Intake extends Command {

    /**
     * Standard Constructor.
     */
    public Intake() {
        requires(Robot.intaker);
    }

    /**
     * o(*￣▽￣*)ブ
     */
    @Override
    protected void initialize() {
        System.out.println("Intake command init");
    }

    /**
     *
     */
    @Override
    protected void execute() {
        Robot.intaker.intake();
    }

    /**
     * @return is this comma
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     *
     */
    @Override
    protected void end() {
        Robot.intaker.stop();
    }

    /**
     *
     */
    @Override
    protected void interrupted() {
    }
}
