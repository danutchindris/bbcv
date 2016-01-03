package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.model.vo.Link;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 3, 2016
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Override
    public List<Link> findAll() {
        return null;
    }
}
