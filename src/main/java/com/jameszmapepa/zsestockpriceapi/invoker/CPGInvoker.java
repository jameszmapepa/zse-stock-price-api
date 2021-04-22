package com.jameszmapepa.zsestockpriceapi.invoker;

import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGRequest;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGResponse;

public interface CPGInvoker {

    CPGResponse process(CPGRequest request) throws Exception;
}
