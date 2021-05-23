
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {

    //Chapter 2

    //jsut
    val ob1 = Observable.just(1,2,3);
    ob1.subscribe {
        println(it);
    }

    // fromIterable
    val ob2 = Observable.fromIterable(listOf(1,2,3));
    ob2.subscribe {
        println(it);
    }

    // empty
    val ob3 = Observable.empty<Unit>()
    ob3.subscribeBy (
        onNext = {println("onNext")},
        onComplete = {println("onComplete")}
    )

    // never
    val ob4 = Observable.never<Unit>()
    ob4.subscribeBy (
        onNext = {println("onNext")},
        onComplete = {println("onComplete")}
    )

    // range
    val ob5 = Observable.range(0, 5)
    ob5.subscribe{
        println(it);
    }

    // CompositeDisposable
    val d6 = CompositeDisposable()
    val ob6 = Observable.just(1,2,3);
    ob1.subscribeBy (
        onNext = {println(it)},
        onComplete = {println("onComplete")}
    ).addTo(d6)

    //d6.dispose()

    //compose
//    Observable.range(1,10).compose(handleObservableCompose()).subscribe{
//        println(it)
//    }

//    Single.just(2).compose(handleSingleCompose()).doOnSuccess{
//        println(it)
//    }

    //Observable.range(1,10).flatMap { fetch(it) }.subscribe{ println(it) }

}

fun <T> handleObservableCompose() : ObservableTransformer<T,T> {
    return ObservableTransformer { it.doOnNext{println(it)} }
}

fun  <T> handleSingleCompose() : SingleTransformer<T, T> {
    return SingleTransformer { it.doOnSuccess{println(it)} }
}

fun fetch(id : Int) : Observable<String> {
    return Observable.create<String>{
        it.onNext(id.toString() + "test")
    }
}