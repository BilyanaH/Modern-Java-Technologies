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
        UniversitySubject[] result = new UniversitySubject[subjectLength];
        for (var i =0; i<subjectLength; i++){
            var subject = sortedSubjects[i];
            int categoryIndex = -1;
            for(int j=0; j < semesterPlan.subjectRequirements().length; j++){
                if(semesterPlan.subjectRequirements()[j].category()==subject.category()){
                    categoryIndex = j;
                    break;
                }
            }
            if(categoryIndex != -1 && requiredCounts[categoryIndex]>0){
                result[count++] = subject;
                totalCredits += subject.credits();
                requiredCounts[categoryIndex] -= 1;
                sortedSubjects[i]=null;
            }
        }
        for (int i = 0; i < requiredCounts.length; i++) {
            if (requiredCounts[i] > 0) {
                throw new CryToStudentsDepartmentException("Can't meet minimum category requirements for" + semesterPlan.subjectRequirements()[i].category());
            }
        }
        for (var subject : sortedSubjects){
            if(subject!=null){
                result[count++] = subject;
                totalCredits += subject.credits();
            }
            if (totalCredits >= semesterPlan.minimalAmountOfCredits()) {
                break;
            }
        }

        if (totalCredits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Can't meet minimum credit requirements");
        }
        UniversitySubject[] finalResult = new UniversitySubject[count];
        for(int i =0; i<count; i++){
            finalResult[i]=result[i];
        }
        return finalResult;
    }
}
