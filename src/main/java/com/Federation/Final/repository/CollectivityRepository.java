package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.CollectivityStructure;
import com.Federation.Final.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CollectivityRepository {
    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Collectivity> createCollectivities(List<Collectivity> collectivities){

        String insertCollectivitySql = "INSERT INTO collectivity (id,location, structure_id, member_id) " +
                "VALUES(?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCollectivitySql)) {
            for(Collectivity c: collectivities){
                for (Member m : c.getMembers()){
                    ps.setString(1, c.getId());
                    ps.setString(2, c.getLocation());
                    ps.setInt(3, c.getCollectivityStructure().getId());
                    ps.setString(4,m.getId());

                    ps.executeUpdate();
                }

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return collectivities;
    }

}
