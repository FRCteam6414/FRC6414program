package org.usfirst.frc.team6414.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;
import org.usfirst.frc.team6414.robot.commands.Mix;



/**
 * Created by willson on 2017/2/14.
 *
 * @author willson
 *         published under GNU Protocol
 */
public class Mixer extends Subsystem {

    private CANTalon mixer = new CANTalon(RobotMap.MIXER_MOTOR);
    private State state = State.STOP;
    enum State{
        FORWARD,
        STOP,
        BACKWARD;
        public State fwdPressed(){
            if (this == FORWARD) {
                return STOP;
            }else{
                return FORWARD;
            }
        }
        public State bwdPressed(){
            if (this == BACKWARD) {
                return STOP;
            }else{
                return BACKWARD;
            }
        }
    }


    public Mixer(){
        super();
        System.out.println("Mix sub system init");
    }

    public void mix(){
//        if(isFwd){
//            mixer.set(RobotMap.MIXER_DEF);
//        }else{
//            mixer.set(-RobotMap.MIXER_DEF);
//        }
        switch (state){
            case FORWARD:
                mixer.set(RobotMap.MIXER_DEF);
                break;
            case BACKWARD:
                mixer.set(-RobotMap.MIXER_DEF);
                break;
            case STOP:
            default:
                mixer.set(0);
        }
        if(Robot.oi.getButSt(RobotMap.MIXER_FWD)){
            state=state.fwdPressed();
        }
        if(Robot.oi.getButSt(RobotMap.MIXER_BWD)){
            state=state.bwdPressed();
        }
    }

    public double getVoltage(){
        return mixer.getOutputVoltage();
    }

    public void stop(){
        mixer.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Mix());
    }
}

