package frc.robot;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

    public class SwerveModule{
    private Talon AngleMotor;
    private Talon DriveMotor;
    private SwerveModuleState currentState;
    //private CANcoder angleEncoder; // learn how to do

  public SwerveModule(int AngleMotorPort,int DriveMotorPort){
DriveMotor = new Talon(DriveMotorPort);
AngleMotor = new Talon(AngleMotorPort);
currentState = new SwerveModuleState();
  }

  
public SwerveModuleState getState(){
  return currentState;
}

// not too sure what this methods does
public void setState(SwerveModuleState newState){
  currentState = newState;
}

}

