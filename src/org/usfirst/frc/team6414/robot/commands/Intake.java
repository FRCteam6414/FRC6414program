package org.usfirst.frc.team6414.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6414.robot.Robot;

/**
 *
 */
public class Intake extends Command {

    public Intake() {
        requires(Robot.intaker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Intake command init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intaker.intake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.intaker.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
