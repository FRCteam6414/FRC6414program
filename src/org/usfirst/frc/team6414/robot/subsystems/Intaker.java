package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Intake;

import static org.usfirst.frc.team6414.robot.subsystems.Intaker.state.*;

/**
 *
 */
public class Intaker extends Subsystem {

    private CANTalon intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    private state isFWD = BSTOP;
    enum state{
        FWD,
        BWD,
        FSTOP,
        BSTOP
    }

    private state getNext(state input){
        switch (input){
            case BWD:
                return BSTOP;
            case FWD:
                return FSTOP;
            case BSTOP:
                return FWD;
            case FSTOP:
                return BWD;
            default:
                return BWD;
        }
    }
    public void intake(){
//        if(isFWD){
//            intakeMotor.set(RobotMap.INTAKE_DEF);
//        }else {
//            intakeMotor.set(-RobotMap.INTAKE_DEF);
//        }
        switch (isFWD){
            case BWD:
                intakeMotor.set(-RobotMap.INTAKE_DEF);
                break;

            case FWD:
                intakeMotor.set(RobotMap.INTAKE_DEF);
                break;

            case BSTOP:
            case FSTOP:
            default:
                intakeMotor.set(0);
                break;
        }
        if(Robot.oi.getButSt(RobotMap.INTAKE_BUT)){
            isFWD = getNext(isFWD);
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

