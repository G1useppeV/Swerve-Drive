// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class TeleopDrive extends Command {
 DoubleSupplier getFoward;
 DoubleSupplier getSide;
 DoubleSupplier getRotation;
 

  public TeleopDrive(DoubleSupplier getFoward, DoubleSupplier getSide, DoubleSupplier getRotation) {
this.getFoward = getFoward;
this.getSide = getSide;
this.getRotation = getRotation;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // obtains the parameters for the robot to swerve drive 
    // ChassisSpeeds class requires three paramters: vx, vy, omega, 
    ChassisSpeeds newDesiredSpeed = new ChassisSpeeds(getFoward.getAsDouble(),getSide.getAsDouble(),getRotation.getAsDouble());
    RobotContainer.swerve.setChassisSpeed(newDesiredSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
