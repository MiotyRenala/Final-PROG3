package com.Federation.Final.service;

import com.Federation.Final.entity.dto.CollectivityLocalStatistics;
import com.Federation.Final.repository.LocalStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class LocalStatisticService {


    private LocalStatisticRepository localStatisticRepository;

    public LocalStatisticService(LocalStatisticRepository localStatisticRepository){
        this.localStatisticRepository = localStatisticRepository;
    }

    public List<CollectivityLocalStatistics> getLocalStatistics(String collectivityId,
                                                                LocalDate from,
                                                                LocalDate to) {
        try {

            return localStatisticRepository.getLocalStatistics(collectivityId, from, to);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching " + e.getMessage());
        }
    }
}