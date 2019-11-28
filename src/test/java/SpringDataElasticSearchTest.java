import com.es.entity.Article;
import com.es.rep.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:ApplicationContext.xml"})
public class SpringDataElasticSearchTest {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex() throws Exception {
        //创建索引，并配置映射关系
        elasticsearchTemplate.createIndex(Article.class);
        // 配置映射关系
        // elasticsearchTemplate.putMapping(Article.class);
    }
    @Test
    public void addDocument() throws Exception {
        for (int i = 11; i <= 20; i++) {
            //创建一个Article对象
            Article article = new Article();
            article.setId(i);
            article.setTitle("中俄青少年" + i);
            article.setContent("高度评价东博会和商务与投资峰会" + i);
            article.setSearchNo(i+10);
            article.setCnt(ThreadLocalRandom.current().nextLong(5,10));
            article.setMark("备注" + i);
            articleRepository.save(article);
        }
    }




}
