package pw.saber.framework.compat;

public class RParam {
    private Class<?> methodClass;

    private Object instance;

    public RParam(Class<?> methodClass, Object instance) {
        this.methodClass = methodClass;
        this.instance = instance;
    }

    public RParam(Class<?> clazz) {
        this.methodClass = clazz;
    }

    public Class<?> getMethodClass() {
        return this.methodClass;
    }

    public Object getInstance() {
        return this.instance;
    }
}

