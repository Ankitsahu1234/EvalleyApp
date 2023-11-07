package android.example.evalleyapp;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class SlotsActivity extends AppCompatActivity {

    String App_id="evalleyapp-gxkjc";
    RecyclerView recview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        Toolbar myToolbar =findViewById(R.id.my_toolbars);
        setActionBar(myToolbar);
         Realm.init(this);
        App app=new App(new AppConfiguration.Builder(App_id).build());
        User user=app.currentUser();
        MongoClient mongoClient =
                user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase =
                mongoClient.getDatabase("eValleyDB");
        CodecRegistry pojoCodecRegistry = fromRegistries(AppConfiguration.DEFAULT_BSON_CODEC_REGISTRY,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoCollection<Slots> collection=mongoDatabase.getCollection("slots",Slots.class)
                .withCodecRegistry(pojoCodecRegistry);

        recview=findViewById(R.id.slotrecview);
        //recview.setLayoutManager(new LinearLayoutManager(this));
        //recview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,5);
        recview.setLayoutManager(gridLayoutManager);
        Document document=new Document("whichArea",new ObjectId(getIntent().getStringExtra("ObjectId")));
       RealmResultTask<MongoCursor<Slots>> result=collection.find(document).iterator();
       result.getAsync(task ->{
           if(task.isSuccess()) {
               MongoCursor<Slots> cursor=task.get();
               List<Slots> slots=new ArrayList<>();
               while (cursor.hasNext())
               {
                   slots.add(cursor.next());
               }
               SlotsAdapter adapter=new SlotsAdapter(slots);
               recview.setAdapter(adapter);
           }
           else
           {
               Log.v("kuch bhi","failed to get success");
           }
       });
    }
}