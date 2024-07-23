// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.SwerveModule;

public class Swerve extends SubsystemBase {
SwerveModule frontLeftModule = new SwerveModule(0,1);
SwerveModule frontRightModule = new SwerveModule(2,3);
SwerveModule backLeftModule = new SwerveModule(4,5);
SwerveModule backRightModule = new SwerveModule(6,7);

// Locations for the swerve drive modules relative to the robot center.
double chassisWidth = 1.75; // should be in meters
double chassisLength =  1.75;
Translation2d frontLeftLocation = new Translation2d(chassisLength / 2, chassisWidth /2);
Translation2d frontRightLocation = new Translation2d(chassisLength / 2, -chassisWidth /2);
Translation2d backLeftLocation = new Translation2d(-chassisLength / 2, chassisWidth /2);
Translation2d backRightLocation = new Translation2d(-chassisLength / 2, -chassisWidth /2);

// Creating my kinematics object using the module locations
// the kinematic object requires the locations of the modules relative to the robot center 
SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
frontLeftLocation,
frontRightLocation,
backLeftLocation,
backRightLocation
);
private final CommandXboxController red1;

  /** Creates a new SwerveDrive. */
  public Swerve(CommandXboxController controller) {
    red1 = controller;
  }

  public void setChassisSpeed(ChassisSpeeds desired){
SwerveModuleState[] newStates = kinematics.toSwerveModuleStates(desired); // converts the desired speed into array of module states
frontLeftModule.setState(newStates[0]); // it sets the state and puts into into the first postion of the array
frontRightModule.setState(newStates[1]);
backLeftModule.setState(newStates[2]);
backRightModule.setState(newStates[3]);

  }

  @Override
  public void periodic() {
    // obtains the parameters for the robot to swerve drive 
    // ChassisSpeeds class requires three paramters: vx, vy, omega, 
    ChassisSpeeds newDesiredSpeed = new ChassisSpeeds(
  -red1.getLeftY(),
  -red1.getLeftX(),
  -red1.getRightX() // rotate
    );
   

setChassisSpeed(newDesiredSpeed);

// simulates robot in advantage scope
    double loggingState[] = {
      frontLeftModule.getState().angle.getDegrees(),
      frontLeftModule.getState().speedMetersPerSecond,
      frontRightModule.getState().angle.getDegrees(),
      frontRightModule.getState().speedMetersPerSecond,
      backLeftModule.getState().angle.getDegrees(),
      backLeftModule.getState().speedMetersPerSecond,
      backRightModule.getState().angle.getDegrees(),
      backRightModule.getState().speedMetersPerSecond,
    
    };
SmartDashboard.putNumberArray("SwerveModuleStates",loggingState);
  }
}
