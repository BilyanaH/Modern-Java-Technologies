package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;


public final class ComputerScienceSemesterPlanner extends AbstractSemesterPlanner{

    @Override
    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws InvalidSubjectRequirementsException {
        validateSemesterPlan(semesterPlan);
        int subjectLength = semesterPlan.subjects().length;
        UniversitySubject[] subjects = semesterPlan.subjects();
        UniversitySubject[] sortedSubjects = new UniversitySubject[subjectLength];
        for (var i = 0 ; i < subjectLength; i++){
            sortedSubjects[i] = subjects[i];
        }
        QuickSort(sortedSubjects, 0, subjects.length - 1, true);
        int totalCredits = 0;
        int count = 0;
        for (var subject : sortedSubjects){
            totalCredits+=subject.credits();
            count++;
            if(totalCredits>=semesterPlan.minimalAmountOfCredits()){
                break;
            }
        }
        if (totalCredits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Can't meet minimum credit requirements");
        }
        UniversitySubject[] result = new UniversitySubject[count];
        for (int i = 0; i < count; i++) {
            result[i] = sortedSubjects[i];
        }
        return result;
    }
}
