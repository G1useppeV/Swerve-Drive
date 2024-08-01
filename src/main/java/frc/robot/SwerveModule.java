package frc.robot;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.CANcoderSimState;

  import edu.wpi.first.math.geometry.Rotation2d;
  import edu.wpi.first.math.kinematics.SwerveModuleState;
  import edu.wpi.first.wpilibj.motorcontrol.Talon;
  import edu.wpi.first.wpilibj.simulation.EncoderSim;
  import edu.wpi.first.wpilibj2.command.SubsystemBase;

      public class SwerveModule extends SubsystemBase{
        public int moduleNumber;
        private Rotation2d angleOffset;
    
        private TalonFX mAngleMotor;
        private TalonFX mDriveMotor;
        private CANcoder angleEncoder;
        public SwerveModuleState currentState;

  public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants){
    this.moduleNumber = moduleNumber;
    this.angleOffset = moduleConstants.angleOffset;

    mAngleMotor = new TalonFX(moduleConstants.angleMotorID);
    mDriveMotor = new TalonFX(moduleConstants.driveMotorID);
    angleEncoder = new CANcoder(moduleConstants.canCoderID);
    currentState = new SwerveModuleState();



  }


  public void resetToAbsolute(){
    double absolutePosition = getCANcoder().getRotations() - angleOffset.getRotations();
    mAngleMotor.setPosition(absolutePosition);
}
public Rotation2d getCANcoder(){
        return Rotation2d.fromRotations(angleEncoder.getAbsolutePosition().getValue());
    } 


public SwerveModuleState getState(){
  return new SwerveModuleState(mDriveMotor.getVelocity().getValue(), Rotation2d.fromRotations(mAngleMotor.getPosition().getValue()));
}


public void setState(SwerveModuleState newState){
  currentState = newState;
}

}

