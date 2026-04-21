package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class CollectivityRepository {
        private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
