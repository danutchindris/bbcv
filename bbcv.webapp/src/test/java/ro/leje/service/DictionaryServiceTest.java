package ro.leje.service;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
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

    private static final String EMPTY = "";
    private static final String ARTICLE_OBJECT_TYPE = "ro.leje.model.entity.ArticleEntity";
    private static final Long ARTICLE_OBJECT_ID = 1L;
    private static final String ARTICLE_TITLE_CATEGORY = "title";

    private static final Long NEW_ARTICLE_OBJECT_ID = 2L;
    private static final String NEW_ARTICLE_EN = "New Article 1";
    private static final String NEW_ARTICLE_RO = "Articol nou 1";

    private static final Long DICTIONARY_ID = 1L;
    private static final Long NOT_EXISTING_DICTIONARY_ID = Long.MAX_VALUE;


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
                optionalObject.map(Dictionary::getObjectType).get());
        Assert.assertEquals("The object id doesn't match", ARTICLE_OBJECT_ID,
                optionalObject.map(Dictionary::getObjectId).get());
        Assert.assertEquals("The category doesn't match", ARTICLE_TITLE_CATEGORY,
                optionalObject.map(Dictionary::getCategory).get());
    }

    @Test
    public void findReturnsEmptyObjectWhenArticleObjectIdIsNotExisting() {
        Optional<Dictionary> optionalObject = service.find(ARTICLE_OBJECT_TYPE, Long.MAX_VALUE, ARTICLE_TITLE_CATEGORY);
        Assert.assertTrue("The object should not be present", !optionalObject.isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void createThrowsExceptionWhenDictionaryObjectIsNull() {
        service.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void createThrowsExceptionWhenObjectTypeIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.create(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createThrowsExceptionWhenObjectTypeIsEmpty() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(EMPTY);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.create(dictionary);
    }

    @Test(expected = NullPointerException.class)
    public void createThrowsExceptionWhenObjectIdIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        service.create(dictionary);
    }

    @Test(expected = NullPointerException.class)
    public void createThrowsExceptionWhenCategoryIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        service.create(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createThrowsExceptionWhenCategoryIsEmpty() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(EMPTY);
        service.create(dictionary);
    }

    @Test
    public void createReturnsNewDictionaryId() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(NEW_ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        dictionary.setEn(NEW_ARTICLE_EN);
        dictionary.setRo(NEW_ARTICLE_RO);
        Long dictionaryId = service.create(dictionary);
        Assert.assertNotNull("The dictionary id should not be null", dictionaryId);
    }

    @Test(expected = NullPointerException.class)
    public void updateThrowsExceptionWhenDictionaryObjectIsNull() {
        service.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateThrowsExceptionWhenDictionaryIdIsNull() {
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectType(ARTICLE_OBJECT_TYPE);
        dictionary.setObjectId(ARTICLE_OBJECT_ID);
        dictionary.setCategory(ARTICLE_TITLE_CATEGORY);
        dictionary.setEn(NEW_ARTICLE_EN);
        dictionary.setRo(NEW_ARTICLE_RO);
        service.update(dictionary);
    }

    @Test
    public void updateStoresChanges() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(DICTIONARY_ID);
        dictionary.setEn(NEW_ARTICLE_EN);
        dictionary.setRo(NEW_ARTICLE_RO);
        service.update(dictionary);
        Optional<Dictionary> optionalObject = service.find(ARTICLE_OBJECT_TYPE, ARTICLE_OBJECT_ID, ARTICLE_TITLE_CATEGORY);
        Assert.assertTrue("The object should be present", optionalObject.isPresent());
        Assert.assertEquals("The English translation doesn't match", NEW_ARTICLE_EN,
                optionalObject.map(Dictionary::getEn).get());
        Assert.assertEquals("The Romanian translation doesn't match", NEW_ARTICLE_RO,
                optionalObject.map(Dictionary::getRo).get());
    }

    @Test(expected = ContextedRuntimeException.class)
    public void updateThrowsExceptionWhenNotExistingDictionaryIdIsProvided() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(NOT_EXISTING_DICTIONARY_ID);
        dictionary.setEn(NEW_ARTICLE_EN);
        dictionary.setRo(NEW_ARTICLE_RO);
        service.update(dictionary);
    }
}
