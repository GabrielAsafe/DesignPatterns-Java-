package implementation.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 * merdas que podem acontecer usando threads
 *
 *
 * se uma trhead grava ou le de um recurso que vai ser usado por outra VAI DAR MERDA
 *
 * fim
 */



/**
 * Interface das operações que eu quero realizar
 */
interface operation {
    ArrayList<Integer> multiplyByConstant(ArrayList<Integer> a);

    ArrayList<Integer> multiplyByValue(ArrayList<Integer> a, int X);

    ArrayList<Integer> addTwoArrays(ArrayList<Integer> a, ArrayList<Integer> b);

    int sumAllValuesOfArray(ArrayList<Integer> a);

    ArrayList<Integer> topHatFilter(ArrayList<Integer> a); // Assume que você vai implementar o filtro
}


/**
 * Em um sistema de pool de threads aparentemente só é necessário uma interface de execussão e mais nada....
 */
interface Task {
    void execute();
}


/**
 * o preto que vai realizar a execução do programa
 */
class Worker extends Thread {
    private final BlockingQueue<Task> taskQueue;//permite bloquear uma queue caso ela esteja cheia e precise aguardar esvaziar, ou esteja vazia e precise que entre algum valor para operar
    private volatile boolean isStopped = false;

    public Worker(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                Task task = taskQueue.take();
                task.execute();
            } catch (InterruptedException e) {
                if (isStopped) {
                    return;
                }
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopWorker() {
        isStopped = true;
        this.interrupt();
    }
}


/**
 * eu fiz a threadpool um silglwtoon. pq ? achei bacana. agora só existe uma única pool.
 */
final class ThreadPool {
    enum Workload {LOW, MEDIUM, HIGH, CUSTOM}//define a quantidade de threads que o sistema vai ter e não defini o que o custom faz. lol

    private final BlockingQueue<Task> taskQueue;
    private final List<Worker> workers;
    private volatile boolean isStopped = false;
    private static volatile ThreadPool instance;

    /**
     * isso aqui é o singleton. é ele que é chamado quando quero usar as cenas da pool. também define a quantidade de threads que vai ter no programa com base na contidade de núcleos que tem no pc
     */
    public static ThreadPool getInstance(Workload workload) {
        int finalWorkload = 0;
        int numCores = Runtime.getRuntime().availableProcessors();

        switch (workload) {
            case LOW:
                finalWorkload = (int) Math.floor(numCores * 0.3);
                break;
            case MEDIUM:
                finalWorkload = (int) Math.floor(numCores * 0.5);
                break;
            case HIGH:
                finalWorkload = numCores;
                break;
        }

        ThreadPool result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ThreadPool.class) {
            if (instance == null) {
                instance = new ThreadPool(finalWorkload * 2);
            }
            return instance;
        }
    }


    private ThreadPool(int numThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }//inicializa as merdas. cria as threads e adiciona elas na pool


    /**
     * essa porra é que exetuta a tarefa. é o exec. a task que for passada aqui vai ser executada
     *
     * @param task
     */
    public synchronized void submit(Task task) {
        if (isStopped) throw new IllegalStateException("ThreadPool is stopped");
        taskQueue.offer(task);
    }

    /**
     * esse puto que para os workers quando o programa acaba.
     */
    public synchronized void stop() {
        isStopped = true;
        for (Worker worker : workers) {
            worker.stopWorker();
        }
    }
}

/**
 * outra porra de singleton pq sim. vai ter singleton até o cu fazer bico e fds
 * <p>
 * isso aqui implementa as operações todas usando stream pq deu vontade de fazer assim
 */
class Operate implements operation {
    static Operate operate;

    public static Operate getOperate() {
        Operate result = operate;
        if (result != null) {
            return result;
        }
        synchronized (Operate.class) {
            if (operate == null) {
                operate = new Operate();
            }
            return operate;
        }
    }

    @Override
    public synchronized ArrayList<Integer> multiplyByConstant(ArrayList<Integer> a) {
        int constant = 10; // Constant multiplier

        ArrayList<Integer> res = new ArrayList<>();
        for (int val : a) {
            res.add(val * constant);
        }
        return res;
//        return a.stream()
//                .map(integer -> integer * constant)
//                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public synchronized ArrayList<Integer> multiplyByValue(ArrayList<Integer> a, int X) {

        ArrayList<Integer> res = new ArrayList<>();
        for (int val : a) {
            res.add(val * X);
        }
        return res;

//
//        return a.stream()
//                .map(integer -> integer * X)
//                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public synchronized ArrayList<Integer> addTwoArrays(ArrayList<Integer> a, ArrayList<Integer> b) {
        int size = Math.min(a.size(), b.size());
        ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(a.get(i) + b.get(i));
        }
        return result;
    }

    @Override
    public synchronized int sumAllValuesOfArray(ArrayList<Integer> a) {


        int val2 = 0;
        for (int val : a) {
            val2 += val;

        }

        return val2;
    }

    @Override
    public synchronized ArrayList<Integer> topHatFilter(ArrayList<Integer> a) {

        ArrayList<Integer> res = new ArrayList<>();
        int x= 3;
        int y=7;

        for (int val : a) {
            if( x <= val && val <= y )
                    res.add(val);
            else
                res.add(0);
        }


        return res;

    }
}


/**
 * Essa piranha é a implementaçao das tasks que quero executar. pq a interface TASK tem um fucking execute hahaha
 */
class ArrayOperator implements Task {
    private final Operate op;
    private final ArrayList<Integer> array1;
    private final ArrayList<Integer> array2;

    private int value;
    private final int operation;//switch case

    public ArrayOperator(Operate op, ArrayList<Integer> a, ArrayList<Integer> b, int operat, int value) {
        this.op = op;
        this.array1 = a;
        this.array2 = b;
        this.operation = operat;
        this.value = value;
    }

    @Override
    public void execute() {
        System.out.println("Task is running on " + Thread.currentThread().getName());
        switch (operation) {
            case 1:


                System.out.println(op.multiplyByConstant(array1));
                break;
            case 2:

                System.out.println(op.multiplyByValue(array1, value));
                break;
            case 3:

                System.out.println(op.addTwoArrays(array1, array2));
                break;
            case 4:
                int sum = op.sumAllValuesOfArray(array1);
                System.out.println("Sum of all values do array 1: " + sum);
                int sum2 = op.sumAllValuesOfArray(array2);
                System.out.println("Sum of all values do array 2: " + sum2);
                break;
            case 5:

                System.out.println(op.topHatFilter(array1));
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }
    }
}

class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance(ThreadPool.Workload.HIGH);
        Operate operate = Operate.getOperate();

        ArrayList<Integer> array1 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        ArrayList<Integer> array2 = new ArrayList<>(List.of(50, 40, 30, 20, 10));
        ArrayList<Integer> result = new ArrayList<>();

        Task multiplyByConstTask = new ArrayOperator(operate, array1, array2, 1, 0);
        Task multiplyByValueTask = new ArrayOperator(operate, array1, array2, 2, 52);
        Task addTwoArraysTask = new ArrayOperator(operate, array1, array2, 3, 0);
        Task sumAllValuesTask = new ArrayOperator(operate, array1, array2, 4, 0);
        Task topHatFilter = new ArrayOperator(operate, array1, array2, 5, 0);

        threadPool.submit(multiplyByConstTask);
        threadPool.submit(multiplyByValueTask);
        threadPool.submit(addTwoArraysTask);
        threadPool.submit(sumAllValuesTask);
        threadPool.submit(topHatFilter);

        threadPool.stop();
    }
}
