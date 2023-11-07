package android.example.evalleyapp;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class ReviewActivity extends AppCompatActivity {

    String App_id="evalleyapp-gxkjc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        EditText rating = (EditText) findViewById(R.id.rating);
        EditText review = (EditText) findViewById(R.id.review);
        Button submit=findViewById(R.id.review_button);

        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(App_id).build());
        User user = app.currentUser();
        MongoClient mongoClient =
                user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase =
                mongoClient.getDatabase("eValleyDB");
        CodecRegistry pojoCodecRegistry = fromRegistries(AppConfiguration.DEFAULT_BSON_CODEC_REGISTRY,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoCollection<Review> mongoCollection =
                mongoDatabase.getCollection(
                        "reviews",
                        Review.class).withCodecRegistry(pojoCodecRegistry);
        submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           int main_rating=Integer.parseInt(rating.getText().toString());
           String main_reveiw=review.getText().toString();
           String objectid=getIntent().getStringExtra("ObjectID");
           Review review=new Review(new ObjectId(),new ObjectId(user.getId()),getIntent().getStringExtra("username"),
                   main_rating,main_reveiw,new ObjectId(objectid));
           mongoCollection.insertOne(review).getAsync(task ->{
               if(task.isSuccess())
               {
                   Toast.makeText(ReviewActivity.this,"Your review has been recorded",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(ReviewActivity.this,"You can't submit your review",Toast.LENGTH_SHORT).show();
               }
           });

           }
        });
    }
}