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
      private TalonFX angleMotor;
    private TalonFX driveMotor;
    private SwerveModuleState currentState;
    private  CANcoder driveEncoder;
    private CANcoder angleEncoder; // learn how to do
    CANcoderSimState driveEncoderSim;
    CANcoderSimState angleEncoderSim;

  public SwerveModule(int angleMotorPort,int driveMotorPort, int driveEncoderPort, int angleEncoderPort){
driveMotor = new TalonFX(driveMotorPort); 
angleMotor = new TalonFX(angleMotorPort);
driveEncoder = new CANcoder(driveEncoderPort);
angleEncoder = new CANcoder(angleEncoderPort);
currentState = new SwerveModuleState();

var talonFXSim = m_talonFX.getSimState();
// get encoder sim states

//  angleEncoderSim = angleEncoder.getSimState();

  }


  public void resetToAbsolute(){
    double absolutePosition = getCANcoder().getRotations() - angleOffset.getRotations();
    angleMotor.setPosition(absolutePosition);
}
public Rotation2d getCANcoder(){
        return Rotation2d.fromRotations(angleEncoder.getAbsolutePosition().getValue());
    }

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

