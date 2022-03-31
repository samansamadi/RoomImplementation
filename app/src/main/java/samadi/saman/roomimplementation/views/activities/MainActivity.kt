package samadi.saman.roomimplementation.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import samadi.saman.roomimplementation.MemoApp
import samadi.saman.roomimplementation.R
import samadi.saman.roomimplementation.adapters.MemoAdapter
import samadi.saman.roomimplementation.models.entities.Memo
import samadi.saman.roomimplementation.viewmodels.MainViewModel
import samadi.saman.roomimplementation.viewmodels.MainViewModelFactory
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MemoApp).repository)
    }

    private lateinit var adapter: MemoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initList()

        viewModel.getMemos().observe(this) { memos ->
            adapter.differ.submitList(memos)
        }
    }

    fun initList() {
        adapter = MemoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun initViews() {
        recyclerView = findViewById(R.id.memoList)
        title = findViewById(R.id.titleInputEditText)
        description = findViewById(R.id.descriptionInputEditText)
        submit = findViewById(R.id.submit)

        submit.setOnClickListener {
            if (title.text.isNullOrBlank()) {
                title.error = "Enter a title"
                return@setOnClickListener
            } else title.error = null

            if (description.text.isNullOrBlank()) {
                description.error = "Enter a description"
                return@setOnClickListener
            } else description.error = null

            launch {
                val result = viewModel.addMemo(
                    Memo(
                        title.text.toString(),
                        description.text.toString()
                    )
                )

                result.invokeOnCompletion {
                    if (it == null) { /* Job successfully finished */
                        Snackbar.make(
                            title.rootView,
                            "Item was successfully added",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        title.setText("")
                        description.setText("")
                        viewModel.getMemos()
                    } else Toast.makeText(
                        this@MainActivity,
                        "Error when adding item to database",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}