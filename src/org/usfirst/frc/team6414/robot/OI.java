package org.usfirst.frc.team6414.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    private Joystick stick = new Joystick(RobotMap.STICK);
    // Button button = new JoystickButton(stick, buttonNumber);
    private Button butIntake = new JoystickButton(stick, RobotMap.INTAKE_FWD),
            butReverseIntake = new JoystickButton(stick, RobotMap.INTAKE_BWD),
            butMixerFwd = new JoystickButton(stick, RobotMap.MIXER_FWD),
            butMixerBwd = new JoystickButton(stick, RobotMap.MIXER_BWD),
            butSetShooterDef = new JoystickButton(stick, RobotMap.SET_SHOOTER_DEF),
            butChassisAdj = new JoystickButton(stick, RobotMap.CHASSIS_ADJUST),
            butShooterBwd = new JoystickButton(stick, RobotMap.SHOOTER_BWD);

    public boolean getButtonState(int port) {
        switch (port) {
            case RobotMap.INTAKE_FWD:
                return butIntake.get();
            case RobotMap.INTAKE_BWD:
                return butReverseIntake.get();
            case RobotMap.MIXER_FWD:
                return butMixerFwd.get();
            case RobotMap.MIXER_BWD:
                return butMixerBwd.get();
            case RobotMap.CHASSIS_ADJUST:
                return butChassisAdj.get();
            case RobotMap.SET_SHOOTER_DEF:
                return butSetShooterDef.get();
            case RobotMap.SHOOTER_BWD:
                return butShooterBwd.get();
            default:
                return new JoystickButton(stick, port).get();
        }
    }

    public double getX() {
        return stick.getX();
    }

    public double getY() {
        return stick.getY();
    }

    public double getThrottle() {
        return stick.getThrottle();
    }

    public boolean getTrigger() {
        return stick.getTrigger();
    }
}
