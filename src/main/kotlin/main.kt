
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

fun main(args: Array<String>) {
    println("Hello World!")

    //compose
    Observable.range(1,10).compose(handleCompose()).subscribe{
        println(it)
    }
}

fun <T> handleCompose() : ObservableTransformer<T,T> {
    return ObservableTransformer { it.doOnNext{println(it)} }
}