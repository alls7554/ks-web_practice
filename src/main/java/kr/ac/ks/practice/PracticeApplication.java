package kr.ac.ks.practice;

import kr.ac.ks.practice.domain.Student;
import kr.ac.ks.practice.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public PracticeApplication(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.save(new Student("홍길동", "홍길동@길동.com", "010-1234-5678"));
		studentRepository.save(new Student("유관순", "유관순@관순.com", "010-9876-5432"));
	}
}
