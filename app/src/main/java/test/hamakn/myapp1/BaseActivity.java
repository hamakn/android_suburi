package test.hamakn.myapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class BaseActivity extends ActionBarActivity {

    // Create系
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");

        SharedPreferences prefs = getSharedPreferences("BaseActivity", Context.MODE_PRIVATE);
        int pauseCount = prefs.getInt("pauseCount", 0);
        Log.d("tag", "pauseCount: " + pauseCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
    }

    // NOTE:
    // http://developer.android.com/reference/android/app/Activity.html#onRestoreInstanceState(android.os.Bundle)
    // Most implementations will simply use onCreate(Bundle) to restore (their) state
    // 画面回転の後とかに呼ばれる
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log("onRestoreInstanceState");

        //Integer i = savedInstanceState.getInt("FOO");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        log("onPostCreate");
    }

    // Resume系
    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        log("onPostResume");
    }

    // Pause系
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        log("onUserLeaveHint");
    }

    // NOTE:
    // http://developer.android.com/reference/android/app/Activity.html#onSaveInstanceState(android.os.Bundle)
    // onPauseの前に必ず呼ばれるわけではない
    // onPauseの際に必ずデータを保存したいのであれば、onPauseにSharedPreferenceを使って実装する
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState");

        //outState.putInt("FOO", 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");

        // http://developer.android.com/reference/android/content/Context.html#getSharedPreferences(java.lang.String, int)
        SharedPreferences prefs = getSharedPreferences("BaseActivity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("pauseCount", prefs.getInt("pauseCount", 0) + 1);
        editor.apply();
    }

    // Stop系
    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        boolean result = super.onCreateThumbnail(outBitmap, canvas);
        log("onCreateThumbnail");
        return result;
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
    }

    // Destroy系
    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    // Restart系
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        log("onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart");
    }

    protected void log(@NonNull String methodName) {
        Log.d("Lifecycle", getLocalClassName() + ":" + methodName);
    }
}