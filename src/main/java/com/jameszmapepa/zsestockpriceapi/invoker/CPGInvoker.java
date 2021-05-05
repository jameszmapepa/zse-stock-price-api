package com.jameszmapepa.zsestockpriceapi.invoker;

import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGRequest;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGResponse;
import org.springframework.http.HttpMethod;

public interface CPGInvoker {

    CPGResponse process(CPGRequest request, HttpMethod httpMethod, String url) throws Exception;
}
