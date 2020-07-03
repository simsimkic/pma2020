package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.BoringLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.LikePostRequest;
import com.example.myapplication.dto.response.CommentResponseDto;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedActivityAdapter extends BaseAdapter {
    Context context;
    ArrayList<PostResponse> posts;


    public FeedActivityAdapter(Context context, ArrayList<PostResponse> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feed_activity_item, null);
        }else{
            view = convertView;
        }

        PostResponse p = posts.get(position);
        TextView user =(TextView)view.findViewById(R.id.user);
        TextView description = (TextView)view.findViewById(R.id.description);
        TextView duration = (TextView)view.findViewById(R.id.duration);
        TextView distance = (TextView)view.findViewById(R.id.distance);
        TextView time = (TextView)view.findViewById(R.id.time);
        TextView likes = (TextView)view.findViewById(R.id.likes);
        TextView comment = (TextView)view.findViewById(R.id.comments);
        ImageView icon = (ImageView)view.findViewById(R.id.icon_user);
        icon.setImageResource(R.drawable.baseline_person_black_24dp);
        ImageView map = (ImageView)view.findViewById(R.id.map_image);


        ImageView like = (ImageView)view.findViewById(R.id.like);
        if (p.isLike()){
            like.setImageResource(R.drawable.favorite);
        }else {
            like.setImageResource(R.drawable.ic_like);
        }
        map.setImageBitmap(decodeBase64(p.getBitmap()));
        user.setText(p.getUser());
        description.setText(p.getDescription());
        duration.setText(Double.toString(p.getDuration()) + " min");
        distance.setText(Double.toString(p.getDistance()) + " km");
        time.setText(p.getDate());
        likes.setText(Integer.toString(p.getLike_num()));
        comment.setText(Integer.toString(p.getComment_num()));

        ListView commentList = view.findViewById(R.id.comment_list);
        CommentAdapter adapter = new CommentAdapter(context, p.getComments());
        commentList.setAdapter(adapter);



        clickLike(p, view);
        addComment(p, view);
        return view;
    }

    private void addComment(PostResponse p, View view) {

        ImageView add_comment = view.findViewById(R.id.btn_add_comment);
        add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //uzmemo komentar

                EditText comment = view.findViewById(R.id.comment);
                String username = SaveSharedPreference.getLoggedObject(context).getUsername();
                CommentResponseDto commentResponseDto = new CommentResponseDto(username, "", comment.getText().toString(), p.getId());
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                comment.getText().clear();
                Call<ArrayList<PostResponse>> call = apiService.commentPost(commentResponseDto);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){
                            ArrayList<PostResponse> r = (ArrayList<PostResponse>) response.body();


                            //i promenimo da bude crno srce ili narandzasto
                            posts=r;
                            notifyDataSetChanged();

                        }



                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag", t.getMessage());
                        Log.e("tag","Greska prilikom komentarisanja posta !!!");

                    }





                });

            }
        });
    }

    private void clickLike(PostResponse p, View view) {
        //klik na like dugme
        ImageView like = view.findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pozivamo metodu za like sa servera
                String username = SaveSharedPreference.getLoggedObject(context).getUsername();
                LikePostRequest likePostRequest = new LikePostRequest(p.getId(), username);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ArrayList<PostResponse>> call = apiService.likePost(likePostRequest);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){
                            ArrayList<PostResponse> r = (ArrayList<PostResponse>) response.body();

                            Log.e("tag", "Like post with id " + p.getId());

                            //i promenimo da bude crno srce ili narandzasto
                            posts=r;
                            notifyDataSetChanged();

                        }



                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag", t.getMessage());
                        Log.e("tag","Greska prilikom like-ovanja posta !!!");

                    }





                });




            }
        });


    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
