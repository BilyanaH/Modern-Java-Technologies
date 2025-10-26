package bg.sofia.uni.fmi.mjt.burnout.subject;

/**
 * @param name the name of the subject
 * @param credits number of credit hours for this subject
 * @param rating difficulty rating of the subject (1-5)
 * @param category the academic category this subject belongs to
 * @param neededStudyTime estimated study time in days required for this subject
 *
 * @throws IllegalArgumentException if the name of the subject is null or blank
 * @throws IllegalArgumentException if the credits are not positive
 * @throws IllegalArgumentException if the rating is not in its bounds
 * @throws IllegalArgumentException if the Category is null
 * @throws IllegalArgumentException if the neededStudy time is not positive
 */
public record UniversitySubject(String name, int credits, int rating, Category category, int neededStudyTime) {
    public  UniversitySubject{
        if (name == null) {
            throw new IllegalArgumentException("Subject name can't be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Subject name can't be blank");
        }
        if (credits <= 0){
            throw new IllegalArgumentException("Credits of subject can't be negative or 0");
        }
        if (rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating  is not in its bounds");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category of subject can't be null");
        }
        if (neededStudyTime <= 0){
            throw new IllegalArgumentException("Needed time to study the subject can't be negative or 0");
        }
    }
}