package test.hamakn.myapp1;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    // 参考: http://outofmem.tumblr.com/post/101241273599/android-listview-1
    public class MyData {
        private String mTitle;
        private String mDescription;

        public MyData(String title, String description) {
            mTitle = title;
            mDescription = description;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getDescription() {
            return mDescription;
        }
    }

    public class MyAdapter extends ArrayAdapter<MyData> {
        private final LayoutInflater _inflater;

        public MyAdapter(Context context, List<MyData> objects) {
            super(context, 0, objects);
            _inflater = LayoutInflater.from(context);
        }

        /*
        public MyAdapter(Context context, MyData[] objects) {
            super(context, 0, objects);
            _inflater = LayoutInflater.from(context);
        }
        */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = _inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            MyData data = super.getItem(position);
            ((TextView) v.findViewById(android.R.id.text1)).setText(data.getTitle());
            ((TextView) v.findViewById(android.R.id.text2)).setText(data.getDescription());

            return v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.mainListView);

        // click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lv = (ListView) parent;
                Object item = parent.getItemAtPosition(position);
            }
        });

        /* simple ArrayAdapter example
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        adapter.add("a");
        adapter.add("b");
        adapter.add("c");
        listView.setAdapter(adapter);
        */

        List<MyData> list = new ArrayList<>();
        list.add(new MyData("タイトル1", "デスクリプション1"));
        list.add(new MyData("タイトル2", "デスクリプション2"));
        list.add(new MyData("タイトル3", "デスクリプション3"));

        MyAdapter adapter = new MyAdapter(
                this, //getApplicationContext(),
                list
        );
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
