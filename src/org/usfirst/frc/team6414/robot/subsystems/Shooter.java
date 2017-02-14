package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Shoot;

/**
 *
 */
public class Shooter extends Subsystem {

    private CANTalon leftShooter = new CANTalon(RobotMap.LEFT_SHOOTER);
    private CANTalon rightShooter = new CANTalon(RobotMap.RIGHT_SHOOTER);

    public void refreshSpeed(double speed){
        speed = Robot.limit(0,1,speed);
        leftShooter.set(speed);
        rightShooter.set(-speed);
    }

    public void shootAtDefault(){
//        refreshSpeed(RobotMap.SHOOTER_DEFAULT);
        leftShooter.set(RobotMap.SHOOTER_DEFAULT);
        rightShooter.set(RobotMap.SHOOTER_DEFAULT);
    }

    public void stop(){
        leftShooter.set(0);
        rightShooter.set(0);
    }

    public void initDefaultCommand() {
        new Shoot();
    }

}

