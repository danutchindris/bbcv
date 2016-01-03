package ro.leje.service;

import ro.leje.model.vo.Link;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 3, 2016
 */
public interface LinkService {

    List<Link> findAll();
}
