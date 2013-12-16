package org.tui.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.tui.model.PizzaSize;

import javax.annotation.Resource;
import java.util.List;

/**
 * Data access implementation class for PizzaSize interface {@link PizzaSizeDao}.
 *
 * @author: Srikanth NT
 */
public class PizzaSizeDaoImpl implements PizzaSizeDao {

    @Resource
    SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<PizzaSize> getPizzaSizes() {
        return sessionFactory.getCurrentSession().createCriteria(PizzaSize.class).list();
    }
}
