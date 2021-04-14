package tech.itpark.proggerhub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tech.itpark.proggerhub.exception.DataAccessException;
import tech.itpark.proggerhub.repository.model.UserAuthModel;
import tech.itpark.proggerhub.repository.model.UserModel;
import tech.itpark.proggerhub.repository.model.UserTokenModel;
import tech.itpark.proggerhub.repository.model.UserWithIdModel;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final DataSource ds;

    public long save(UserModel model) {
        try (final var conn = ds.getConnection();
             final var stmt =
                     conn.prepareStatement("INSERT INTO users(login, password) VALUES(?, ?) RETURNING id;")) {
            var index = 0;
            stmt.setString(++index, model.getLogin());
            stmt.setString(++index, model.getHash());
            try (final var rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new DataAccessException("no returning id");
                }
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public Optional<UserWithIdModel> findByLogin(String login) {
        try (final var conn = ds.getConnection();
             final var stmt = conn.createStatement();
             final var rs =
                     stmt.executeQuery("SELECT id, login, password FROM users WHERE login = '" + login + "'")) {
            return rs.next() ? Optional.of(
                    new UserWithIdModel(rs.getLong("id"), rs.getString("login"), rs.getString("password"))) :
                    Optional.empty();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void save(UserTokenModel model) {
        try (final var conn = ds.getConnection();
             final var stmt =
                     conn.prepareStatement("INSERT INTO tokens(user_id, token) VALUES(?, ?);")) {
            var index = 0;
            stmt.setLong(++index, model.getId());
            stmt.setString(++index, model.getToken());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public Optional<UserAuthModel> findByToken(String token) {
        try (final var conn = ds.getConnection();
             final var stmt =
                     conn.prepareStatement("SELECT u.id, u.roles FROM users u JOIN tokens t on u.id = t.user_id WHERE t.token = ?;")) {
            int index = 0;
            stmt.setString(++index, token);
            try (final var rs = stmt.executeQuery()) {
                return !rs.next() ? Optional.empty() :
                        Optional.of(new UserAuthModel(rs.getLong("id"), Set.of((String[]) rs.getArray("roles").getArray())));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

}
