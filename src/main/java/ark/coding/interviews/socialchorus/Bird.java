package ark.coding.interviews.socialchorus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

interface Bird {
    Egg lay();
}

class Chicken implements Bird{
    public Chicken() {
    }

    public static void main(String[] args) throws Exception {
        Chicken chicken = new Chicken();
        System.out.println(chicken instanceof Bird);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Bird>> futureList = new ArrayList<>();

    }

    @Override
    public Egg lay() {
        return new Egg(new Callable<Bird>() {
            @Override
            public Bird call() throws Exception {
                return new Chicken();
            }
        });
    }
}

class Egg {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Future<Bird> future;
    int noOfHatches = 0;
    public Egg(Callable<Bird> createBird) {
        future = executor.submit(createBird);
    }

    public Bird hatch() throws Exception {
        if(noOfHatches > 1){
            throw new IllegalStateException();
        } else {
            noOfHatches = noOfHatches + 1;
            return future.get();
        }
    }
}