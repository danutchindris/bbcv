package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.User;
import ro.leje.util.CategoryConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Marking the test class as {@link javax.transaction.Transactional},
 * rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since January 25, 2015
 */
@Transactional
public class ArticleServiceTest extends AbstractTest {

    private static final long USER_ID = 1L;
    private static final String USER_NAME = "test.user";
    private static final Long ARTICLE_ID = 1L;
    private static final long NOT_EXISTING_ARTICLE_ID = Long.MAX_VALUE;

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
        Optional<Article> object = service.find(ARTICLE_ID, CategoryConstants.EN);
        Assert.assertNotNull("The returned object shouldn't be null", object);
    }

    @Test
    public void findReturnsEmptyOptionalArticleWhenNotExistingArticleIdProvided() {
        Optional<Article> object = service.find(NOT_EXISTING_ARTICLE_ID, CategoryConstants.EN);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertTrue("The optional should be empty", !object.isPresent());
    }

    @Test
    public void findReturnsNotEmptyList() {
        List<Article> list = service.find(CategoryConstants.EN);
        Assert.assertNotNull("The returned list should not be null", list);
        Assert.assertTrue("The list should contain at least one item", list.size() > 0);
    }

    @Test
    public void findAuthorsReturnsNotNull() {
        List<User> authors = service.findAuthors(ARTICLE_ID);
        Assert.assertNotNull("The returned object should not be null", authors);
    }

    @Test
    public void findAuthorsReturnsExpectedListWhenCorrectArticleIdIsProvided() {
        List<User> authors = service.findAuthors(ARTICLE_ID);
        Assert.assertEquals("The returned list should contain one element", 1, authors.size());
        Assert.assertEquals("The assigned author's name should be " + USER_NAME, USER_NAME,
                authors.iterator().next().getUserName());
    }

    @Test
    public void createReturnsNewArticleId() {
        Long articleId = service.create(null);
        Assert.assertNotNull("The articleId should not be null", articleId);
    }

    @Test
    public void createReturnsNewArticleIdAndAssignsAuthors() {
        Set<Long> authorIds = new HashSet<>();
        authorIds.add(USER_ID);
        Long articleId = service.create(authorIds);
        Assert.assertNotNull("The articleId should not be null", articleId);
        List<User> authors = service.findAuthors(articleId);
        Assert.assertNotNull("The authors list should not be null", authors);
        Assert.assertEquals("The authors list should contain one element", 1, authors.size());
        User user = authors.iterator().next();
        Assert.assertEquals("The user id is incorrect", USER_ID, user.getId());
        Assert.assertEquals("The user name is incorrect", USER_NAME, user.getUserName());
    }
}
