package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Article;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Marking the test class as {@link javax.transaction.Transactional},
 * rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since January 25, 2015
 */
@Transactional
public class ArticleServiceTest extends AbstractTest {

    private static final long TEST_ARTICLE_ID = 1L;
    private static final long TEST_NOT_EXISTING_ARTICLE_ID = Long.MAX_VALUE;

    @Resource
    private ArticleService service;

    @Before
    public void setUp() {
        // set up before each test method in the class
    }

    @After
    public void tearDown() {
        // clean up after each test method in the class
    }

    @Test
    public void findReturnsOptionalArticle() {
        Optional<Article> object = service.find(TEST_ARTICLE_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
    }

    @Test
    public void findReturnsEmptyOptionalArticleWhenNotExistingArticleIdProvided() {
        Optional<Article> object = service.find(TEST_NOT_EXISTING_ARTICLE_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertTrue("The optional should be empty", !object.isPresent());
    }
}
