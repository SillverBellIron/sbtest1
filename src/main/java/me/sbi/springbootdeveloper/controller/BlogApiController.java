package me.sbi.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sbi.springbootdeveloper.domain.Article;
import me.sbi.springbootdeveloper.dto.AddArticleRequest;
import me.sbi.springbootdeveloper.dto.ArticleResponse;
import me.sbi.springbootdeveloper.dto.UpdateArticleRequest;
import me.sbi.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    //Http 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //@RequestBody 로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){

        Article savedArticle=blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticle(){

        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }
    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){

        Article article=blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        System.out.println("값 확인 : "+id);
        blogService.delete(id);


        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id
    , @RequestBody UpdateArticleRequest request) {

        Article updateArticle = blogService.update(id,request);

        return ResponseEntity.ok().body(updateArticle);

    }
}
