package org.insta.databaseconnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.databaseconnection.exception.DatabaseConnectionFailedException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * Utility class for establishing a database connection.
 * </p>
 *
 * <p>
 * This class provides a static method {@code get()} to obtain a database connection
 * using the properties specified in the {@code db.properties} file.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Connection
 */
public final class DatabaseConnection {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);
    private static Connection connection;

    /**
     * <p>
     * Obtains a database connection.
     * </p>
     *
     * @return A {@code Connection} object representing the database connection.
     */
    public static Connection get() {
        if (Objects.isNull(connection)) {
            final Properties properties = new Properties();

            try (final FileReader fileReader = new FileReader("C:/designpp/Instagram/database/src/main/resources/db.properties")) {
                properties.load(fileReader);

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));

                return connection;
            } catch (ClassNotFoundException | SQLException | IOException exception) {
                if (LOGGER != null) LOGGER.error("Database Connection failed");

                throw new DatabaseConnectionFailedException("Database connection failed");
            }
        }
        return connection;
    }
}
