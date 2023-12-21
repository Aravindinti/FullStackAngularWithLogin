  package in.co.vwits.sms.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient; 
@Entity
@Table(name="tbl_student")
@NamedQuery(name="findAllWithSubjects",query="FROM Student As s LEFT JOIN FETCH s.subjectsLearning")
public class Student implements Comparable<Student> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int rollno;
	@Column(name="st_name",nullable=false)
	private String name;
	private Double percentage;
	private int numberofAttempts;
//	@ElementCollection(fetch=FetchType.EAGER)//Not recommeneded though possible
	
	@ElementCollection(fetch=FetchType.LAZY)//recommeded approach it fetch data when data needed usih jpql 
	@CollectionTable(name="Students_Subjects",joinColumns = @JoinColumn(name="rollno_fk"))
	private List<String>subjectsLearning;
	@Transient
	private LocalDate dateOfBirth;
	
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public int getNumberofAttempts() {
		return numberofAttempts;
	}



	public void setNumberofAttempts(int numberofAttempts) {
		this.numberofAttempts = numberofAttempts;
	}



	public int getRollno() {
		return rollno;
	}



	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
  


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}



	public List<String> getSubjectsLearning() {
		return subjectsLearning;
	}



	public void setSubjectsLearning(List<String> subjectsLearning) {
		this.subjectsLearning = subjectsLearning;
	}



	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", percentage=" + percentage + ", numberofAttempts="
				+ numberofAttempts + ", subjectsLearning="  + subjectsLearning+", dateOfBirth=" + dateOfBirth + "]";
	}



	public int compareTo(Student o)
	{
		return (int)( o.percentage-this.percentage);
	}

}
