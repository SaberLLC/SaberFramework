package pw.saber.framework.compat;

import java.util.LinkedList;
import java.util.List;

public class ParamBuilder {
    private List<RParam> parameters = new LinkedList<>();
    private int validObjectCount = 0;

    public ParamBuilder(Class<?> clazz, Object obj) {
        add(clazz, obj);
    }

    public ParamBuilder() {
    }

    public List<RParam> getParameters() {
        return this.parameters;
    }

    public int getValidObjectCount() {
        return this.validObjectCount;
    }

    public void setValidObjectCount(int validObjectCount) {
        this.validObjectCount = validObjectCount;
    }

    public void add(RParam param) {
        this.parameters.add(param);
        if (param != null)
            this.validObjectCount++;
    }

    public void add(Class<?> clazz, Object param) {
        add(new RParam(clazz, param));
    }
}

