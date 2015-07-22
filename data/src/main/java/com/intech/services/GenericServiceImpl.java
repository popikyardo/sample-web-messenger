package com.intech.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
public class GenericServiceImpl<E, K extends Serializable> implements GenericService<E, K> {

    private JpaRepository<E, K> genericDao;

    public GenericServiceImpl(JpaRepository<E,K> genericDao) {
        this.genericDao=genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E save(E entity) {
        return genericDao.save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> findAll() {
        return genericDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> findAll(Iterable<K> ids) {
        return genericDao.findAll(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E findOne(K id) {
        return genericDao.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(E entity) {
        genericDao.delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeById(K id) {
        genericDao.delete(id);
    }
}