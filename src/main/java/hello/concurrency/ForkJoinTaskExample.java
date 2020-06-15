package hello.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskExample {
    public interface Node {
        Collection<Node> getChildren();

        long getValue();

        void add(Node child);
    }

    public static class ClassNode implements Node {
        private long value;
        private Collection<Node> children = new ArrayList<>();

        public ClassNode(long value) {
            this.value = value;
        }

        @Override
        public Collection<Node> getChildren() {
            return children;
        }

        public void add(Node child) {
            children.add(child);
        }

        @Override
        public long getValue() {
            return value;
        }
    }

    public static class ValueSumCounter extends RecursiveTask<Long> {
        private final Node node;

        public ValueSumCounter(Node node) {
            this.node = node;
        }

        @Override
        protected Long compute() {
            long sum = node.getValue();
            List<ValueSumCounter> subTasks = new LinkedList<>();

            for(Node child : node.getChildren()) {
                ValueSumCounter task = new ValueSumCounter(child);
                task.fork(); // запустим асинхронно
                subTasks.add(task);
            }

            for(ValueSumCounter task : subTasks) {
                sum += task.join(); // дождёмся выполнения задачи и прибавим результат
            }

            System.out.println("TOTAL SUM: " + sum);

            return sum;
        }
    }

    public static void main(String[] args) {
        Node root = getRootNode();
        new ForkJoinPool().invoke(new ValueSumCounter(root));
    }

    public static Node getRootNode() {
        Node node1 = new ClassNode(1);
        Node node2 = new ClassNode(3);
        Node node3 = new ClassNode(5);
        Node node4 = new ClassNode(2);
        Node node5 = new ClassNode(4);
        Node node6 = new ClassNode(2);

        node5.add(node6);

        node1.add(node2);
        node1.add(node3);
        node1.add(node4);
        node1.add(node5);

        return node1;
    }
}
