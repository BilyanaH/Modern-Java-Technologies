package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class SoftwareEngineeringSemesterPlanner extends AbstractSemesterPlanner {
    @Override
    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws InvalidSubjectRequirementsException {
        validateSemesterPlan(semesterPlan);
        int subjectLength = semesterPlan.subjects().length;
        UniversitySubject[] subjects = semesterPlan.subjects();
        UniversitySubject[] sortedSubjects = new UniversitySubject[subjectLength];
        for (var i = 0 ; i < subjectLength; i++){
            sortedSubjects[i] = subjects[i];
        }
        QuickSort(sortedSubjects,0, subjectLength-1, false);
        int totalCredits = 0;
        int count = 0;

        int[] requiredCounts = new int[semesterPlan.subjectRequirements().length];
        for (int i = 0; i < semesterPlan.subjectRequirements().length; i++) {
            requiredCounts[i] = semesterPlan.subjectRequirements()[i].minAmountEnrolled();
        }
        boolean areSubjectsEnough = true;
        for (var subject : sortedSubjects){
            areSubjectsEnough = true;
            int categoryIndex = -1;
            for(int i=0; i < semesterPlan.subjectRequirements().length; i++){
                if(semesterPlan.subjectRequirements()[i].category()==subject.category()){
                    categoryIndex = i;
                    requiredCounts[i]--;
                }
                if(requiredCounts[i]!=0){
                    areSubjectsEnough=false;
                }
            }
            if(requiredCounts[categoryIndex]==0){
                continue;
            }
            totalCredits+=subject.credits();
            count++;
            if(totalCredits>=semesterPlan.minimalAmountOfCredits()&&areSubjectsEnough){
                break;
            }

        }
        if (totalCredits < semesterPlan.minimalAmountOfCredits()&&!areSubjectsEnough) {
            throw new CryToStudentsDepartmentException("Can't meet minimum credit requirements");
        }
        UniversitySubject[] result = new UniversitySubject[count];
        for (int i = 0; i < count; i++) {
            result[i] = sortedSubjects[i];
        }
        return result;
    }
}
