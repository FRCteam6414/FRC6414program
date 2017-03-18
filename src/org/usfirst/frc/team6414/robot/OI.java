package org.usfirst.frc.team6414.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.util.HashMap;

//import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick stick = new Joystick(RobotMap.STICK);

    public boolean getButtonState(int port) {
        return ButtonManager.get(stick, port);
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

class ButtonManager {
    private static HashMap<Joystick, HashMap<Integer, JoystickButton>> buttons = new HashMap<>();

    private static JoystickButton getButton(Joystick stick, int port) {
        buttons.putIfAbsent(new Joystick(stick.getPort()), new HashMap<>());
        buttons.get(stick).putIfAbsent(port, new JoystickButton(stick, port));
        return buttons.get(stick).get(port);
    }

    static boolean get(Joystick stick, int port) {
        return getButton(stick, port).get();
    }
}