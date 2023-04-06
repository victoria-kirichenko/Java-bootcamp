package ex05;

import java.util.UUID;

class TransactionsLinkedListNode {
    Transaction data;
    TransactionsLinkedListNode next;
    TransactionsLinkedListNode prev;

    TransactionsLinkedListNode(Transaction data_, TransactionsLinkedListNode next_, TransactionsLinkedListNode prev_) {
        data = data_;
        next = next_;
        prev = prev_;
    }
}

public class TransactionLinkedList implements TransactionsList {

    TransactionsLinkedListNode head = null;
    TransactionsLinkedListNode tail = null;
    int count = 0;


    @Override
    public void transactionAdd(Transaction tr) {
        var node = new TransactionsLinkedListNode(tr, null, null);

        if(head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        count++;
    }
    @Override
    public void transactionRemove(UUID id) {
        if(head == null) {
            throw new TransactionNotFoundException();
        }
        TransactionsLinkedListNode delete_node = foundNode(id);
        if (delete_node == head) {
            head = head.next;
        } else {
            delete_node.prev.next = delete_node.next;
        }
        if (delete_node == tail) {
            tail = delete_node.prev;
        } else {
            delete_node.next.prev = delete_node.prev;
        }
        count--;
    }

    public TransactionsLinkedListNode foundNode(UUID id) {
        TransactionsLinkedListNode current = head;
        if (current == null) throw new TransactionNotFoundException();
        while (current != null) {
            if (current.data.id.equals(id)) break;
            current = current.next;
        }
        return current;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[count];
        TransactionsLinkedListNode current = head;
        int idx = 0;
        while (current != null) {
            arr[idx] = current.data;
            current = current.next;
            idx++;
        }
        return arr;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Transaction getTransactionByid(UUID id) {
        Transaction transaction;
        TransactionsLinkedListNode current = head;
        if (current == null) throw new TransactionNotFoundException();
        while (current != null) {
            if (current.data.id.equals(id)) break;
            current = current.next;
        }
        transaction = current.data;
        return transaction;
    }
}