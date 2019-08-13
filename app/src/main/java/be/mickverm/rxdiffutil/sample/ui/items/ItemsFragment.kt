package be.mickverm.rxdiffutil.sample.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.rxdiffutil.RxDiffUtil
import be.mickverm.rxdiffutil.sample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ItemsFragment : Fragment() {

    companion object {
        fun newInstance() = ItemsFragment()
    }

    private lateinit var viewModel: ItemsFragmentViewModel
    private lateinit var adapter: ItemsAdapter

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(
            this,
            ItemsFragmentViewModelFactory()
        ).get(ItemsFragmentViewModel::class.java)

        adapter = ItemsAdapter()

        val rvItems = view.findViewById<RecyclerView>(R.id.rv_items)
        rvItems.layoutManager = GridLayoutManager(context, 5)
        rvItems.adapter = adapter

        disposables.add(
            viewModel.randomItems
                .compose(RxDiffUtil.calculateFlowable(::ItemDiffCallback))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
