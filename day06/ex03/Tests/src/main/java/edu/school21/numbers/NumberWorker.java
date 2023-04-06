package edu.school21.numbers;

import edu.school21.exceptions.AlreadyAuthenticatedException;

public class NumberWorker {

    public boolean isPrime(int number) {
        boolean isPrime = true;
        if (number <= 0 || number == 1) {
            throw new AlreadyAuthenticatedException();
        }
        for (int i = 2; i <= Math.round(Math.sqrt(number)); i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
    public int digitsSum(int number) {
        int sum = 0;
        while (number != 0) {
            int ost = number % 10;
            sum += ost;
            number /= 10;
        }
        return sum;
    }
}

