package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Move;

/**
 *
 */
public class Chassis extends MonitoredSystem {
    private CANTalon leftMaster, leftSlave;
    private CANTalon rightMaster, rightSlave;

    public Chassis() {
        leftMaster = new CANTalon(RobotMap.LEFT_MASTER);
        leftSlave = new CANTalon(RobotMap.LEFT_SLAVE);
        leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftSlave.set(leftMaster.getDeviceID());

        rightMaster = new CANTalon(RobotMap.RIGHT_MASTER);
        rightSlave = new CANTalon(RobotMap.RIGHT_SLAVE);
        rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightSlave.set(rightMaster.getDeviceID());

        rightMaster.enableBrakeMode(true);
        rightSlave.enableBrakeMode(true);
        leftMaster.enableBrakeMode(true);
        leftSlave.enableBrakeMode(true);
        System.out.println("Chassis sub system init");

        threadInit(() -> {
            SmartDashboard.putNumber("left speed:", leftMaster.get());
            SmartDashboard.putNumber("right speed:", rightMaster.get());
        });
    }


    public void move(double x, double y) {
        leftMaster.set(Robot.limit(-1, 1, x + y));
        rightMaster.set(Robot.limit(-1, 1, x - y));
    }


    public void moveByJoystick() {
        double x = Robot.oi.getX();
        double y = Robot.oi.getY();
        if (Robot.oi.getButtonState(RobotMap.CHASSIS_ADJUST)) {
            x = 0.2 * x;
            y = 0.2 * y;
        }
        if (Robot.oi.getButtonState(RobotMap.CHASSIS_REV)) {
            y = -y;
        }
        move(x, y);
    }

    public void stop() {
        leftSlave.set(leftMaster.getDeviceID());
        rightSlave.set(rightMaster.getDeviceID());
        leftMaster.set(0);
        rightMaster.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Move());
    }
}
