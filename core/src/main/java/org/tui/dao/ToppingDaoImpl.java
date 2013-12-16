package org.tui.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.tui.model.Topping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Data access implementation class for Pizza toppings interface {@link ToppingDao}.
 *
 * @author: Srikanth NT
 */
public class ToppingDaoImpl implements ToppingDao {

    @Resource
    SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Topping> getToppings() {
        return sessionFactory.getCurrentSession().createCriteria(Topping.class).list();
    }
}
