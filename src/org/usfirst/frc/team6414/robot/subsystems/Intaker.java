package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Intake;

import static org.usfirst.frc.team6414.robot.subsystems.Intaker.State.STOP;

/**
 *
 */
public class Intaker extends Subsystem {

    private CANTalon intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    private State state = STOP;

    enum State {
        FORWARD,
        BACKWARD,
        STOP;

        public State fwdPressed() {
            if (this == FORWARD) {
                return STOP;
            } else {
                return FORWARD;
            }
        }

        public State bwdPressed() {
            if (this == BACKWARD) {
                return STOP;
            } else {
                return BACKWARD;
            }
        }
    }

    public Intaker(){
        super();
        System.out.println("Intake sub system init");
    }

    public void intake(){
//        if(state){
//            intakeMotor.set(RobotMap.INTAKE_DEF);
//        }else {
//            intakeMotor.set(-RobotMap.INTAKE_DEF);
//        }
        switch (state) {
            case BACKWARD:
                intakeMotor.set(-RobotMap.INTAKE_DEF);
                break;

            case FORWARD:
                intakeMotor.set(RobotMap.INTAKE_DEF);
                break;

            case STOP:
            default:
                intakeMotor.set(0);
                break;
        }
        if(Robot.oi.getButSt(RobotMap.INTAKE_BUT)){
            state = state.fwdPressed();
        } else if (Robot.oi.getButSt(RobotMap.REVERSE_INTAKE)) {
            state = state.bwdPressed();
        }
        while (Robot.oi.getButSt(RobotMap.INTAKE_BUT) || Robot.oi.getButSt(RobotMap.REVERSE_INTAKE)) ;
    }
    public double getVoltage(){
        return intakeMotor.get();
    }

    public void stop(){
        intakeMotor.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Intake());
    }
}

