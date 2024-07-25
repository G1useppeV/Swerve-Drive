package frc.robot;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.CANcoderSimState;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

    public class SwerveModule extends SubsystemBase{
    private Talon angleMotor;
    private Talon driveMotor;
    private SwerveModuleState currentState;
    private  CANcoder driveEncoder;
    private CANcoder angleEncoder; // learn how to do
    CANcoderSimState driveEncoderSim;
    CANcoderSimState angleEncoderSim;

  public SwerveModule(int angleMotorPort,int driveMotorPort, int driveEncoderPort, int angleEncoderPort){
driveMotor = new Talon(driveMotorPort);
angleMotor = new Talon(angleMotorPort);
//driveEncoder = new CANcoder(driveEncoderPort);
//angleEncoder = new CANcoder(angleEncoderPort);
currentState = new SwerveModuleState();

// get encoder sim states

//angleEncoderSim = angleEncoder.getSimState();

  }
  //public double getAnglePosition() {
    //return 
  //}
  public double getLeftDistanceInch() {
    return driveEncoder.getPosition().getValueAsDouble();
  }
public SwerveModuleState getState(){
  return currentState;
}

// not too sure what this methods does
public void setState(SwerveModuleState newState){
  currentState = newState;
}

@Override
public void periodic() {

}

}

