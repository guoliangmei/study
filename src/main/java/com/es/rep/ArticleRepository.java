package com.es.rep;

import com.es.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("articleRepository")
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
    List<Article> findByTitle(String title);


}
