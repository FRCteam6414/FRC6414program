package org.usfirst.frc.team6414.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;


/**
 * Created by willson on 2017/3/8.
 *
 * @author willson
 *         published under GNU Protocol
 */
public class Autonomous extends Command {

    public Autonomous() {
        requires(Robot.chassis);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     *
     */
    protected void initialize() {
        this.setTimeout(15);
    }

    /**
     * @param distant distant form robot to the wall of control station (average)
     * @return the speed it should go at a certain distance. Closer, slower.
     * max=1, min=0, f'(x)=-2sqrt(a)/(2sqrt(-x+a))
     * f(x)=sqrt(-x+a)/sqrt(a) => sqrt(-x/a+1)
     */
    private double speed(double distant) {
        return Math.sqrt(-distant / RobotMap.START_DISTANT + 1);
    }

    private double getDistance() {
        return 0.5 * (Robot.uSensor.getLeftDistant() + Robot.uSensor.getRightDistant());
    }

    private double getRotate() {
        return (Robot.uSensor.getRightDistant() - Robot.uSensor.getLeftDistant())
                / 1.414 * RobotMap.SENSOR_DIST;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    protected void execute() {
        Robot.chassis.move(speed(getDistance()), getRotate());
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    protected boolean isFinished() {
        return false;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    protected void end() {
        Robot.chassis.stop();
    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    protected void interrupted() {
        super.interrupted();
    }
}
