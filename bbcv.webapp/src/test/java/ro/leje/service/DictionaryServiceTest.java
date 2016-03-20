package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Dictionary;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Marking the test class as {@link javax.transaction.Transactional},
 * rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since March 19, 2016
 */
@Transactional
public class DictionaryServiceTest extends AbstractTest {

    private static final String ARTICLE_OBJECT_TYPE = "ro.leje.model.entity.ArticleEntity";
    private static final Long ARTICLE_OBJECT_ID = 1L;
    private static final String ARTICLE_TITLE_CATEGORY = "title";

    @Resource
    private DictionaryService service;

    @Before
    public void setUp() {
        // set up before each test method in the class
    }

    @After
    public void tearDown() {
        // clean up after each test method in the class
    }

    @Test
    public void findReturnsExpectedObject() {
        Optional<Dictionary> optionalObject = service.find(ARTICLE_OBJECT_TYPE, ARTICLE_OBJECT_ID, ARTICLE_TITLE_CATEGORY);
        Assert.assertTrue("The object should be present", optionalObject.isPresent());
        Assert.assertEquals("The object type doesn't match", ARTICLE_OBJECT_TYPE,
                optionalObject.map(Dictionary::getObjectType));
        Assert.assertEquals("The object id doesn't match", ARTICLE_OBJECT_ID,
                optionalObject.map(Dictionary::getObjectId));
        Assert.assertEquals("The category doesn't match", ARTICLE_TITLE_CATEGORY,
                optionalObject.map(Dictionary::getCategory));
    }

    @Test
    public void findReturnsEmptyObjectWhenArticleObjectIdIsNotExisting() {
        Optional<Dictionary> optionalObject = service.find(ARTICLE_OBJECT_TYPE, Long.MAX_VALUE, ARTICLE_TITLE_CATEGORY);
        Assert.assertTrue("The object should not be present", !optionalObject.isPresent());
    }

    @Test
    public void create() {
        // TODO
    }

    @Test
    public void update() {
        // TODO
    }
}
