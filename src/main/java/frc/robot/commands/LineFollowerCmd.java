package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;
import frc.robot.Constants.LineFollowerConstants;;

public class LineFollowerCmd extends Command {
  private final Drivetrain m_drivetrain;

  /**
   * Creates a new ArcadeDrive. This command will drive your robot according to the speed supplier
   * lambdas. This command does not terminate.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   * @param xaxisSpeedSupplier Lambda supplier of forward/backward speed
   * @param zaxisRotateSupplier Lambda supplier of rotational speed
   */
  public LineFollowerCmd(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((m_drivetrain.getLeftReflectance() >= LineFollowerConstants.kDarkness) 
    && (m_drivetrain.getRightReflectance() >= LineFollowerConstants.kDarkness)) {
        m_drivetrain.arcadeDrive(0.5, 0);
    } else if ((m_drivetrain.getLeftReflectance() <= LineFollowerConstants.kDarkness) 
    && (m_drivetrain.getRightReflectance() >= LineFollowerConstants.kDarkness)) {
        m_drivetrain.arcadeDrive(0.5, -0.2);
    } else if ((m_drivetrain.getLeftReflectance() >= LineFollowerConstants.kDarkness) 
    && (m_drivetrain.getRightReflectance() <= LineFollowerConstants.kDarkness)) {
        m_drivetrain.arcadeDrive(0.5, 0.2);
    } else if ((m_drivetrain.getLeftReflectance() <= LineFollowerConstants.kDarkness) 
    && (m_drivetrain.getRightReflectance() <= LineFollowerConstants.kDarkness)) {
        m_drivetrain.arcadeDrive(0, 0);
    }
    SmartDashboard.putNumber("Left",m_drivetrain.getLeftReflectance());
    SmartDashboard.putNumber("Right", m_drivetrain.getRightReflectance());
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
