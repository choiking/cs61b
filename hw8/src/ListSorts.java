/* ListSorts.java */

import list.*;

import java.util.Objects;

public class ListSorts {


    /**
     *  makeQueueOfQueues() makes a queue of queues, each containing one item
     *  of q.  Upon completion of this method, q is empty.
     *  @param q is a LinkedQueue of objects.
     *  @return a LinkedQueue containing LinkedQueue objects, each of which
     *    contains one object from q.
     **/
    public static LinkedQueue makeQueueOfQueues(LinkedQueue q) throws QueueEmptyException{
        // Replace the following line with your solution.
        if(q.isEmpty()) return null;
        LinkedQueue queList=new LinkedQueue();
        int k=q.size();
        for(int i=0;i<k;i++) {
            LinkedQueue lq=new LinkedQueue();
            lq.enqueue(q.dequeue());
            queList.enqueue(lq);
        }
        return queList;
    }

    /**
     *  mergeSortedQueues() merges two sorted queues into a third.  On completion
     *  of this method, q1 and q2 are empty, and their items have been merged
     *  into the returned queue.
     *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @return a LinkedQueue containing all the Comparable objects from q1
     *   and q2 (and nothing else), sorted from smallest to largest.
     **/
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException {
        // Replace the following line with your solution.
        LinkedQueue bigQ = new LinkedQueue();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Comparable item1 = (Comparable) q1.front();
            Comparable item2 = (Comparable) q2.front();
            if (item1.compareTo(item2) > 0) bigQ.enqueue(q2.dequeue());
            else bigQ.enqueue(q1.dequeue());
        }
        if(q1.isEmpty()) bigQ.append(q2);
        else  bigQ.append(q1);
        return bigQ;
    }

    /**
     *  partition() partitions qIn using the pivot item.  On completion of
     *  this method, qIn is empty, and its items have been moved to qSmall,
     *  qEquals, and qLarge, according to their relationship to the pivot.
     *  @param qIn is a LinkedQueue of Comparable objects.
     *  @param pivot is a Comparable item used for partitioning.
     *  @param qSmall is a LinkedQueue, in which all items less than pivot
     *    will be enqueued.
     *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
     *    will be enqueued.
     *  @param qLarge is a LinkedQueue, in which all items greater than pivot
     *    will be enqueued.
     **/
    public static void partition(LinkedQueue qIn, Comparable pivot,
                                 LinkedQueue qSmall, LinkedQueue qEquals,
                                 LinkedQueue qLarge) throws QueueEmptyException {
        // Your solution here.
        if (qIn.size()<2) return;
        while(!qIn.isEmpty()){
            Comparable item=(Comparable)qIn.front();
            if(item.compareTo(pivot)<0) qSmall.enqueue(qIn.dequeue());
            else if(item.compareTo(pivot)==0) qEquals.enqueue(qIn.dequeue());
            else   qLarge.enqueue(qIn.dequeue());
        }
    }

    /**
     *  mergeSort() sorts q from smallest to largest using mergesort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void mergeSort(LinkedQueue q) throws QueueEmptyException {
        // Your solution here.
        if(q.size()<2) return;
        LinkedQueue newQ =makeQueueOfQueues(q);//make a queList using the input q
        while(newQ.size()>1){
            LinkedQueue q1= (LinkedQueue)newQ.dequeue();
            LinkedQueue q2=(LinkedQueue) newQ.dequeue();
            newQ.enqueue(mergeSortedQueues(q1,q2));
        }
        q.append((LinkedQueue) newQ.front());
    }

    /**
     *  quickSort() sorts q from smallest to largest using quicksort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void quickSort(LinkedQueue q) throws QueueEmptyException {
        // Your solution here.
        if(q.size()<2) return;
        //divide
        //Randomly choose a pivot
        Comparable pivot=(Comparable) q.nth(new Integer((int) (q.size() * Math.random())+1));
        LinkedQueue qSmall=new LinkedQueue();
        LinkedQueue qEquals=new LinkedQueue();
        LinkedQueue qLarge=new LinkedQueue();
        partition(q, pivot, qSmall, qEquals, qLarge);
        //conquer
        quickSort(qSmall);
        quickSort(qLarge);
        //concatenate
        q.append(qSmall);
        q.append(qEquals);
        q.append(qLarge);
    }

    /**
     *  makeRandom() builds a LinkedQueue of the indicated size containing
     *  Integer items.  The items are randomly chosen between 0 and size - 1.
     *  @param size is the size of the resulting LinkedQueue.
     **/
    public static LinkedQueue makeRandom(int size) {
        LinkedQueue q = new LinkedQueue();
        for (int i = 0; i < size; i++) {
            q.enqueue(new Integer((int) (size * Math.random())));
        }
        return q;
    }

    public  static void countTime(int size) throws QueueEmptyException {
        Timer stopWatch = new Timer();
        LinkedQueue q = makeRandom(size);
        stopWatch.start();
        mergeSort(q);
        stopWatch.stop();
        System.out.println("Mergesort time, " + size + " Integers:  " +
                stopWatch.elapsed() + " msec.");

        stopWatch.reset();
        q = makeRandom(size);
        stopWatch.start();
        quickSort(q);
        stopWatch.stop();
        System.out.println("Quicksort time, " + size + " Integers:  " +
                stopWatch.elapsed() + " msec.");
    }
    /**
     *  main() performs some tests on mergesort and quicksort.  Feel free to add
     *  more tests of your own to make sure your algorithms works on boundary
     *  cases.  Your test code will not be graded.
     **/
    public static void main(String [] args) throws QueueEmptyException {

        LinkedQueue q = makeRandom(9);
        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(10);
        System.out.println(q.toString());;
        quickSort(q);
        System.out.println(q.toString());


    /*Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE1);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE1 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE1);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE1 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    */
        countTime(100);
        countTime(1000);
        countTime(10000);
        countTime(100000);
        countTime(1000000);
    }

}