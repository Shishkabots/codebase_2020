package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.AnalogGyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDturn extends Command {
    public AnalogGyro g = Robot.gyro; // angles are in degrees
    public double t; // target
    double P = 1;
    double I = 1;
    double D = 1;
    double integral, previous_error, error, derivative = 0;
    double rcw;
    double dt = 0.02;
    double completionThreshold = 0.5;
    
    //m_drivetrain is a drivetrain subsystem btw
    public PIDturn(double tt) {
        t = tt;
        requires(Robot.m_drivetrain);
    }
    
    protected void initialize() {
    	Robot.m_drivetrain.move(0, 0);
    }
    
    protected void execute() {
        error = t - g.getAngle(); // Error = Target - Actual
        integral += (error * dt); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - previous_error) / dt;
        Robot.m_drivetrain.arcadeDrive(0, P * error + I * this.integral + D * derivative);
        SmartDashboard.putNumber("Gyro Output Angle: ", g.getAngle());
        SmartDashboard.putNumber("Gyro Integral: ", integral);
        SmartDashboard.putNumber("Gyro Error: ", error);
        SmartDashboard.putNumber("Gyro Derivative: ", derivative);
    }

    protected boolean isFinished() {
        return (Math.abs(error) <= completionThreshold);
    }
    
    protected void end() {
    	Robot.m_drivetrain.move(0, 0);
    }

    protected void interrupted() {
    	//Robot.m_drivetrain.move(0, 0);
    }
}