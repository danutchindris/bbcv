package ro.leje.rest;

import ro.leje.model.vo.Link;

import java.util.List;

/**
 * @author Danut Chindris
 * @since December 6, 2015
 */
public interface LinkServiceConsumer {

    List<Link> findAll();
}
