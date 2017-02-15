package org.usfirst.frc.team6414.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Move;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	private CANTalon leftMaster;
	private CANTalon leftSlave;
	private CANTalon rightMaster;
	private CANTalon rightSlave;

	public Chassis(){
		leftMaster = new CANTalon(RobotMap.LEFT_MASTER);
		leftSlave = new CANTalon(RobotMap.LEFT_SLAVE);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.set(leftMaster.getDeviceID());

		rightMaster = new CANTalon(RobotMap.RIGHT_MASTER);
		rightSlave = new CANTalon(RobotMap.RIGHT_SLAVE);
        rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.set(rightMaster.getDeviceID());
	}

	
	private void move(double x, double y){
		leftMaster.set(Robot.limit(-1,1,y+x));
		rightMaster.set(Robot.limit(-1,1,y-x));
        SmartDashboard.putNumber("left speed:",Robot.chassis.getVoltage()[0]);
        SmartDashboard.putNumber("right speed:",Robot.chassis.getVoltage()[1]);
	}

	public double[] getVoltage(){
	    return new double[]{leftMaster.getOutputVoltage(),rightMaster.getOutputVoltage()};
    }
	
	public void moveByJoystick(){
		move(Robot.oi.getX(),Robot.oi.getY());
	}

	public void stop(){
		leftMaster.set(0);
		rightMaster.set(0);
	}

	public void initDefaultCommand() {
		new Move();
	}
}
