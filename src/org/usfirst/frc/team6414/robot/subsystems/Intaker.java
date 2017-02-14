package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Intake;

/**
 *
 */
public class Intaker extends Subsystem {

    private CANTalon intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    private boolean isFWD = true;

    public void intake(){
        if(isFWD){
            intakeMotor.set(RobotMap.INTAKE_DEF);
        }else {
            intakeMotor.set(-RobotMap.INTAKE_DEF);
        }
        if(Robot.oi.getButSt(RobotMap.INTAKE_BUT)){
            isFWD = !isFWD;
            while (Robot.oi.getButSt(RobotMap.INTAKE_BUT));
        }
    }

    public void stop(){
        intakeMotor.set(0);
    }

    public void initDefaultCommand() {
        new Intake();
    }
}

