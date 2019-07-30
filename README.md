RxDiffUtil
==========
[ ![Download](https://api.bintray.com/packages/mickverm/maven/RxDiffUtil/images/download.svg) ](https://bintray.com/mickverm/maven/RxDiffUtil/_latestVersion)

Download
--------

````groovy
implementation 'be.mickverm.rxjava2:rxdiffutil:1.0.0'
````

Usage
-----

````kotlin
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), 
    Consumer<Pair<List<Item>, DiffUtil.DiffResult> {

    private var items = listOf<Item>()

    override fun accept(pair: Pair<List<Item>, DiffUtil.DiffResult>) {
        items = pair.first
        pair.second.dispatchUpdatesTo(this)
    }
}
````

````kotlin
database.observableItems
	.compose(RxDiffUtil.calculateObservable((ItemDiffCallback)::create))
	.subscribeOn(Schedulers.computation())
	.observeOn(AndroidSchedulers.mainThread())
	.subscribe(recyclerViewAdapter)

database.flowableItems
	.compose(RxDiffUtil.calculateObservable((ItemDiffCallback)::create))
	.subscribeOn(Schedulers.computation())
	.observeOn(AndroidSchedulers.mainThread())
	.subscribe(recyclerViewAdapter)
````

License
-------

    Copyright 2019 Michiel Vermeersch

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
