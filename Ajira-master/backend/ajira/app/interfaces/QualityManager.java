package interfaces;

import java.util.*;

import models.Evaluation;
import models.QualityMetrics;

public interface QualityManager {
	public boolean SubmitEvaluation(Evaluation evaluation);

	public QualityMetrics CalculateMicroEmployerQualityMetrics(String microEmpID);

	public QualityMetrics CalculateMicroWorkerQualityMetrics(String microWorkerID);
}
