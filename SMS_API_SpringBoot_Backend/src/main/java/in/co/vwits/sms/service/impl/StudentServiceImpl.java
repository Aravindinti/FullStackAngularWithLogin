package in.co.vwits.sms.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.vwits.modlel.Exception.StudentNotFoundException;
import in.co.vwits.sms.model.Student;
import in.co.vwits.sms.repository.StudentRepository;
import in.co.vwits.sms.service.StudentService;
@Service
@Transactional//this antation instruct spring framework to invoke all methods
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repo;
	public StudentServiceImpl(StudentRepository dao)
	{
		//this is to stop tight coupling
		//dao=new  StudentJDBCDaoImpl();
		//dao=new StudentJpaDaoImpl();
		this.repo=dao;
		//System.out.println("in constructor");
	}
	public StudentServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/*

	public StudentDao getDao() {
		return repo;
	}


	public void setDao(StudentDao dao) {
		this.repo = dao;
		System.out.println("In set dao");
	}
	*/



	@Override
	public List<Student>findAllStudentsScoredMoreThan(double percentage)
	{
		return repo.findAll().stream() 
		    .filter(student->student.getPercentage()>percentage)
		      .collect(Collectors.toList());
		      
		
	}
	@Override
	public List<Student>findAllStudentsScoredMoreThan80Andlessthan3Attempts(double percentage,int attempts)
	{
		return repo.findAll().stream() 
		    .filter(student->student.getPercentage()>percentage)
		       .filter(student->student.getNumberofAttempts() <attempts )
		           .collect(Collectors.toList());
		}
	@Override
	public long findTotalCountOFStudentsStartsWithNameStarting(char c)
	{
		return repo.findAll().stream() 
			    .filter(student->student.getName().charAt(0)==c)
			      .count();		      
	}
	@Override
	public List<Student> findAllStudentsStartsWithNameStarting(char c)
	{
		return repo.findAll().stream() 
			    .filter(student->student.getName().charAt(0)==c)
			    .collect(Collectors.toList());	      
	}
	 
	@Override
	public List<Student> findAll(){
		return repo.findAll();
	
	}
	//Create  am method which insert a record 
	@Override
	public void save(Student s)
	{
		repo.save(s);
		System.out.println("Inserted");
		
	}
	/*
	@Override
	public Optional<Student> findByRoll(int value) throws StudentNotFoundException
	{
	
	 return Optional.of(repo.findById(value));
	/*	// Optional<Student>p=repo.findByRoll(value);
		// if(p.isPresent())
		         return Optional.of(repo.findByRoll(value)) ;
		 else {
			 //throw user Defined exception StudentNotfound
			 throw new StudentNotFoundException();
		 }
		
	//	return null;
	 * */
	 
	//}
	
	@Override
	public void DeletbyRoll(int roll)
	{	
		repo.deleteById(roll);		
	}
	
	@Override
	public void UpdatePerByRoll(int roll,double per)
	{
		//repo.UpdatePerByRoll(roll,per);
	}	


	@Override
	public List<Student> sortBasedonScoreByDesc(double percentage)
	{
		return repo.findAll().stream() 
				          .sorted()  
		                   .collect(Collectors.toList()) ;
		                 // .sorted();
	}
	@Override
	public List<String> findAllStudentsNamesScoredMoreThanGivenPercentage(double percentage)
	{
		return repo.findAll().stream() 
		    .filter(student->student.getPercentage()>percentage)
		      // .map(student->student.getName())
		           .map(Student::getName)
		               .collect(Collectors.toList());
	}
	@Override
	public List<Student> findAllStudentsLearningSpecificSubject(String subject)
	{
		 return repo.findAll().stream()
				 .filter(student->student.getSubjectsLearning().stream().anyMatch(sub->sub.equals(subject)))
				// .filter(student->student.getSubjectsLearning().contains(subject))  
				    .collect(Collectors.toList());
				 
	}
	@Override
	public List<Student> findAllStudentsBornBeforeSpecificDate(LocalDate SpecificDate)
	{
		return repo.findAll().stream()
				.filter(student -> student.getDateOfBirth().isBefore(SpecificDate))
				.collect(Collectors.toList());
				
	}
	@Override
	public List<Student> findAllStudentsBornAfterSpecificDate(LocalDate SpecificDate)
	{
		return repo.findAll().stream()
				.filter(student -> student.getDateOfBirth().isAfter(SpecificDate))
				.collect(Collectors.toList());		
	}
	@Override
	public List<Student> findAllStudentsBornBeforeAndAfterSpecificDate(LocalDate BeforeDate,LocalDate AfterDate)
	{
		return repo.findAll().stream()
				.filter(student -> student.getDateOfBirth().isBefore(BeforeDate))
				.filter(student -> student.getDateOfBirth().isAfter(AfterDate))
				.collect(Collectors.toList());
				
	}
	@Override
	public int findcountAllStudentsBornAfterSpecificDate(LocalDate SpecificDate)
	{
		return  (int)repo.findAll().stream()
				.filter(student -> student.getDateOfBirth().isAfter(SpecificDate))
				.count();
	
	}
	@Override
	public Map<Boolean, List<Student>> partionStudentsBasedonMarks(double percentage)
	{
		return repo.findAll().stream()
				.collect(Collectors.partitioningBy(s->s.getPercentage()>percentage));
}

@Override
public Map<Boolean, List<Student>> partionStudentsBasedonMarksLess(double percentage)
{
	return repo.findAll().stream()
			.collect(Collectors.partitioningBy(s->s.getPercentage()<percentage));
}

@Override
public long UniqueSubjects()
{
	return repo.findAll().stream().flatMap(student->student.getSubjectsLearning().stream()).distinct().count();
			
}

@Override
public void updateByRollno(Student ss) {
	// TODO Auto-generated method stub
	this.repo.save(ss);
	
}

@Override
public List<Student> findAllWithSubjects() {
	// TODO Auto-generated method stub
	return this.repo.findAllWithSubjects();

}

@Override
public Student findAllWithSubjectsByRoll(int roll) {
	// TODO Auto-generated method stub
	return this.repo.findAllWithSubjectsByRoll(roll);
}
@Override
public Optional<Student> findByRoll(int value) throws StudentNotFoundException {
	// TODO Auto-generated method stub
	return repo.findById(value);
}

}
