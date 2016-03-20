package ro.leje.service;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Dictionary;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Marking the test class as {@link javax.transaction.Transactional},
 * rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since January 25, 2015
 */
@Transactional
public class ArticleServiceTest extends AbstractTest {

    private static final String EMPTY = "";
    private static final String ARTICLE_OBJECT_TYPE = "ro.leje.model.entity.ArticleEntity";
    private static final Long ARTICLE_OBJECT_ID = 1L;
    private static final String ARTICLE_TITLE_CATEGORY = "title";
    private static final long TEST_NOT_EXISTING_ARTICLE_ID = Long.MAX_VALUE;
    private static final String ARTICLE_EN_TITLE = "New Article";
    private static final String ARTICLE_RO_TITLE = "Articol nou";

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
    public void findAllReturnsNotEmptyList() {
        List<Article> list = service.findAll();
        Assert.assertNotNull("The returned list should not be null", list);
        Assert.assertTrue("The list should contain at least one item", list.size() > 0);
    }

    @Test(expected = NullPointerException.class)
    public void createOrUpdateThrowsExceptionWhenDictionaryObjectIsNull() {
        service.createOrUpdate(null);
    }

    @Test(expected = NullPointerException.class)
    public void createOrUpdateThrowsExceptionWhenObjectTypeIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.createOrUpdate(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createOrUpdateThrowsExceptionWhenObjectTypeIsEmpty() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(EMPTY);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.createOrUpdate(dictionary);
    }

    @Test(expected = NullPointerException.class)
    public void createOrUpdateThrowsExceptionWhenCategoryIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        service.createOrUpdate(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createOrUpdateThrowsExceptionWhenCategoryIsEmpty() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setCategory(EMPTY);
        service.createOrUpdate(dictionary);
    }

    @Test
    public void createOrUpdateReturnsNewArticleIdWhenObjectIdIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        dictionary.setEn(ARTICLE_EN_TITLE);
        dictionary.setRo(ARTICLE_RO_TITLE);
        Long articleId = service.createOrUpdate(dictionary);
        Assert.assertNotNull("The article id should not be null", articleId);
    }

    @Test
    public void createOrUpdateReturnsExistingArticleIdWhenObjectIdIsProvided() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        dictionary.setEn(ARTICLE_EN_TITLE);
        dictionary.setRo(ARTICLE_RO_TITLE);
        Long articleId = service.createOrUpdate(dictionary);
        Assert.assertNotNull("The article id should not be null", articleId);
    }

    @Test(expected = ContextedRuntimeException.class)
    public void createOrUpdateThrowsExceptionWhenNotExistingObjectIdIsProvided() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(Long.MAX_VALUE);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.createOrUpdate(dictionary);
    }

    @Test
    public void findReturnsOptionalArticle() {
        Optional<Article> object = service.find(ARTICLE_OBJECT_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
    }

    @Test
    public void findReturnsEmptyOptionalArticleWhenNotExistingArticleIdProvided() {
        Optional<Article> object = service.find(TEST_NOT_EXISTING_ARTICLE_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertTrue("The optional should be empty", !object.isPresent());
    }
}
