package me.sbi.springbootdeveloper.repository;
import me.sbi.springbootdeveloper.domain.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {

}
