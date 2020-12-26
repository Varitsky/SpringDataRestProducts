package ru.geekbrains.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.university.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //создание метода который вернет optional, метод работает по ключевым словам в названии метода, по полю Name
    Optional<Student> findByName(String name);

    //тоже самое с другими @RequestParam
    List<Student> findAllByScoreBetweenAndIdGreaterThan(int min, int max, int minId);


    @Query("select s from Student s where s.id = ?1 and s.name = ?2")
    Optional<Student> customFinderById(Long id, String name);

    // создание метода с урока
    List<Student> findAllByScoreIsGreaterThanEqual(int score);
}
