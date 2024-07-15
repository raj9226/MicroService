package com.raj.question_service.Repository;

import com.raj.question_service.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);


    @Query(value = "SELECT q.id FROM questions q WHERE q.category=:category order by RAND() LIMIT :numQ",nativeQuery = true)
    List<Integer> findByCategoryRandomLimitNumQ(String category, int numQ);
}
