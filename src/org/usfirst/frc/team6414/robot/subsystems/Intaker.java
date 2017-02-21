package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Intake;

import static org.usfirst.frc.team6414.robot.subsystems.Intaker.State.STOP;

/**
 *
 */
public class Intaker extends MonitoredSystem {

    private CANTalon intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    private State state = STOP;
    private boolean privFwdButState = false, privBwdButState = false;

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

    public Intaker() {
        super();
        System.out.println("Intake sub system init");
        threadInit(() -> SmartDashboard.putNumber("Intake speed:", intakeMotor.get()));
    }

    public void intake() {
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
        if (Robot.oi.getButSt(RobotMap.INTAKE_FWD) != privFwdButState) {
            privFwdButState = !privFwdButState;
            if (privFwdButState) {
                state = state.fwdPressed();
            }
        }
        if (Robot.oi.getButSt(RobotMap.INTAKE_BWD) != privBwdButState) {
            privBwdButState = !privBwdButState;
            if (privBwdButState) {
                state = state.bwdPressed();
            }
        }
    }

    public void stop() {
        intakeMotor.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Intake());
    }
}

