package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public abstract sealed class AbstractSemesterPlanner implements  SemesterPlannerAPI permits SoftwareEngineeringSemesterPlanner, ComputerScienceSemesterPlanner{
    protected void QuickSort(UniversitySubject[] subjects, int leftIndex, int rightIndex, boolean byRating){
        if(leftIndex >= rightIndex){
            return;
        }
        int pivotIndex = Partition(subjects, leftIndex, rightIndex, byRating);
        QuickSort(subjects, leftIndex, pivotIndex-1, byRating);
        QuickSort(subjects, pivotIndex+1, rightIndex, byRating);
    }
    protected int Partition(UniversitySubject[] subjects, int leftIndex, int rightIndex, boolean byRating){
        int pivot = byRating ? subjects[rightIndex].rating() : subjects[rightIndex].credits();
        int maxElementIndex = leftIndex-1;
        for (int i = leftIndex; i <= rightIndex; i++){
            int value = byRating ? subjects[i].rating() : subjects[i].credits();
            if(value>pivot){
                maxElementIndex++;
                UniversitySubject swap = subjects[maxElementIndex];
                subjects[maxElementIndex]=subjects[i];
                subjects[i] = swap;
            }
        }
        UniversitySubject swap = subjects[maxElementIndex+1];
        subjects[maxElementIndex+1]=subjects[rightIndex];
        subjects[rightIndex] = swap;
        return maxElementIndex + 1;
    }

    protected void validateSemesterPlan(SemesterPlan semesterPlan) throws InvalidSubjectRequirementsException {
        if(semesterPlan==null){
            throw new IllegalArgumentException("Semester plan can't be null");
        }
        if (semesterPlan.subjects() == null || semesterPlan.subjectRequirements() == null) {
            throw new IllegalArgumentException("Subjects or subject requirements cannot be null");
        }
        int subjectReqLength =semesterPlan.subjectRequirements().length;
        for (int i = 0; i < subjectReqLength; i++){
            for (int j = i+1; j < subjectReqLength; j++){
                if(semesterPlan.subjectRequirements()[i].category()==semesterPlan.subjectRequirements()[j].category()) {
                    throw new InvalidSubjectRequirementsException("Duplicate categories in subject requirements");
                }
            }
        }
    }
    @Override
    public abstract UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan)
            throws InvalidSubjectRequirementsException;

    @Override
    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration){
        if(subjects==null){
            throw new IllegalArgumentException("Subjects array can't be null");
        }
        if(maximumSlackTime<=0){
            throw new IllegalArgumentException("MaximumSlackTime must be positive");
        }
        if(semesterDuration<=0){
            throw new IllegalArgumentException("SemesterDuration must be positive");
        }
        double restNeeded = 0;
        double studyTime = 0;
        for(var subject : subjects){
            restNeeded += Math.ceil(subject.neededStudyTime()*subject.category().getCoeff());
            studyTime+=subject.neededStudyTime();
        }
        int jarCount = (int)Math.ceil(studyTime/5);
        if(studyTime+restNeeded>semesterDuration){
            jarCount*=2;
        }
        double slackTime = semesterDuration - (studyTime + restNeeded);
        if(slackTime > maximumSlackTime){
            throw new DisappointmentException("Too much rest time, grandma is disappointed!");
        }
        return jarCount;
    }

}
