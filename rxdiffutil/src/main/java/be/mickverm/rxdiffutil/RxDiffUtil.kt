package be.mickverm.rxdiffutil

import androidx.recyclerview.widget.DiffUtil
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

object RxDiffUtil {

    @JvmStatic
    fun <T> calculateObservable(
        itemDiffer: BiFunction<List<T>, List<T>, DiffUtil.Callback>
    ): ObservableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> {
        val diffResult = DiffUtil.calculateDiff(itemDiffer.apply(emptyList(), emptyList()))
        val seedPair: Pair<List<T>, DiffUtil.DiffResult> = Pair(emptyList(), diffResult)
        return ObservableTransformer { upstream ->
            upstream
                .scan(seedPair, { oldPair, nextItems ->
                    val callback = itemDiffer.apply(oldPair.first, nextItems)
                    val result = DiffUtil.calculateDiff(callback, true)
                    Pair(nextItems, result)
                })
                .skip(1) // downstream shouldn't receive seedPair.
        }
    }

    inline fun <T> calculateObservable(
        crossinline function: (List<T>, List<T>) -> DiffUtil.Callback
    ): ObservableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> =
        calculateObservable(BiFunction { old, new -> function(old, new) })

    @JvmStatic
    fun <T> calculateFlowable(
        itemDiffer: BiFunction<List<T>, List<T>, DiffUtil.Callback>
    ): FlowableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> {
        val diffResult = DiffUtil.calculateDiff(itemDiffer.apply(emptyList(), emptyList()))
        val seedPair: Pair<List<T>, DiffUtil.DiffResult> = Pair(emptyList(), diffResult)
        return FlowableTransformer { upstream ->
            upstream
                .scan(seedPair, { oldPair, nextItems ->
                    val callback = itemDiffer.apply(oldPair.first, nextItems)
                    val result = DiffUtil.calculateDiff(callback, true)
                    Pair(nextItems, result)
                })
                .skip(1) // downstream shouldn't receive seedPair.
        }
    }

    inline fun <T> calculateFlowable(
        crossinline function: (List<T>, List<T>) -> DiffUtil.Callback
    ): FlowableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> =
        calculateFlowable(BiFunction { old, new -> function(old, new) })
}
