package com.akb.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.akb.dao.entity.ApplicationEvent;

public interface ApplicationEventRepository extends PagingAndSortingRepository<ApplicationEvent,String>{

	List<ApplicationEvent> findAllByAppId(String id);
}
