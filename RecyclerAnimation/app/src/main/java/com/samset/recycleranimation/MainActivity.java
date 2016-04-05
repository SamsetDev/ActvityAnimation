package com.samset.recycleranimation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.samset.recycleranimation.activities.SecondActivity;
import com.samset.recycleranimation.adapter.RecyclerAdapter;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String[] data, language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getResources().getStringArray(R.array.country);
        language = getResources().getStringArray(R.array.language);
        recyclerView = (RecyclerView) findViewById(R.id.rv); // layout reference

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true); // to improve performance

        recyclerView.setAdapter(new RecyclerAdapter(data, language)); // the data manager is assigner to the RV
        recyclerView.addOnItemTouchListener( // and the click is handled
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra(SecondActivity.ID, data[position]);

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.CONTACT_city),
                                        getString(R.string.transition_name_city)),
                                new Pair<View, String>(view.findViewById(R.id.country_flag),
                                        getString(R.string.transition_name_flag)),
                                new Pair<View, String>(view.findViewById(R.id.country_name),
                                        getString(R.string.transition_name_name)),
                                new Pair<View, String>(view.findViewById(R.id.country_lang),
                                        getString(R.string.transition_name_lang))

                        );
                        ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());


                    }
                }));
    }

    public static class RecyclerClickListener implements RecyclerView.OnItemTouchListener {

        private OnItemClickListener mListener;
        GestureDetector mGestureDetector;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }

        public RecyclerClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {

        }

    }
}
