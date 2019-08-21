package be.mickverm.rxdiffutil.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.mickverm.rxdiffutil.sample.ui.items.ItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ItemsFragment.newInstance())
                    .commitNow()
        }
    }
}
