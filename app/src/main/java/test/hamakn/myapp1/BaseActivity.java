package test.hamakn.myapp1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", getLocalClassName() + ":onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", getLocalClassName() + ":onResume");
    }
}