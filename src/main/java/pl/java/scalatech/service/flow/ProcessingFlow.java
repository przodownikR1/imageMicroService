package pl.java.scalatech.service.flow;

public abstract class ProcessingFlow<T> {
    protected ProcessingFlow<T> successor;

    public void setSuccessor(ProcessingFlow<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);        
        if (successor != null) { return successor.handle(r); }
        return r;
    }

abstract protected T handleWork(T input);
}