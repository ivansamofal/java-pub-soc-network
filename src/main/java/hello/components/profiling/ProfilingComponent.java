package hello.components.profiling;

import lombok.Data;

@Data
public class ProfilingComponent  implements ProfilingComponentMBean {
    private boolean enabled = true;
}
