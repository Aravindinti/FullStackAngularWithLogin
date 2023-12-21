package in.co.vwits.sms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.co.vwits.modlel.Exception.StudentNotFoundException;
import in.co.vwits.sms.model.Student;

public interface StudentService {

	List<Student> findAllStudentsScoredMoreThan(double percentage);

	List<Student> findAllStudentsScoredMoreThan80Andlessthan3Attempts(double percentage, int attempts);

	long findTotalCountOFStudentsStartsWithNameStarting(char c);

	List<Student> findAllStudentsStartsWithNameStarting(char c);

	List<Student> findAll();

	//Create  am method which insert a record 
	void save(Student s);

	Optional<Student> findByRoll(int value) throws StudentNotFoundException;

	void DeletbyRoll(int roll);

	void UpdatePerByRoll(int roll, double per);

	List<Student> sortBasedonScoreByDesc(double percentage);

	List<String> findAllStudentsNamesScoredMoreThanGivenPercentage(double percentage);

	List<Student> findAllStudentsLearningSpecificSubject(String subject);

	List<Student> findAllStudentsBornBeforeSpecificDate(LocalDate SpecificDate);

	List<Student> findAllStudentsBornAfterSpecificDate(LocalDate SpecificDate);

	List<Student> findAllStudentsBornBeforeAndAfterSpecificDate(LocalDate BeforeDate, LocalDate AfterDate);

	int findcountAllStudentsBornAfterSpecificDate(LocalDate SpecificDate);

	Map<Boolean, List<Student>> partionStudentsBasedonMarks(double percentage);

	Map<Boolean, List<Student>> partionStudentsBasedonMarksLess(double percentage);

	long UniqueSubjects();

	void updateByRollno(Student ss);
	 List<Student>findAllWithSubjects(); 
	 Student  findAllWithSubjectsByRoll(int roll);
}