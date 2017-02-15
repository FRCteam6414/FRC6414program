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
    private boolean isFwd = true;

    public void mix(){
        if(isFwd){
            mixer.set(RobotMap.MIXER_DEF);
        }else{
            mixer.set(-RobotMap.MIXER_DEF);
        }
        if(Robot.oi.getButSt(RobotMap.MIXER_FWD)){
            isFwd = true;
        }
        if(Robot.oi.getButSt(RobotMap.MIXER_BWD)){
            isFwd = false;
        }
    }

    public double getVoltage(){
        return mixer.getOutputVoltage();
    }

    public void stop(){
        mixer.set(0);
    }

    public void initDefaultCommand() {
        new Mix();
    }
}

