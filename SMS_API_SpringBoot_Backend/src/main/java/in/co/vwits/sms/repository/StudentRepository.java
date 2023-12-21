package in.co.vwits.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.co.vwits.sms.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

	
	@Query("SELECT DISTINCT s From Student As s LEFT JOIN FETCH s.subjectsLearning" )
	List<Student>findAllWithSubjects();
    @Query("From Student As s LEFT JOIN FETCH s.subjectsLearning WHERE  s.rollno = :rno")
	Student findAllWithSubjectsByRoll(@Param(value ="rno")int roll);

	
	
}
