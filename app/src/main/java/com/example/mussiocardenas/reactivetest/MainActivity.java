package com.example.mussiocardenas.reactivetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Unbinder unbinder;

    @BindView(R.id.btnAction) Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);


        // Rx PoC 1) Create observable behavior
        /*Observable<Integer> integerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        });*/


        // Rx PoC 2) Define subscriber behavior
        Subscriber<Integer> integerSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,integer.toString());
            }
        };

        // Rx PoC 3) Bind Observable & Subscriber
        // Observable.just(1, 2, 3).subscribe( integerSubscriber );

        // Alternative With Lambdas
        /*Observable.just(4, 5, 6).doOnNext( i -> {
            Log.d(TAG, "Hello " + i-- );
        }).subscribe(integerSubscriber);*/

        // RxJava PoC - Reduce
        // Observable.just(1,1,1,1).reduce(0, (a,b) -> a + b ).subscribe(integerSubscriber);

        // RxJava PoC - Filter
        Observable.just(1,2,3,4)
                .reduce(0, (a,b) -> a + b )
                .filter(value -> {
                    Log.i(TAG, " value: " + value );
                        return value != null ; })
                .subscribe(integerSubscriber);
    }

    @OnClick(R.id.btnAction)
    public void doAction(Button btn){
        Toast.makeText(this, "agile", Toast.LENGTH_SHORT).show();
        btn.setText("Halo");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(unbinder!= null) unbinder.unbind();
    }
}