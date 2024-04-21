package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	public boolean existsByEmail(String email);
	public Users getByEmail(String email);
	public boolean existsByEmailAndPassword(String email, String password);

}
