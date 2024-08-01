// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.beans.Encoder;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;

//import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.SwerveModule;


public class Swerve extends SubsystemBase {
  public SwerveModule[] mSwerveMods;
  public SwerveModuleState[] states;
/* 
SwerveModule frontLeftModule = new SwerveModule(0,1, 10,11);
SwerveModule frontRightModule = new SwerveModule(2,3,12,13);
SwerveModule backLeftModule = new SwerveModule(4,5,14,15);
SwerveModule backRightModule = new SwerveModule(6,7,16,17);
*/



  /** Creates a new SwerveDrive. */
  public Swerve() {    
    
    mSwerveMods = new SwerveModule[] {
    new SwerveModule(0, Constants.Swerve.Mod0.constants),
    new SwerveModule(1, Constants.Swerve.Mod1.constants),
    new SwerveModule(2, Constants.Swerve.Mod2.constants),
    new SwerveModule(3, Constants.Swerve.Mod3.constants)
};

resetModulesToAbsolute();
  }


  public void resetModulesToAbsolute(){
    for(SwerveModule mod : mSwerveMods){
        mod.resetToAbsolute();
    }} 
  public void setChassisSpeed(ChassisSpeeds desired){
SwerveModuleState[] newStates = Constants.Swerve.swerveKinematics.toSwerveModuleStates(desired); // converts the desired speed into array of module states

for (SwerveModule mod:mSwerveMods){
  mod.setState(newStates[mod.moduleNumber]);
}
  }


public SwerveModuleState[] getModuleStates(){
  states = new SwerveModuleState[4];
  for(SwerveModule mod : mSwerveMods){
      states[mod.moduleNumber] = mod.getState();
  }
  return states;
}



  @Override
  public void periodic() {
    // obtains the parameters for the robot to swerve drive 
    // ChassisSpeeds class requires three paramters: vx, vy, omega, 
    ChassisSpeeds newDesiredSpeed = new ChassisSpeeds(
  -RobotContainer.red1.getLeftY(),
  -RobotContainer.red1.getLeftX(),
  -RobotContainer.red1.getRightX() // rotate
    );
   

setChassisSpeed(newDesiredSpeed);

// simulates robot in advantage scope
 
    double loggingState[] = {
      


      mSwerveMods[0].currentState.angle.getDegrees(),
      mSwerveMods[0].currentState.speedMetersPerSecond,
      mSwerveMods[1].currentState.angle.getDegrees(),
      mSwerveMods[1].currentState.speedMetersPerSecond,
      mSwerveMods[2].currentState.angle.getDegrees(),
      mSwerveMods[2].currentState.speedMetersPerSecond,
      mSwerveMods[3].currentState.angle.getDegrees(),
      mSwerveMods[3].currentState.speedMetersPerSecond
    };
    

  SmartDashboard.putNumberArray("SwerveStates", loggingState);

  }
}
