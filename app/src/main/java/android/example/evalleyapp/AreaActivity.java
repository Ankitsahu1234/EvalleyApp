package android.example.evalleyapp;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class AreaActivity extends AppCompatActivity {

    String App_id="evalleyapp-gxkjc";
    RecyclerView recview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        Toolbar myToolbar =findViewById(R.id.my_toolbar);
        setActionBar(myToolbar);
        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(App_id).build());


        User user = app.currentUser();
        MongoClient mongoClient =
                user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase =
                mongoClient.getDatabase("eValleyDB");
        CodecRegistry pojoCodecRegistry = fromRegistries(AppConfiguration.DEFAULT_BSON_CODEC_REGISTRY,
                
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoCollection<ModelArea> mongoCollection =
                mongoDatabase.getCollection(
                        "areas",
                        ModelArea.class).withCodecRegistry(pojoCodecRegistry);
        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        recview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        RealmResultTask<MongoCursor<ModelArea>> cursorRealmResultTask = mongoCollection.find().iterator();
        cursorRealmResultTask.getAsync(task -> {
            if (task.isSuccess()) {
                MongoCursor<ModelArea> cursor = task.get();
                List<ModelArea> result = new ArrayList<>();
                while (cursor.hasNext()) {
                    result.add(cursor.next());

                }

                AreaAdapter adapter=new AreaAdapter(result,getApplicationContext());
                recview.setAdapter(adapter);

            }

        });
    }
}