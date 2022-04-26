package edu.school21.numbers;

class illegalNumberException extends RuntimeException {
    @Override
    public String toString() {
        return "Illigal number exception";
    }
}

public class NumberWorker {
    public boolean isPrime(int input) {
        if (input < 2)
            throw new illegalNumberException();
        for (int i = 2; i * i <= input; i++)
            if (input % i == 0)
                return false;
        return true;
    }

    public int digitsSum(int number) {
        number *= number < 0 ? -1 : 1;
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}

