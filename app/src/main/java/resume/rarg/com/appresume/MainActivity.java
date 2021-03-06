package resume.rarg.com.appresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private ListAdapter mAdapter;
    private LinearLayout placeNameHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isListView = true;
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new ListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    ListAdapter.OnItemClickListener onItemClickListener = new ListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(MainActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_PARAM_ID, position);

            // 1
            ImageView placeImage = (ImageView) v.findViewById(R.id.placeImage);
            LinearLayout placeNameHolder = (LinearLayout) v.findViewById(R.id.placeNameHolder);
// 2
            Pair<View, String> imagePair = Pair.create((View) placeImage, "tImage");
            Pair<View, String> holderPair = Pair.create((View) placeNameHolder, "tNameHolder");
// 3
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    imagePair, holderPair);
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        }
    };

    private void setUpActionBar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {

        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_view_list_black_24dp);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_view_module_black_24dp);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }
}
