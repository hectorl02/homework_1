package com.training.homework.infrastructure.adapter.output;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.domain.valueObjs.BirdId;
import com.training.homework.birds.application.ports.output.BirdRepository;
import com.training.homework.infrastructure.logSystem.Log;
import com.training.homework.infrastructure.models.BirdDAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@Repository
public class PostgresSqlBirdRepository implements BirdRepository {
    private final DataSource dataSource;

    public PostgresSqlBirdRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Bird bird) {
        String sql = "insert into birds (common_name, scientific_name, zone_name, confirmed_quantity) values (?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bird.getCommonName().getValue());
            preparedStatement.setString(2, bird.getScientificName().getValue());
            preparedStatement.setString(3, bird.getZoneName().getValue());
            preparedStatement.setInt(4, bird.getConfirmedQuantity().getValue());

            preparedStatement.execute();
        } catch (SQLException exception){
            System.out.println("Error DB....: "  + exception.getMessage());
            throw new RuntimeException("Error query ng database", exception);
        }

    }

    @Override
    public Optional<Bird> get(BirdId birdId) {
        String sql = "Select * From birds Where id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, birdId.getValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BirdDAO birdDAO = BirdDAO.fromResultSet(resultSet);
                Bird bird = birdDAO.toDomain();
                return Optional.of(bird);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {

            throw new RuntimeException("Error query database", exception);
        }
    }

    @Override
    public void update(Bird bird) {
        String sql = "Update birds Set common_name = ?, " +
                "scientific_name = ?, " +
                "zone_name = ?, " +
                "confirmed_quantity = ? Where id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bird.getCommonName().getValue());
            preparedStatement.setString(2, bird.getScientificName().getValue());
            preparedStatement.setString(3, bird.getZoneName().getValue());
            preparedStatement.setInt(4, bird.getConfirmedQuantity().getValue());
            preparedStatement.setLong(5, bird.getId().getValue());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            Log log = new Log(exception.getMessage(), "Error queryn database");
            log.logger();
            throw new RuntimeException("Error queryn database", exception);
        }
    }

    @Override
    public Boolean delete(BirdId birdId) {
        String sql = "Delete from birds Where id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, birdId.getValue());

            Boolean result = preparedStatement.execute();

            return result;

        } catch (SQLException exception) {
            Log log = new Log(exception.getMessage(), "Error queryn database");
            throw  new RuntimeException("Error queryng database", exception);
        }
    }
}
