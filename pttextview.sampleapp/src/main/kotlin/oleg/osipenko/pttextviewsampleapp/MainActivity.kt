package oleg.osipenko.pttextviewsampleapp;

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.activity_main.viewGroup
import org.jetbrains.anko.alert
import org.jetbrains.anko.padding
import org.jetbrains.anko.webView

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewGroup.views.forEachIndexed { i, view ->
            view.setOnClickListener {
                val intent = Intent(this@MainActivity, javaClass<SampleActivity>())
                intent.putExtra(INDEX, i)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            alert {
                title(R.string.title)
                customView {
                    webView() {
                        padding = 16
                    }.loadUrl("file:///android_asset/licenses.html")
                }
                positiveButton(R.string.btn_close) {
                    dismiss()
                }
            }.show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    public val ViewGroup.views: List<View>
        get() = (0..getChildCount() - 1) map { getChildAt(it) }
}
