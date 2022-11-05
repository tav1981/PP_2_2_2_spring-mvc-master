package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Car;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    //@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCar(byte count) {
        if (count == 0) {
            TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
            return query.getResultList();
        } else {
            String hql2 = "FROM Car " ;
            TypedQuery<Car> query2=sessionFactory.getCurrentSession().createQuery(hql2).setMaxResults((int) count);
            return query2.getResultList();
        }
    }

    @Override
    public void add5Car(){
        add(new Car("car1", 1, 1L));
        add(new Car("car2", 2, 1L));
        add(new Car("car3", 3, 1L));
        add(new Car("car4", 4, 1L));
        add(new Car("car5", 5, 1L));
    }

}
