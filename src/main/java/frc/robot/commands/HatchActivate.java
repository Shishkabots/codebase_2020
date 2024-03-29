package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchActivate extends Command {
    private int timecounter = 0;
    private final int endtime = 30;
    
    public HatchActivate() {
       timecounter = 0;
    }

            // Called just before this Command runs the first time
            
    @Override
    protected void initialize() {
        if(timecounter == 0) {
            Robot.ds.set(DoubleSolenoid.Value.kReverse);
            Robot.dsoy.set(DoubleSolenoid.Value.kReverse);
        }
        if(Robot.testing){
            SmartDashboard.putString("pogyes?: ", "BEGIN");
        }
    }
        
    // Called repeatedly when this Command is scheduled to run
    
    @Override
    protected void execute() {
        timecounter++;
        if(timecounter > endtime) {
            Robot.ds.set(DoubleSolenoid.Value.kForward);
            Robot.dsoy.set(DoubleSolenoid.Value.kForward);
            if(Robot.testing){
                //SmartDashboard.putString("pogyes?: ", "pog");
            }
        }  
        if(Robot.testing){          
            //SmartDashboard.putNumber("timecounter", timecounter);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if(timecounter > endtime) {
            //SmartDashboard.putString("Hatch Piston: ", "Done");
            return true;
        }else {
            //SmartDashboard.putString("Hatch Piston: ", "Not Done");
            return false;
        }
    
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.ds.set(DoubleSolenoid.Value.kForward);
        Robot.dsoy.set(DoubleSolenoid.Value.kForward);
        timecounter = 0;
        if(Robot.testing){
            //SmartDashboard.putNumber("timecounter", 0);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.m_hatch.setState("Off");
    }
}