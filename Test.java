package frc.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends IterativeRobot {

    WPI_TalonSRX FrontRight;
    WPI_TalonSRX FrontLeft;
    WPI_TalonSRX BackRight;
    WPI_TalonSRX BackLeft;
    SpeedControllerGroup Right;
    SpeedControllerGroup Left;
    DifferentialDrive DriveTrain;
    XboxController Controller;

    @Override
    public void robotInit() {
        FrontRight = new WPI_TalonSRX(1);
        FrontLeft = new WPI_TalonSRX(0);
        BackRight = new WPI_TalonSRX(3);
        BackLeft = new WPI_TalonSRX(2);

        Right = new SpeedControllerGroup(FrontRight, BackRight);
        Left = new SpeedControllerGroup(FrontLeft, BackLeft);

        DriveTrain = new DifferentialDrive(Left, Right);

        Controller = new XboxController(0);
    }

    @Override
    public void teleopInit() {
        // Nothing
    }

    @Override
    public void teleopPeriodic() {
        
        if(Controller.getAButton()) {
            double y = Controller.getY(GenericHID.Hand.kLeft);
            double x = Controller.getX(GenericHID.Hand.kLeft);
            DriveTrain.arcadeDrive(-y, x);
        } else if(Controller.getBumper(GenericHID.Hand.kLeft)) {
            double y = Controller.getY(GenericHID.Hand.kLeft);
            double x = Controller.getX(GenericHID.Hand.kRight);
            DriveTrain.arcadeDrive(-y, x);
        } else if(Controller.getBumper(GenericHID.Hand.kRight)) {
            double y = Controller.getY(GenericHID.Hand.kRight);
            double x = Controller.getX(GenericHID.Hand.kLeft);
            DriveTrain.arcadeDrive(-y, x);
        } else {
            // Nothing
        }
    }
}
