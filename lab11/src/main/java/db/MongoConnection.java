package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoConnection {
    private static MongoDatabase database;

    public static MongoDatabase getConnection() {
        if (database == null) {
            try {
                Dotenv dotenv = Dotenv.load();
                String uri = dotenv.get("MONGODB_URI");
                String dbName = dotenv.get("MONGODB_DATABASE");

                ConnectionString connectionString = new ConnectionString(uri);
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                        .build();


                MongoClient client = MongoClients.create(settings);
                database = client.getDatabase(dbName);

                System.out.println("Підключено до MongoDB: " + dbName);
            } catch (Exception e) {
                System.err.println("Помилка підключення: " + e.getMessage());
            }
        }
        return database;
    }
}

