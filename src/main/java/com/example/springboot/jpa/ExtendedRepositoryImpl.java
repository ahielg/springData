package com.example.springboot.jpa;

import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Ahielg
 * @date 27/12/2020
 */
public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private JpaEntityInformation<T, ?> entityInformation;
    private EntityManager entityManager;

    public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    @SneakyThrows
    public List<T> findByIds(ID... ids) {
        Query query = this.entityManager.createQuery("select e from " + this.entityInformation.getEntityName()
                + " e where e." + this.entityInformation.getIdAttribute().getName() + " in (:ids)");
        query.setParameter("ids", ids);
        long wait = new Random().nextInt(10000 - 1) + 1;
        Thread.sleep(wait);

        System.out.println("executing query for Ids: " + Arrays.toString(ids));
        //noinspection unchecked
        return (List<T>) query.getResultList();
    }

}
