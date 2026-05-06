package com.Federation.Final.service;

import com.Federation.Final.entity.CollectivityActivity;
import com.Federation.Final.entity.dto.CreateCollectivityActivity;
import com.Federation.Final.repository.CollectivityActivityRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CollectivityActivityService {
    private  final CollectivityActivityRepository collectivityActivityRepository;

    public CollectivityActivityService(CollectivityActivityRepository collectivityActivityRepository) {
        this.collectivityActivityRepository = collectivityActivityRepository;
    }

    public List<CollectivityActivity> getActivitiesByCollectivityId(String collectivityId) {
        try {
            if (!collectivityActivityRepository.collectivityExists(collectivityId)) {
                throw new IllegalArgumentException("Collectivity not found with id: " + collectivityId);
            }

            return collectivityActivityRepository.findActivityByCollectivityId(collectivityId);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching activities: " + e.getMessage(), e);
        }
    }

    public List<CollectivityActivity> createActivities(String collectivityId,
                                                       List<CreateCollectivityActivity> dtos) {
        try {
            if (!collectivityActivityRepository.collectivityExists(collectivityId)) {
                throw new IllegalArgumentException("Collectivity not found with id: " + collectivityId);
            }

            List<CollectivityActivity> activities = new ArrayList<>();

            for (CreateCollectivityActivity dto : dtos) {

                boolean hasRecurrence = dto.getRecurrenceRule() != null;
                boolean hasExecutiveDate = dto.getExecutiveDate() != null;

                if (hasRecurrence && hasExecutiveDate) {
                    throw new IllegalArgumentException(
                            "Both recurrence rule and executive date provided for activity: " + dto.getLabel());
                }
                if (!hasRecurrence && !hasExecutiveDate) {
                    throw new IllegalArgumentException(
                            "Either recurrence rule or executive date must be provided for activity: " + dto.getLabel());
                }

                if (dto.getMemberOccupationConcerned() == null ||
                        dto.getMemberOccupationConcerned().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Member occupation concerned must be provided for activity: " + dto.getLabel());
                }

                CollectivityActivity activity = new CollectivityActivity();
                activity.setId(UUID.randomUUID().toString());
                activity.setLabel(dto.getLabel());
                activity.setActivityType(dto.getActivityType());
                activity.setMemberOccupationConcerned(dto.getMemberOccupationConcerned());
                activity.setRecurrenceRule(dto.getRecurrenceRule());
                activity.setExecutiveDate(dto.getExecutiveDate());
                activity.setCollectivityId(collectivityId);

                activities.add(activity);
            }

            return collectivityActivityRepository.saveAll(collectivityId, activities);

        } catch (SQLException e) {
            throw new RuntimeException("Error creating activities: " + e.getMessage(), e);
        }

    }
}
